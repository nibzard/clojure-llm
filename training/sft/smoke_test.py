#!/usr/bin/env python3
"""Smoke test for SFT training pipeline.

Validates the full pipeline with 10 samples, 3 steps:
- Tokenizer loads
- Tinker connects
- LoRA client created
- forward_backward + optim_step works
- Loss is returned

Usage:
    python3 training/sft/smoke_test.py
"""

import json
import os
import sys
import time
from pathlib import Path

from dotenv import load_dotenv

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))

load_dotenv(ROOT / ".env")

from tinker import AdamParams, Datum, ModelInput, ServiceClient
from transformers import AutoTokenizer


def main():
    print("=" * 50)
    print("SFT Smoke Test")
    print("=" * 50)

    model_id = "Qwen/Qwen3-8B-Base"

    # 1. Load tokenizer
    print("\n[1/5] Loading tokenizer...")
    tokenizer = AutoTokenizer.from_pretrained(model_id, trust_remote_code=True)
    print(f"  OK. Vocab size: {len(tokenizer)}")

    # 2. Load a few training pairs
    print("\n[2/5] Loading training data...")
    data_path = ROOT / "data" / "sft" / "sft_train.jsonl"
    records = []
    with open(data_path) as f:
        for line in f:
            if len(records) >= 10:
                break
            records.append(json.loads(line))
    print(f"  OK. Loaded {len(records)} pairs")

    # 3. Tokenize and create Datum objects
    print("\n[3/5] Creating training data...")
    data = []
    for rec in records:
        messages = rec["messages"]
        prompt_ids = tokenizer.encode(messages[0]["content"], add_special_tokens=True)
        response_ids = tokenizer.encode(messages[1]["content"], add_special_tokens=False)
        all_ids = prompt_ids + response_ids
        target_tokens = all_ids[1:] + [0]
        loss_mask = [0] * len(prompt_ids) + [1] * len(response_ids)
        loss_mask = loss_mask[1:] + [0]

        datum = Datum(
            model_input=ModelInput.from_ints(all_ids),
            loss_fn_inputs={
                "target_tokens": target_tokens,
                "weights": loss_mask,
            },
        )
        data.append(datum)
    print(f"  OK. Created {len(data)} Datum objects")
    print(f"  Sample token lengths: {[d.model_input.length for d in data[:3]]}")

    # 4. Connect to Tinker and create LoRA client
    print("\n[4/5] Connecting to Tinker...")
    service_client = ServiceClient()
    training_client = service_client.create_lora_training_client(
        base_model=model_id,
        rank=32,
    )
    print("  OK. LoRA training client created")

    # 5. Run 3 training steps
    print("\n[5/5] Running 3 training steps...")
    adam = AdamParams(learning_rate=2e-5, weight_decay=0.01, grad_clip_norm=1.0)

    for step in range(3):
        # Use a small batch (3 samples)
        batch = data[step * 3:(step + 1) * 3]
        if not batch:
            break

        fb_result = training_client.forward_backward(
            data=batch,
            loss_fn="cross_entropy",
        ).result()

        loss = fb_result.metrics.get("loss:sum", 0.0)
        if step == 0:
            print(f"  Available metrics: {list(fb_result.metrics.keys())}")
            print(f"  loss_fn_output_type: {fb_result.loss_fn_output_type}")
            if fb_result.loss_fn_outputs:
                print(f"  loss_fn_outputs[0] keys: {list(fb_result.loss_fn_outputs[0].keys())}")

        training_client.optim_step(adam).result()
        print(f"  Step {step + 1}: loss={loss:.4f}")

    print("\n" + "=" * 50)
    print("SMOKE TEST PASSED")
    print("=" * 50)


if __name__ == "__main__":
    main()
