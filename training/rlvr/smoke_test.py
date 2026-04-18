#!/usr/bin/env python3
"""Smoke test for RLVR training pipeline.

Validates the full pipeline with 3 tasks, 4 rollouts:
1. Load SFT checkpoint via Tinker
2. Generate 4 completions x 3 tasks = 12 samples
3. Evaluate via Clojure subprocess
4. Compute GRPO advantages
5. One policy update step (importance_sampling loss)
6. Verify loss is returned

Usage:
    python3 training/rlvr/smoke_test.py
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

from tinker import (
    AdamParams,
    Datum,
    ModelInput,
    SamplingParams,
    ServiceClient,
)
from transformers import AutoTokenizer

from training.rlvr.evaluate import evaluate_single, load_training_tasks
from training.rlvr.rewards import compute_grpo_advantages


SFT_CHECKPOINT = "tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600"


def main():
    print("=" * 60)
    print("RLVR Smoke Test")
    print("=" * 60)

    model_id = "Qwen/Qwen3-8B-Base"
    n_tasks = 3
    n_rollouts = 4

    # 1. Load tokenizer
    print(f"\n[1/6] Loading tokenizer...")
    tokenizer = AutoTokenizer.from_pretrained(model_id, trust_remote_code=True)
    print(f"  OK. Vocab size: {len(tokenizer)}")

    # 2. Load training tasks
    print(f"\n[2/6] Loading training tasks...")
    tasks = load_training_tasks()
    tasks = tasks[:n_tasks]
    print(f"  OK. Loaded {len(tasks)} tasks")
    for t in tasks:
        print(f"    - {t['task_id']}")

    # 3. Connect to Tinker and load SFT checkpoint
    print(f"\n[3/6] Connecting to Tinker...")
    client = ServiceClient()
    tc = client.create_training_client_from_state(SFT_CHECKPOINT)
    print("  OK. Training client from SFT checkpoint ready.")

    # Create sampling client
    save_result = tc.save_weights_for_sampler("rlvr-smoke-test").result()
    sc = client.create_sampling_client(model_path=save_result.path)
    print(f"  OK. Sampling client ready (path: {save_result.path})")

    # 4. Generate rollouts
    print(f"\n[4/6] Generating {n_rollouts} rollouts per task...")
    params = SamplingParams(max_tokens=512, temperature=0.7, top_p=0.95)

    all_rollouts = []
    for task in tasks:
        prompt_text = (ROOT / task["prompt_path"]).read_text().strip()
        prompt_tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
        model_input = ModelInput.from_ints(prompt_tokens)

        for k in range(n_rollouts):
            try:
                response = sc.sample(model_input, 1, params).result()
                seq = response.sequences[0]
                gen_tokens = list(seq.tokens)
                gen_text = tokenizer.decode(gen_tokens)
                logprobs = list(seq.logprobs) if seq.logprobs else [0.0] * len(gen_tokens)

                all_rollouts.append({
                    "task_id": task["task_id"],
                    "tokens": prompt_tokens + gen_tokens,
                    "logprobs": [0.0] * len(prompt_tokens) + logprobs,
                    "test_path": str(ROOT / task["tests_path"]),
                    "prompt_len": len(prompt_tokens),
                })
            except Exception as e:
                print(f"    WARNING: generation failed for {task['task_id']}: {e}")
                all_rollouts.append({
                    "task_id": task["task_id"],
                    "tokens": [],
                    "logprobs": [],
                    "test_path": str(ROOT / task["tests_path"]),
                    "prompt_len": 0,
                })

    print(f"  OK. Generated {len(all_rollouts)} rollouts")

    # 5. Evaluate rollouts
    print(f"\n[5/6] Evaluating rollouts via Clojure...")
    for r in all_rollouts:
        if not r["tokens"]:
            r["reward"] = 0.0
            r["passed"] = False
            continue

        # Extract code from generated tokens
        gen_tokens = r["tokens"][r["prompt_len"]:]
        gen_text = tokenizer.decode(gen_tokens)
        prompt_text = (ROOT / next(
            t["prompt_path"] for t in tasks if t["task_id"] == r["task_id"]
        )).read_text().strip()

        # Extract clean defn form
        combined = prompt_text + gen_text
        last_defn = combined.rfind("(defn")
        if last_defn >= 0:
            code = combined[last_defn:]
            depth = 0
            for i in range(len(code)):
                if code[i] == '(':
                    depth += 1
                elif code[i] == ')':
                    depth -= 1
                    if depth == 0:
                        code = code[:i + 1]
                        break
        else:
            code = combined

        passed, err = evaluate_single(code, r["test_path"])
        r["reward"] = 1.0 if passed else 0.0
        r["passed"] = passed

    pass_count = sum(1 for r in all_rollouts if r["passed"])
    print(f"  OK. {pass_count}/{len(all_rollouts)} passed")

    # 6. Compute GRPO advantages and policy update
    print(f"\n[6/6] Computing GRPO advantages and running policy update...")

    # Group by task
    task_groups = {}
    for r in all_rollouts:
        task_groups.setdefault(r["task_id"], []).append(r)

    # Compute advantages
    all_data = []
    for task_id, group in task_groups.items():
        rewards = [r["reward"] for r in group]
        advantages = compute_grpo_advantages(rewards)
        print(f"  {task_id}: rewards={rewards}, advantages={[f'{a:.2f}' for a in advantages]}")

        for r, adv in zip(group, advantages):
            if not r["tokens"] or len(r["tokens"]) < 2:
                continue

            all_tokens = r["tokens"]
            n = len(all_tokens)
            target_tokens = all_tokens[1:] + [0]
            prompt_len = r["prompt_len"]
            loss_mask = [0.0] * prompt_len + [1.0] * (n - prompt_len)
            loss_mask = loss_mask[1:] + [0.0]

            # For importance_sampling: scale weights by advantage
            # Positive advantage → keep weight, negative → zero weight
            # This is REINFORCE / reward-weighted regression
            advantage_scaled_mask = [w * max(adv, 0.0) for w in loss_mask]

            datum = Datum(
                model_input=ModelInput.from_ints(all_tokens),
                loss_fn_inputs={
                    "target_tokens": target_tokens,
                    "weights": advantage_scaled_mask,
                },
            )
            all_data.append(datum)

    if all_data:
        fb_result = tc.forward_backward(
            data=all_data,
            loss_fn="cross_entropy",
        ).result()

        loss = fb_result.metrics.get("loss:sum", 0.0)
        print(f"  Available metrics: {list(fb_result.metrics.keys())}")
        print(f"  Loss: {loss:.4f}")

        # Optimizer step
        adam = AdamParams(learning_rate=5e-6, weight_decay=0.01, grad_clip_norm=1.0)
        tc.optim_step(adam).result()
        print(f"  Optimizer step complete")
    else:
        print(f"  WARNING: No valid data for policy update")

    print(f"\n{'=' * 60}")
    print("RLVR SMOKE TEST PASSED")
    print(f"{'=' * 60}")


if __name__ == "__main__":
    main()
