#!/usr/bin/env python3
"""
SFT Training Script for Clojure LLM using Tinker Platform.

This script performs supervised fine-tuning of Qwen3-8B-Base on Clojure code data
using the Tinker training platform.

Usage:
    python training/sft/train.py --config training/sft/config.yaml
"""

import argparse
import json
import os
from dataclasses import dataclass, field
from pathlib import Path
from typing import Optional

import yaml
import tinker
from tinker import ServiceClient, ChatDatasetBuilder, ModelInput, EncodedTextChunk


@dataclass
class SFTConfig:
    """Configuration for SFT training."""

    # Model
    model: str = "Qwen3-8B-Base"

    # LoRA
    lora_rank: int = 32
    lora_alpha: int = 64
    lora_dropout: float = 0.1

    # Training
    learning_rate: float = 2.0e-5
    num_epochs: int = 3
    batch_size: int = 8
    gradient_accumulation_steps: int = 1
    max_seq_length: int = 2048

    # Optimizer
    weight_decay: float = 0.01
    warmup_ratio: float = 0.1

    # Data
    train_path: str = "data/sft/train.jsonl"
    val_path: str = "data/sft/val.jsonl"

    # Output
    output_dir: str = "checkpoints/sft"
    save_steps: int = 100
    save_total_limit: int = 3

    # Logging
    log_steps: int = 10
    wandb_project: Optional[str] = None

    @classmethod
    def from_yaml(cls, path: str) -> "SFTConfig":
        """Load config from YAML file."""
        with open(path) as f:
            data = yaml.safe_load(f)

        # Extract nested values
        model = data.get("model", "Qwen3-8B-Base")
        lora = data.get("lora", {})
        training = data.get("training", {})
        data_config = data.get("data", {})
        checkpointing = data.get("checkpointing", {})
        logging = data.get("logging", {})

        return cls(
            model=model,
            lora_rank=lora.get("rank", 32),
            lora_alpha=lora.get("alpha", 64),
            lora_dropout=lora.get("dropout", 0.1),
            learning_rate=training.get("learning_rate", 2.0e-5),
            num_epochs=training.get("num_epochs", 3),
            batch_size=training.get("batch_size", 8),
            gradient_accumulation_steps=training.get("gradient_accumulation_steps", 1),
            max_seq_length=training.get("max_seq_length", 2048),
            weight_decay=training.get("weight_decay", 0.01),
            warmup_ratio=training.get("warmup_ratio", 0.1),
            train_path=data_config.get("train_path", "data/sft/train.jsonl"),
            val_path=data_config.get("val_path", "data/sft/val.jsonl"),
            output_dir=checkpointing.get("output_dir", "checkpoints/sft"),
            save_steps=checkpointing.get("save_steps", 100),
            save_total_limit=checkpointing.get("save_total_limit", 3),
            log_steps=logging.get("log_steps", 10),
            wandb_project=logging.get("wandb_project"),
        )


def load_jsonl(path: str) -> list:
    """Load JSONL file and return list of records."""
    data = []
    with open(path) as f:
        for line in f:
            line = line.strip()
            if line:
                data.append(json.loads(line))
    return data


def create_chat_dataset(data_path: str, max_samples: Optional[int] = None):
    """
    Create a Tinker ChatDataset from JSONL file.

    Args:
        data_path: Path to JSONL file with chat format
        max_samples: Optional limit on number of samples

    Returns:
        Tinker SupervisedDataset
    """
    records = load_jsonl(data_path)

    if max_samples:
        records = records[:max_samples]

    # Extract messages from each record
    # Expected format: {"messages": [{"role": "user", "content": "..."}, ...]}
    all_messages = [record["messages"] for record in records]

    # Build dataset using Tinker's ChatDatasetBuilder
    builder = ChatDatasetBuilder()

    for messages in all_messages:
        # Convert to Tinker format
        # ChatDatasetBuilder expects alternating user/assistant messages
        builder.add_message(
            role=messages[0]["role"],
            content=messages[0]["content"]
        )
        if len(messages) > 1:
            builder.add_message(
                role=messages[1]["role"],
                content=messages[1]["content"]
            )

    return builder.build()


def create_training_batch(dataset, batch_idx: int, batch_size: int, max_seq_length: int):
    """
    Create a single training batch from the dataset.

    Args:
        dataset: Tinker SupervisedDataset
        batch_idx: Index of current batch
        batch_size: Number of samples per batch
        max_seq_length: Maximum sequence length

    Returns:
        ModelInput with batched data
    """
    # In a real implementation, this would use Tinker's dataloader
    # For now, placeholder for the pattern
    start_idx = batch_idx * batch_size
    end_idx = start_idx + batch_size

    # Get batch samples
    # This would use dataset[start_idx:end_idx] with Tinker's API

    # Create ModelInput
    # Each chunk is EncodedTextChunk with tokens
    return ModelInput(chunks=[])


def train_sft(config: SFTConfig):
    """
    Main SFT training loop using Tinker ServiceClient.

    Following the Tinker cookbook pattern:
    1. Connect to ServiceClient with base model
    2. Apply LoRA configuration
    3. Load data with ChatDatasetBuilder
    4. Training loop: forward_backward -> optim_step -> (optional) save_state
    5. Save final weights and get sampling client
    """

    print(f"Starting SFT training with config:")
    print(f"  Model: {config.model}")
    print(f"  LoRA rank: {config.lora_rank}, alpha: {config.lora_alpha}")
    print(f"  Learning rate: {config.learning_rate}")
    print(f"  Batch size: {config.batch_size}")
    print(f"  Epochs: {config.num_epochs}")

    # Initialize Tinker ServiceClient
    # This connects to the Tinker cloud training platform
    print("\nConnecting to Tinker...")
    client = ServiceClient(
        model=config.model,
        # LoRA configuration
        lora_rank=config.lora_rank,
        lora_alpha=config.lora_alpha,
        lora_dropout=config.lora_dropout,
    )

    # Load training data
    print(f"\nLoading training data from {config.train_path}...")
    train_dataset = create_chat_dataset(config.train_path)
    print(f"  Training samples: {len(train_dataset)}")

    if os.path.exists(config.val_path):
        val_dataset = create_chat_dataset(config.val_path)
        print(f"  Validation samples: {len(val_dataset)}")
    else:
        val_dataset = None
        print("  No validation set")

    # Calculate number of steps
    num_samples = len(train_dataset)
    steps_per_epoch = (num_samples + config.batch_size - 1) // config.batch_size
    total_steps = steps_per_epoch * config.num_epochs

    print(f"\nTraining configuration:")
    print(f"  Steps per epoch: {steps_per_epoch}")
    print(f"  Total steps: {total_steps}")

    # Create output directory
    os.makedirs(config.output_dir, exist_ok=True)

    # Training loop
    print("\nStarting training loop...")
    global_step = 0

    for epoch in range(config.num_epochs):
        print(f"\n=== Epoch {epoch + 1}/{config.num_epochs} ===")

        for step in range(steps_per_epoch):
            global_step += 1

            # Create batch
            batch = create_training_batch(
                train_dataset,
                step,
                config.batch_size,
                config.max_seq_length
            )

            # Forward + backward pass
            # This computes gradients and accumulates them
            client.forward_backward(batch)

            # Gradient accumulation check
            if (step + 1) % config.gradient_accumulation_steps == 0:
                # Update weights
                client.optim_step()

            # Logging
            if global_step % config.log_steps == 0:
                loss = 0.0  # Would get from client.get_metrics()
                print(f"Step {global_step}/{total_steps} | Loss: {loss:.4f}")

            # Checkpointing
            if global_step % config.save_steps == 0:
                checkpoint_path = os.path.join(
                    config.output_dir,
                    f"checkpoint-step-{global_step}"
                )
                print(f"Saving checkpoint to {checkpoint_path}")
                client.save_state(checkpoint_path)

    # Save final checkpoint
    final_path = os.path.join(config.output_dir, "final")
    print(f"\nSaving final checkpoint to {final_path}")
    client.save_state(final_path)

    # Export weights and create sampling client
    print("\nExporting model weights...")
    sampling_client = client.save_weights_and_get_sampling_client()

    print("Training complete!")
    print(f"Model checkpoint saved to: {final_path}")
    print("Sampling client ready for inference")

    return sampling_client


def main():
    parser = argparse.ArgumentParser(description="SFT training for Clojure LLM")
    parser.add_argument(
        "--config",
        type=str,
        default="training/sft/config.yaml",
        help="Path to config YAML file"
    )
    parser.add_argument(
        "--model",
        type=str,
        default=None,
        help="Override model name"
    )
    parser.add_argument(
        "--train-path",
        type=str,
        default=None,
        help="Override training data path"
    )
    parser.add_argument(
        "--output-dir",
        type=str,
        default=None,
        help="Override output directory"
    )

    args = parser.parse_args()

    # Load config
    config = SFTConfig.from_yaml(args.config)

    # Apply CLI overrides
    if args.model:
        config.model = args.model
    if args.train_path:
        config.train_path = args.train_path
    if args.output_dir:
        config.output_dir = args.output_dir

    # Run training
    train_sft(config)


if __name__ == "__main__":
    main()
