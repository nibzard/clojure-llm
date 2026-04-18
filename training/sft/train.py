#!/usr/bin/env python3
"""
SFT Training Script for Clojure LLM using Tinker Platform.

Fine-tunes Qwen3-8B-Base on verified Clojure code pairs via LoRA.
Uses Tinker's cloud training API with cross-entropy loss.

Usage:
    python3 training/sft/train.py --config training/sft/config.yaml
    python3 training/sft/train.py --limit 20  # quick smoke test
"""

import argparse
import json
import math
import os
import sys
import time
from pathlib import Path

import numpy as np
import wandb
import yaml
from dotenv import load_dotenv
from transformers import AutoTokenizer

from tinker import (
    AdamParams,
    Datum,
    ModelInput,
    ServiceClient,
)

ROOT = Path(__file__).resolve().parent.parent.parent


def load_config(path):
    with open(path) as f:
        return yaml.safe_load(f)


def load_jsonl(path):
    data = []
    with open(path) as f:
        for line in f:
            line = line.strip()
            if line:
                data.append(json.loads(line))
    return data


def tokenize_pair(tokenizer, prompt_text, response_text, max_length):
    """Tokenize a prompt-response pair for SFT.

    Returns (ModelInput, target_tokens, loss_mask) or None if too long.
    """
    # Tokenize prompt and response separately to build loss mask
    prompt_ids = tokenizer.encode(prompt_text, add_special_tokens=True)
    response_ids = tokenizer.encode(response_text, add_special_tokens=False)

    # Concatenate
    all_ids = prompt_ids + response_ids

    if len(all_ids) > max_length:
        return None

    # Target tokens: shift by 1 (next-token prediction)
    target_tokens = all_ids[1:] + [tokenizer.eos_token_id or 0]

    # Loss mask: 0 for prompt, 1 for response
    prompt_len = len(prompt_ids)
    loss_mask = [0] * prompt_len + [1] * len(response_ids)

    # Shift mask to align with target
    loss_mask = loss_mask[1:] + [0]

    model_input = ModelInput.from_ints(all_ids)

    return model_input, target_tokens, loss_mask


def create_batch(tokenizer, records, batch_idx, batch_size, max_length):
    """Create a batch of Datum objects for training."""
    start = batch_idx * batch_size
    end = min(start + batch_size, len(records))
    batch_records = records[start:end]

    data = []
    skipped = 0

    for rec in batch_records:
        messages = rec["messages"]
        prompt_text = messages[0]["content"]
        response_text = messages[1]["content"]

        result = tokenize_pair(tokenizer, prompt_text, response_text, max_length)
        if result is None:
            skipped += 1
            continue

        model_input, target_tokens, loss_mask = result

        datum = Datum(
            model_input=model_input,
            loss_fn_inputs={
                "target_tokens": target_tokens,
                "weights": loss_mask,
            },
        )
        data.append(datum)

    return data, skipped


def train(config, limit=None):
    """Main SFT training loop."""
    model_name = config.get("model", "Qwen3-8B-Base")
    tinker_model = f"Qwen/{model_name}"

    lora_cfg = config.get("lora", {})
    training_cfg = config.get("training", {})
    data_cfg = config.get("data", {})
    ckpt_cfg = config.get("checkpointing", {})
    log_cfg = config.get("logging", {})

    rank = lora_cfg.get("rank", 32)
    max_length = training_cfg.get("max_seq_length", 2048)
    num_epochs = training_cfg.get("num_epochs", 3)
    batch_size = training_cfg.get("batch_size", 8)
    lr = training_cfg.get("learning_rate", 2e-5)
    weight_decay = training_cfg.get("weight_decay", 0.01)
    warmup_ratio = training_cfg.get("warmup_ratio", 0.1)
    log_steps = log_cfg.get("log_steps", 10)
    save_steps = ckpt_cfg.get("save_steps", 100)
    output_dir = ckpt_cfg.get("output_dir", "checkpoints/sft")

    train_path = data_cfg.get("train_path", "data/sft/sft_train.jsonl")

    wandb_project = log_cfg.get("wandb_project", "clojure-llm")
    wandb_entity = log_cfg.get("wandb_entity", "nibzard-org")

    wandb.init(
        entity=wandb_entity,
        project=wandb_project,
        name=f"sft-{model_name}-lora{rank}",
        config={
            "model": tinker_model,
            "lora_rank": rank,
            "lora_alpha": lora_cfg.get("alpha", 64),
            "learning_rate": lr,
            "batch_size": batch_size,
            "max_seq_length": max_length,
            "num_epochs": num_epochs,
            "optimizer": training_cfg.get("optimizer", "adamw"),
            "weight_decay": weight_decay,
            "warmup_ratio": warmup_ratio,
            "lr_scheduler": training_cfg.get("lr_scheduler", "cosine"),
        },
    )

    print("=" * 60)
    print("SFT Training — Tinker Platform")
    print("=" * 60)
    print(f"  Model:          {tinker_model}")
    print(f"  LoRA rank:      {rank}")
    print(f"  Learning rate:  {lr}")
    print(f"  Batch size:     {batch_size}")
    print(f"  Max seq length: {max_length}")
    print(f"  Epochs:         {num_epochs}")
    print(f"  wandb:          {wandb_entity}/{wandb_project}")

    # Load tokenizer
    print("\nLoading tokenizer...")
    tokenizer = AutoTokenizer.from_pretrained(tinker_model, trust_remote_code=True)
    if tokenizer.pad_token is None:
        tokenizer.pad_token = tokenizer.eos_token

    # Load training data
    print(f"Loading data from {train_path}...")
    records = load_jsonl(train_path)
    if limit:
        records = records[:limit]
    print(f"  Training pairs: {len(records)}")

    # Connect to Tinker
    print("\nConnecting to Tinker...")
    service_client = ServiceClient()

    training_client = service_client.create_lora_training_client(
        base_model=tinker_model,
        rank=rank,
        train_mlp=True,
        train_attn=True,
        train_unembed=True,
    )
    print("  Connected. LoRA training client ready.")

    # Calculate steps
    steps_per_epoch = math.ceil(len(records) / batch_size)
    total_steps = steps_per_epoch * num_epochs
    warmup_steps = int(total_steps * warmup_ratio)

    print(f"\n  Steps per epoch: {steps_per_epoch}")
    print(f"  Total steps:     {total_steps}")
    print(f"  Warmup steps:    {warmup_steps}")

    os.makedirs(output_dir, exist_ok=True)

    # Training loop
    global_step = 0
    total_loss = 0.0
    total_skipped = 0
    start_time = time.time()

    for epoch in range(num_epochs):
        print(f"\n=== Epoch {epoch + 1}/{num_epochs} ===")

        # Shuffle records each epoch
        import random
        random.seed(42 + epoch)
        random.shuffle(records)

        for step in range(steps_per_epoch):
            global_step += 1

            # Compute LR with cosine schedule
            if global_step <= warmup_steps:
                current_lr = lr * global_step / warmup_steps
            else:
                progress = (global_step - warmup_steps) / (total_steps - warmup_steps)
                current_lr = lr * 0.1 + (lr - lr * 0.1) * 0.5 * (1 + math.cos(math.pi * progress))

            # Create batch
            batch_data, skipped = create_batch(
                tokenizer, records, step, batch_size, max_length
            )
            total_skipped += skipped

            if not batch_data:
                continue

            # Forward + backward
            fb_result = training_client.forward_backward(
                data=batch_data,
                loss_fn="cross_entropy",
            ).result()

            # Extract loss
            loss = fb_result.metrics.get("loss:sum", 0.0)
            total_loss += loss

            # Optimizer step
            adam_params = AdamParams(
                learning_rate=current_lr,
                weight_decay=weight_decay,
                grad_clip_norm=1.0,
            )
            training_client.optim_step(adam_params).result()

            # Logging
            if global_step % log_steps == 0:
                avg_loss = total_loss / log_steps
                elapsed = time.time() - start_time
                steps_per_sec = global_step / elapsed
                print(
                    f"  Step {global_step}/{total_steps} | "
                    f"Loss: {avg_loss:.4f} | "
                    f"LR: {current_lr:.2e} | "
                    f"Speed: {steps_per_sec:.1f} steps/s"
                )
                wandb.log({
                    "train/loss": avg_loss,
                    "train/learning_rate": current_lr,
                    "train/steps_per_sec": steps_per_sec,
                    "train/epoch": epoch + step / steps_per_epoch,
                }, step=global_step)
                total_loss = 0.0

            # Checkpoint
            if save_steps and global_step % save_steps == 0:
                ckpt_name = f"checkpoint-step-{global_step}"
                print(f"  Saving checkpoint: {ckpt_name}")
                training_client.save_state(ckpt_name).result()

    # Save final weights
    print(f"\nSaving final model...")
    sampling_client = training_client.save_weights_and_get_sampling_client(
        name="sft-clojure-final"
    )
    print("  Model saved. Sampling client ready.")

    elapsed = time.time() - start_time
    print(f"\nTraining complete!")
    print(f"  Total time:   {elapsed:.0f}s")
    print(f"  Total steps:  {global_step}")
    print(f"  Skipped:      {total_skipped} pairs (too long)")

    wandb.finish()

    return sampling_client


def main():
    parser = argparse.ArgumentParser(description="SFT training for Clojure LLM")
    parser.add_argument(
        "--config", default="training/sft/config.yaml",
        help="Path to config YAML"
    )
    parser.add_argument(
        "--limit", type=int, default=None,
        help="Limit number of training pairs"
    )
    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    config = load_config(args.config)
    train(config, limit=args.limit)


if __name__ == "__main__":
    main()
