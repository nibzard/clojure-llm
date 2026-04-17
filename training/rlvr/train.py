#!/usr/bin/env python3
"""
RLVR Training Script for Clojure LLM.

Reinforcement Learning from Verifier Rewards using Pi as the rollout environment.
Uses Tinker's RL training API with custom ClojurePiEnv.

Usage:
    python training/rlvr/train.py --config training/rlvr/config.yaml
"""

import argparse
import json
import os
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, List, Optional

import yaml
import numpy as np

from tinker import ServiceClient, EnvGroupBuilder

from .config import load_rlvr_config
from .env import ClojurePiEnv, ClojureTask, create_task_from_dict
from .pi_client import create_pi_client
from .rewards import compute_advantages, compute_group_statistics, RewardTracker


@dataclass
class RLVRConfig:
    """Configuration for RLVR training."""

    # Model
    model: str = "Qwen3-8B-Base"
    sft_checkpoint: Optional[str] = None

    # Pi
    pi_rpc_url: str = "http://localhost:8080"
    pi_timeout: float = 30.0
    pi_max_retries: int = 3

    # Environment
    max_steps: int = 10
    env_timeout: float = 60.0

    # Rollouts
    rollouts_per_task: int = 8
    num_iterations: int = 10
    tasks_per_batch: int = 4

    # Training
    learning_rate: float = 1.0e-5
    batch_size: int = 8
    max_seq_length: int = 2048

    # GRPO
    kl_penalty_coeff: float = 0.1
    clip_range: float = 0.2

    # Rewards
    reward_weights: Dict[str, float] = None

    # Checkpointing
    save_steps: int = 50
    output_dir: str = "checkpoints/rlvr"

    # Logging
    log_steps: int = 5
    wandb_project: Optional[str] = None

    # Data
    benchmark_tasks: str = "benchmark/tasks-v0.edn"

    def __post_init__(self):
        if self.reward_weights is None:
            self.reward_weights = {
                "eval": 0.1,
                "kondo": 0.2,
                "namespace": 0.1,
                "tests": 0.6,
            }

    @classmethod
    def from_yaml(cls, path: str) -> "RLVRConfig":
        """Load config from YAML file."""
        with open(path) as f:
            data = yaml.safe_load(f)

        model = data.get("model", "Qwen3-8B-Base")
        pi = data.get("pi", {})
        env = data.get("env", {})
        rollouts = data.get("rollouts", {})
        training = data.get("training", {})
        rewards = data.get("rewards", {})
        checkpointing = data.get("checkpointing", {})
        logging = data.get("logging", {})
        data_config = data.get("data", {})

        return cls(
            model=model,
            sft_checkpoint=data.get("sft_checkpoint"),
            pi_rpc_url=pi.get("rpc_url", "http://localhost:8080"),
            pi_timeout=pi.get("timeout_sec", 30.0),
            pi_max_retries=pi.get("max_retries", 3),
            max_steps=env.get("max_steps", 10),
            env_timeout=env.get("timeout_sec", 60.0),
            rollouts_per_task=rollouts.get("rollouts_per_task", 8),
            num_iterations=rollouts.get("num_iterations", 10),
            tasks_per_batch=rollouts.get("tasks_per_batch", 4),
            learning_rate=training.get("learning_rate", 1.0e-5),
            batch_size=training.get("batch_size", 8),
            max_seq_length=training.get("max_seq_length", 2048),
            kl_penalty_coeff=training.get("kl_penalty_coeff", 0.1),
            clip_range=training.get("clip_range", 0.2),
            reward_weights={
                "eval": rewards.get("eval", 0.1),
                "kondo": rewards.get("kondo", 0.2),
                "namespace": rewards.get("namespace", 0.1),
                "tests": rewards.get("tests", 0.6),
            },
            save_steps=checkpointing.get("save_steps", 50),
            output_dir=checkpointing.get("output_dir", "checkpoints/rlvr"),
            log_steps=logging.get("log_steps", 5),
            wandb_project=logging.get("wandb_project"),
            benchmark_tasks=data_config.get("benchmark_tasks", "benchmark/tasks-v0.edn"),
        )


def load_tasks(tasks_path: str) -> List[Dict[str, Any]]:
    """Load benchmark tasks from file."""
    # For simplicity, assume JSON format
    # In production, would handle EDN properly
    with open(tasks_path) as f:
        if tasks_path.endswith(".json"):
            return json.load(f)
        else:
            # EDN placeholder
            return []


def create_clojure_tasks(task_dicts: List[Dict]) -> List[ClojureTask]:
    """Convert benchmark task dicts to ClojureTask instances."""
    return [create_task_from_dict(t) for t in task_dicts]


def run_rollout_group(
    client: ServiceClient,
    env_builder: ClojurePiEnv,
    tasks: List[ClojureTask],
    num_rollouts: int,
) -> List[Dict[str, Any]]:
    """
    Run a group of GRPO rollouts for the given tasks.

    For each task, generates num_rollouts code samples and evaluates them.

    Args:
        client: Tinker ServiceClient for sampling
        env_builder: Environment builder
        tasks: Tasks to rollout
        num_rollouts: Number of samples per task

    Returns:
        List of rollout results with rewards and log probs
    """
    all_results = []

    for task in tasks:
        task_results = []

        for rollout_idx in range(num_rollouts):
            # Reset environment
            env = env_builder.build(task)
            prompt = env.reset()

            # Generate code sample
            sample = client.sample(
                prompt,
                max_tokens=512,
                temperature=0.7,
            )
            code = sample.text
            log_prob = sample.log_prob  # Would come from sample

            # Evaluate in environment
            step_result = env.step(code)

            task_results.append({
                "task_id": task.task_id,
                "code": code,
                "reward": step_result.reward,
                "log_prob": log_prob,
                "done": step_result.done,
                "info": step_result.info,
            })

        all_results.extend(task_results)

    return all_results


def train_rlvr(config: RLVRConfig):
    """
    Main RLVR training loop.

    1. Load SFT checkpoint
    2. Connect to Pi via RPC
    3. For each iteration:
       - Sample batch of tasks
       - For each task, run N rollouts (GRPO group)
       - Compute advantages from group rewards
       - Update policy using advantages
       - Log metrics and checkpoint
    """
    print(f"Starting RLVR training with config:")
    print(f"  Model: {config.model}")
    print(f"  SFT checkpoint: {config.sft_checkpoint}")
    print(f"  Pi RPC URL: {config.pi_rpc_url}")
    print(f"  Rollouts per task: {config.rollouts_per_task}")
    print(f"  Iterations: {config.num_iterations}")

    # Initialize Pi client
    print("\nConnecting to Pi...")
    pi_client = create_pi_client(
        rpc_url=config.pi_rpc_url,
        timeout=config.pi_timeout,
        max_retries=config.pi_max_retries,
        trace_path=os.path.join(config.output_dir, "trace.jsonl"),
    )

    # Load tasks
    print(f"\nLoading tasks from {config.benchmark_tasks}...")
    task_dicts = load_tasks(config.benchmark_tasks)
    tasks = create_clojure_tasks(task_dicts)
    print(f"  Loaded {len(tasks)} tasks")

    # Create environment builder
    from .env import ClojurePiEnvBuilder
    env_builder = ClojurePiEnvBuilder(
        pi_client=pi_client,
        max_steps=config.max_steps,
        reward_weights=config.reward_weights,
    )

    # Initialize Tinker ServiceClient with SFT checkpoint
    print("\nInitializing Tinker ServiceClient...")
    if config.sft_checkpoint and os.path.exists(config.sft_checkpoint):
        client = ServiceClient.load_checkpoint(config.sft_checkpoint)
    else:
        client = ServiceClient(
            model=config.model,
            lora_rank=32,
            lora_alpha=64,
        )

    # Create output directory
    os.makedirs(config.output_dir, exist_ok=True)

    # Reward tracker
    reward_tracker = RewardTracker()

    # Training loop
    print("\nStarting RLVR training loop...")
    global_step = 0

    for iteration in range(config.num_iterations):
        print(f"\n=== Iteration {iteration + 1}/{config.num_iterations} ===")

        # Sample batch of tasks
        batch_size = min(config.tasks_per_batch, len(tasks))
        batch_tasks = np.random.choice(tasks, size=batch_size, replace=False).tolist()

        print(f"  Running rollouts for {len(batch_tasks)} tasks...")

        # Run rollouts for each task
        all_rollouts = run_rollout_group(
            client=client,
            env_builder=env_builder,
            tasks=batch_tasks,
            num_rollouts=config.rollouts_per_task,
        )

        # Group by task for GRPO
        task_groups = {}
        for r in all_rollouts:
            tid = r["task_id"]
            if tid not in task_groups:
                task_groups[tid] = []
            task_groups[tid].append(r)

        # Compute advantages and update
        for task_id, group in task_groups.items():
            # Compute GRPO advantages
            advantages = compute_advantages(
                group,
                kl_penalty_coeff=config.kl_penalty_coeff,
            )

            # Update policy using advantages
            # In Tinker, this would be: client.update_with_rollouts(group, advantages)
            # For placeholder, just log
            group_stats = compute_group_statistics(group)
            print(f"    Task {task_id}: "
                  f"mean_reward={group_stats['mean_reward']:.3f}, "
                  f"pass_rate={group_stats['pass_rate']:.2%}")

            # Record in tracker
            reward_tracker.record_group(iteration, group_stats)

        # Log iteration summary
        iteration_reward = np.mean([r["reward"] for r in all_rollouts])
        iteration_pass_rate = np.mean([r["info"]["tests_pass"] for r in all_rollouts])
        print(f"  Iteration {iteration + 1}: "
              f"reward={iteration_reward:.3f}, "
              f"pass_rate={iteration_pass_rate:.2%}")

        # Checkpoint
        if (iteration + 1) % config.save_steps == 0:
            checkpoint_path = os.path.join(
                config.output_dir,
                f"checkpoint-iter-{iteration + 1}"
            )
            print(f"  Saving checkpoint to {checkpoint_path}")
            client.save_state(checkpoint_path)

    # Save final checkpoint
    final_path = os.path.join(config.output_dir, "final")
    print(f"\nSaving final checkpoint to {final_path}")
    client.save_state(final_path)

    # Save reward tracker summary
    summary_path = os.path.join(config.output_dir, "training_summary.json")
    with open(summary_path, "w") as f:
        json.dump(reward_tracker.get_summary(), f, indent=2)

    # Close Pi client
    pi_client.close()

    print("\nRLVR training complete!")
    print(f"  Final checkpoint: {final_path}")
    print(f"  Training summary: {summary_path}")


def main():
    parser = argparse.ArgumentParser(description="RLVR training for Clojure LLM")
    parser.add_argument(
        "--config",
        type=str,
        default="training/rlvr/config.yaml",
        help="Path to config YAML file"
    )
    parser.add_argument(
        "--sft-checkpoint",
        type=str,
        default=None,
        help="Override SFT checkpoint path"
    )
    parser.add_argument(
        "--pi-url",
        type=str,
        default=None,
        help="Override Pi RPC URL"
    )
    parser.add_argument(
        "--output-dir",
        type=str,
        default=None,
        help="Override output directory"
    )

    args = parser.parse_args()

    # Load config
    config = RLVRConfig.from_yaml(args.config)

    # Apply CLI overrides
    if args.sft_checkpoint:
        config.sft_checkpoint = args.sft_checkpoint
    if args.pi_url:
        config.pi_rpc_url = args.pi_url
    if args.output_dir:
        config.output_dir = args.output_dir

    # Run training
    train_rlvr(config)


if __name__ == "__main__":
    main()
