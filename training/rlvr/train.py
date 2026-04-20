#!/usr/bin/env python3
"""
RLVR Training Script for Clojure LLM — GRPO on Tinker.

Reinforcement Learning from Verifier Rewards using Clojure subprocess evaluation.
Uses Tinker's real RL training API with importance_sampling loss for GRPO.

Training loop:
  1. Load SFT checkpoint via create_training_client_from_state
  2. For each iteration:
     a. Sample batch of tasks from training set
     b. Generate K completions per task (temperature=0.7) with logprobs
     c. Evaluate each completion via Clojure subprocess (binary reward)
     d. Compute GRPO advantages within each task group
     e. Policy update via forward_backward("importance_sampling")
     f. optim_step with AdamParams
     g. Checkpoint

Usage:
    python3 training/rlvr/train.py --config training/rlvr/config.yaml
    python3 training/rlvr/train.py --config training/rlvr/config.yaml --iterations 2 --smoke
"""

import argparse
import json
import math
import os
import random
import sys
import time
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

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

# Import our own modules
sys.path.insert(0, str(ROOT))
from training.rlvr.evaluate import (
    compute_reward,
    evaluate_single,
    evaluate_single_detailed,
    load_training_tasks,
)
from training.rlvr.rewards import (
    compute_group_statistics,
    RewardTracker,
)


# ── Config ────────────────────────────────────────────────────────────────────


@dataclass
class RLVRConfig:
    """Configuration for RLVR training."""

    # Model
    model: str = "Qwen3-8B-Base"
    sft_checkpoint: str = ""

    # Rollouts
    rollouts_per_task: int = 8
    num_iterations: int = 10
    tasks_per_batch: int = 30
    temperature: float = 0.7
    max_tokens: int = 512
    refresh_sampler_every_iteration: bool = True

    # Training
    learning_rate: float = 5.0e-6
    weight_decay: float = 0.01
    grad_clip_norm: float = 1.0

    # GRPO
    kl_penalty_coeff: float = 0.1
    clip_range: float = 0.2

    # Checkpointing
    save_steps: int = 2
    output_dir: str = "checkpoints/rlvr"

    # Logging
    wandb_project: str = "clojure-llm"
    wandb_entity: str = "nibzard-org"

    # Data
    training_tasks: str = "data/sft/train_task_ids.json"
    heldout_tasks: str = "data/sft/heldout_task_ids.json"
    benchmark_tasks: str = "benchmark/tasks-v0.edn"

    # Reward
    reward_mode: str = "shaped"
    reward_weights: Optional[Dict[str, float]] = None

    @classmethod
    def from_yaml(cls, path: str) -> "RLVRConfig":
        with open(path) as f:
            data = yaml.safe_load(f)

        rollouts = data.get("rollouts", {})
        training = data.get("training", {})
        ckpt = data.get("checkpointing", {})
        log = data.get("logging", {})

        return cls(
            model=data.get("model", "Qwen3-8B-Base"),
            sft_checkpoint=data.get("sft_checkpoint", ""),
            rollouts_per_task=rollouts.get("rollouts_per_task", 8),
            num_iterations=rollouts.get("num_iterations", 10),
            tasks_per_batch=rollouts.get("tasks_per_batch", 30),
            temperature=rollouts.get("temperature", 0.7),
            max_tokens=rollouts.get("max_tokens", 512),
            refresh_sampler_every_iteration=rollouts.get("refresh_sampler_every_iteration", True),
            learning_rate=training.get("learning_rate", 5.0e-6),
            weight_decay=training.get("weight_decay", 0.01),
            grad_clip_norm=training.get("grad_clip_norm", 1.0),
            kl_penalty_coeff=training.get("kl_penalty_coeff", 0.1),
            clip_range=training.get("clip_range", 0.2),
            save_steps=ckpt.get("save_steps", 2),
            output_dir=ckpt.get("output_dir", "checkpoints/rlvr"),
            wandb_project=log.get("wandb_project", "clojure-llm"),
            wandb_entity=log.get("wandb_entity", "nibzard-org"),
            reward_mode=training.get("reward_mode", "shaped"),
            reward_weights=training.get("reward_weights"),
        )


# ── Rollout generation ────────────────────────────────────────────────────────


@dataclass
class RolloutResult:
    """A single rollout (completion) with its evaluation result."""

    task_id: str
    code: str
    tokens: List[int]
    logprobs: List[float]
    reward: float
    passed: bool
    reward_components: Optional[Dict[str, float]] = None


def generate_rollouts(
    sc,
    tokenizer,
    tasks: List[Dict],
    rollouts_per_task: int,
    temperature: float,
    max_tokens: int,
) -> List[RolloutResult]:
    """Generate K completions per task using Tinker sampling client.

    Args:
        sc: Tinker sampling client.
        tokenizer: HuggingFace tokenizer.
        tasks: List of task dicts with prompt_path.
        rollouts_per_task: K completions per task.
        temperature: Sampling temperature.
        max_tokens: Max tokens per completion.

    Returns:
        List of RolloutResult objects.
    """
    from tinker import SamplingParams

    all_rollouts = []

    for task in tasks:
        prompt_path = ROOT / task["prompt_path"]
        prompt_text = prompt_path.read_text().strip()

        # Tokenize prompt
        prompt_tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
        model_input = ModelInput.from_ints(prompt_tokens)

        params = SamplingParams(
            max_tokens=max_tokens,
            temperature=temperature,
            top_p=0.95,
        )

        for k in range(rollouts_per_task):
            try:
                response = sc.sample(model_input, 1, params).result()
                seq = response.sequences[0]

                generated_tokens = list(seq.tokens)
                generated_text = tokenizer.decode(generated_tokens)

                # Extract logprobs (Tinker returns them as Optional[List[float]])
                logprobs = list(seq.logprobs) if seq.logprobs else [0.0] * len(generated_tokens)

                # Build full code: prompt + generated
                code = _extract_code(prompt_text, generated_text)

                all_rollouts.append(RolloutResult(
                    task_id=task["task_id"],
                    code=code,
                    tokens=prompt_tokens + generated_tokens,
                    logprobs=[0.0] * len(prompt_tokens) + logprobs,
                    reward=0.0,  # Will be filled by evaluation
                    passed=False,
                    reward_components=None,
                ))

            except Exception as e:
                # Failed generation → zero reward
                all_rollouts.append(RolloutResult(
                    task_id=task["task_id"],
                    code=f";; Generation failed: {e}",
                    tokens=[],
                    logprobs=[],
                    reward=0.0,
                    passed=False,
                    reward_components=None,
                ))

    return all_rollouts


def _extract_code(prompt_text: str, generated_text: str) -> str:
    """Extract clean Clojure defn form from prompt + generated text."""
    combined = prompt_text + generated_text

    # Find the LAST (defn form (model may regenerate the signature)
    last_defn = combined.rfind("(defn")
    if last_defn >= 0:
        candidate = combined[last_defn:]
        # Find matching closing paren
        depth = 0
        for i in range(len(candidate)):
            if candidate[i] == '(':
                depth += 1
            elif candidate[i] == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1]
        return candidate

    return combined


# ── Evaluation ────────────────────────────────────────────────────────────────


def evaluate_rollouts(
    rollouts: List[RolloutResult],
    tasks: List[Dict],
    timeout: int = 10,
    reward_mode: str = "binary",
    reward_weights: Optional[Dict[str, float]] = None,
) -> List[RolloutResult]:
    """Evaluate rollouts via Clojure subprocess.

    For each rollout, loads the candidate code + test file and checks if tests pass.
    Sets rollout.reward to 1.0 (pass) or 0.0 (fail) and rollout.passed accordingly.

    Args:
        rollouts: List of RolloutResult objects (reward not yet set).
        tasks: List of task dicts with tests_path.
        timeout: Per-evaluation timeout in seconds.

    Returns:
        Same rollouts with reward and passed fields set.
    """
    # Build test path lookup
    test_paths = {t["task_id"]: str(ROOT / t["tests_path"]) for t in tasks}

    for rollout in rollouts:
        test_path = test_paths.get(rollout.task_id)
        if not test_path or not os.path.exists(test_path):
            rollout.reward = 0.0
            rollout.passed = False
            continue

        if reward_mode == "shaped":
            details = evaluate_single_detailed(rollout.code, test_path, timeout=timeout)
            rollout.reward = compute_reward(details, reward_weights)
            rollout.passed = bool(details["passed"])
            rollout.reward_components = {
                "syntax": 1.0 if details["syntax_ok"] else 0.0,
                "kondo": 1.0 if details["kondo_ok"] else 0.0,
                "load": 1.0 if details["load_ok"] else 0.0,
                "tests": 1.0 if details["tests_ok"] else 0.0,
            }
        else:
            passed, _ = evaluate_single(rollout.code, test_path, timeout=timeout)
            rollout.reward = 1.0 if passed else 0.0
            rollout.passed = passed
            rollout.reward_components = {
                "syntax": 1.0 if passed else 0.0,
                "kondo": 1.0 if passed else 0.0,
                "load": 1.0 if passed else 0.0,
                "tests": 1.0 if passed else 0.0,
            }

    return rollouts


# ── GRPO advantages ───────────────────────────────────────────────────────────


def compute_grpo_advantages(
    group: List[RolloutResult],
) -> List[float]:
    """Compute GRPO advantages for a group of rollouts (same task).

    advantage_i = (reward_i - mean(rewards)) / std(rewards)

    If all rewards are the same (std=0), advantages are all 0.0.

    Args:
        group: List of RolloutResult for the same task.

    Returns:
        List of advantage values.
    """
    rewards = np.array([r.reward for r in group])
    mean_r = np.mean(rewards)
    std_r = np.std(rewards)

    if std_r < 1e-8:
        return [0.0] * len(group)

    advantages = (rewards - mean_r) / std_r
    return advantages.tolist()


# ── Policy update ─────────────────────────────────────────────────────────────


def policy_update(
    tc,
    rollouts: List[RolloutResult],
    advantages: List[float],
) -> float:
    """Perform a single policy update step via Tinker's forward_backward.

    Uses cross_entropy loss with advantage-scaled weights:
    - Positive advantage samples get weight = advantage (REINFORCE)
    - Negative advantage samples get weight = 0 (filtered out)
    - This is valid reward-weighted regression

    Args:
        tc: Tinker training client.
        rollouts: RolloutResult objects with tokens and logprobs.
        advantages: One scalar advantage per rollout.

    Returns:
        Loss value from forward_backward.
    """
    data = []

    for rollout, advantage in zip(rollouts, advantages):
        if not rollout.tokens:
            continue

        all_tokens = rollout.tokens
        n = len(all_tokens)

        if n < 2:
            continue

        # Target tokens: shifted by 1
        target_tokens = all_tokens[1:] + [0]

        # Loss mask: 0 for prompt, 1 for generated
        # Count prompt length from leading zero logprobs
        prompt_len = 0
        for lp in rollout.logprobs:
            if abs(lp) < 1e-10:
                prompt_len += 1
            else:
                break

        loss_mask = [0.0] * prompt_len + [1.0] * (n - prompt_len)
        # Shift to align with target
        loss_mask = loss_mask[1:] + [0.0]

        # Scale weights by advantage (REINFORCE / reward-weighted regression)
        # Only train on positive-advantage samples; zero out negative ones
        scaled_mask = [w * max(advantage, 0.0) for w in loss_mask]

        datum = Datum(
            model_input=ModelInput.from_ints(all_tokens),
            loss_fn_inputs={
                "target_tokens": target_tokens,
                "weights": scaled_mask,
            },
        )
        data.append(datum)

    if not data:
        return 0.0

    # Forward + backward with cross_entropy loss
    fb_result = tc.forward_backward(
        data=data,
        loss_fn="cross_entropy",
    ).result()

    loss = fb_result.metrics.get("loss:sum", 0.0)
    return loss


# ── Main training loop ────────────────────────────────────────────────────────


def train(config: RLVRConfig, smoke: bool = False):
    """Main RLVR training loop.

    Args:
        config: Training configuration.
        smoke: If True, run minimal smoke test (3 tasks, 4 rollouts, 1 iteration).
    """
    tinker_model = f"Qwen/{config.model}"

    wandb.init(
        entity=config.wandb_entity,
        project=config.wandb_project,
        name=f"rlvr-{config.model}-grpo",
        config={
            "model": tinker_model,
            "sft_checkpoint": config.sft_checkpoint,
            "rollouts_per_task": config.rollouts_per_task,
            "num_iterations": config.num_iterations,
            "tasks_per_batch": config.tasks_per_batch,
            "temperature": config.temperature,
            "max_tokens": config.max_tokens,
            "refresh_sampler_every_iteration": config.refresh_sampler_every_iteration,
            "learning_rate": config.learning_rate,
            "weight_decay": config.weight_decay,
            "kl_penalty_coeff": config.kl_penalty_coeff,
            "clip_range": config.clip_range,
            "reward_mode": config.reward_mode,
            "reward_weights": config.reward_weights,
        },
    )

    print("=" * 60)
    print("RLVR Training — GRPO on Tinker")
    print("=" * 60)
    print(f"  Model:            {tinker_model}")
    print(f"  SFT checkpoint:   {config.sft_checkpoint}")
    print(f"  Rollouts/task:    {config.rollouts_per_task}")
    print(f"  Iterations:       {config.num_iterations}")
    print(f"  Tasks/batch:      {config.tasks_per_batch}")
    print(f"  Temperature:      {config.temperature}")
    print(f"  Reward mode:      {config.reward_mode}")
    print(f"  Learning rate:    {config.learning_rate}")
    print(f"  wandb:            {config.wandb_entity}/{config.wandb_project}")

    # ── Load tasks ─────────────────────────────────────────────────────────

    print("\nLoading training tasks...")
    tasks = load_training_tasks(
        config.training_tasks,
        config.benchmark_tasks,
    )
    print(f"  Total training tasks: {len(tasks)}")

    if smoke:
        tasks = tasks[:3]
        config.rollouts_per_task = 4
        config.num_iterations = 1
        config.tasks_per_batch = 3
        print(f"  SMOKE MODE: {len(tasks)} tasks, {config.rollouts_per_task} rollouts, {config.num_iterations} iteration")

    # ── Connect to Tinker ──────────────────────────────────────────────────

    print("\nConnecting to Tinker...")
    client = ServiceClient()

    # Load SFT checkpoint
    print(f"Loading SFT checkpoint: {config.sft_checkpoint}")
    tc = client.create_training_client_from_state(config.sft_checkpoint)
    print("  Training client ready.")

    # Create sampling client from current weights
    save_result = tc.save_weights_for_sampler("rlvr-train-current").result()
    sc = client.create_sampling_client(model_path=save_result.path)
    print(f"  Sampling client ready (path: {save_result.path})")

    # ── Load tokenizer ────────────────────────────────────────────────────

    print("\nLoading tokenizer...")
    tokenizer = AutoTokenizer.from_pretrained(tinker_model, trust_remote_code=True)
    if tokenizer.pad_token is None:
        tokenizer.pad_token = tokenizer.eos_token
    print(f"  Vocab size: {len(tokenizer)}")

    # ── Setup ──────────────────────────────────────────────────────────────

    os.makedirs(config.output_dir, exist_ok=True)
    tracker = RewardTracker()
    adam = AdamParams(
        learning_rate=config.learning_rate,
        weight_decay=config.weight_decay,
        grad_clip_norm=config.grad_clip_norm,
    )

    # ── Training loop ─────────────────────────────────────────────────────

    start_time = time.time()

    for iteration in range(config.num_iterations):
        iter_start = time.time()
        print(f"\n{'='*60}")
        print(f"Iteration {iteration + 1}/{config.num_iterations}")
        print(f"{'='*60}")

        # 1. Sample batch of tasks
        batch_size = min(config.tasks_per_batch, len(tasks))
        batch_tasks = random.sample(tasks, batch_size)
        print(f"  Sampled {len(batch_tasks)} tasks")

        # 2. Generate rollouts
        print(f"  Generating {config.rollouts_per_task} rollouts per task...")
        rollouts = generate_rollouts(
            sc, tokenizer, batch_tasks,
            config.rollouts_per_task,
            config.temperature,
            config.max_tokens,
        )
        print(f"  Generated {len(rollouts)} rollouts")

        # 3. Evaluate rollouts
        print(f"  Evaluating rollouts via Clojure...")
        rollouts = evaluate_rollouts(
            rollouts,
            batch_tasks,
            reward_mode=config.reward_mode,
            reward_weights=config.reward_weights,
        )
        pass_count = sum(1 for r in rollouts if r.passed)
        mean_reward = np.mean([r.reward for r in rollouts])
        print(f"  Results: {pass_count}/{len(rollouts)} passed ({mean_reward:.1%})")

        # 4. Group by task and compute GRPO advantages
        task_groups = {}
        for r in rollouts:
            task_groups.setdefault(r.task_id, []).append(r)

        all_advantages = {}
        for task_id, group in task_groups.items():
            advs = compute_grpo_advantages(group)
            for rollout, adv in zip(group, advs):
                all_advantages[id(rollout)] = adv

            stats = compute_group_statistics([
                {"reward": r.reward, "tests_pass": r.passed}
                for r in group
            ])
            tracker.record_group(iteration, stats)
            print(f"    {task_id}: reward={stats['mean_reward']:.2f}, "
                  f"pass_rate={stats['pass_rate']:.0%}")

        # 5. Policy update
        advantages_ordered = [all_advantages[id(r)] for r in rollouts]
        print(f"  Running policy update ({len(rollouts)} samples)...")

        loss = policy_update(tc, rollouts, advantages_ordered)
        print(f"  Loss: {loss:.4f}")

        wandb.log({
            "rlvr/loss": loss,
            "rlvr/mean_reward": mean_reward,
            "rlvr/pass_rate": pass_count / max(len(rollouts), 1),
            "rlvr/rollouts_total": len(rollouts),
        }, step=iteration)

        # 6. Optimizer step
        tc.optim_step(adam).result()

        # 7. Refresh sampling client with updated weights
        if config.refresh_sampler_every_iteration or (iteration + 1) % 2 == 0:
            save_result = tc.save_weights_for_sampler(
                f"rlvr-train-iter-{iteration + 1}"
            ).result()
            sc = client.create_sampling_client(model_path=save_result.path)
            print(f"  Refreshed sampling client")

        # 8. Checkpoint
        if (iteration + 1) % config.save_steps == 0:
            ckpt_name = f"checkpoint-iter-{iteration + 1}"
            print(f"  Saving checkpoint: {ckpt_name}")
            tc.save_state(ckpt_name).result()

        iter_elapsed = time.time() - iter_start
        print(f"  Iteration time: {iter_elapsed:.1f}s")

    # ── Final checkpoint ──────────────────────────────────────────────────

    print(f"\nSaving final checkpoint...")
    sampling_client = tc.save_weights_and_get_sampling_client(
        name="rlvr-clojure-final"
    )
    print("  Final model saved. Sampling client ready.")

    # Save training summary
    summary_path = os.path.join(config.output_dir, "training_summary.json")
    summary = tracker.get_summary()
    summary["total_time_s"] = time.time() - start_time
    summary["total_iterations"] = config.num_iterations
    summary["model"] = config.model
    summary["sft_checkpoint"] = config.sft_checkpoint
    with open(summary_path, "w") as f:
        json.dump(summary, f, indent=2)

    print(f"\nTraining complete!")
    print(f"  Time:       {summary['total_time_s']:.0f}s")
    print(f"  Final pass: {summary.get('final_pass_rate', 0):.1%}")
    print(f"  Summary:    {summary_path}")

    wandb.finish()

    return sampling_client


# ── CLI ───────────────────────────────────────────────────────────────────────


def main():
    parser = argparse.ArgumentParser(description="RLVR training for Clojure LLM")
    parser.add_argument(
        "--config",
        default="training/rlvr/config.yaml",
        help="Path to config YAML file",
    )
    parser.add_argument(
        "--sft-checkpoint",
        default=None,
        help="Override SFT checkpoint path",
    )
    parser.add_argument(
        "--iterations",
        type=int,
        default=None,
        help="Override number of iterations",
    )
    parser.add_argument(
        "--output-dir",
        default=None,
        help="Override output directory",
    )
    parser.add_argument(
        "--smoke",
        action="store_true",
        help="Smoke test: 3 tasks, 4 rollouts, 1 iteration",
    )

    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    config = RLVRConfig.from_yaml(args.config)

    # Apply CLI overrides
    if args.sft_checkpoint:
        config.sft_checkpoint = args.sft_checkpoint
    if args.iterations:
        config.num_iterations = args.iterations
    if args.output_dir:
        config.output_dir = args.output_dir

    train(config, smoke=args.smoke)


if __name__ == "__main__":
    main()
