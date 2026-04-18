"""
Reward computation and GRPO advantage calculation for RLVR training.

Provides:
- Binary reward: 1.0 (tests pass) or 0.0 (fail)
- GRPO advantage normalization within task groups
- RewardTracker for training metrics
"""

from typing import Any, Dict, List

import numpy as np


def compute_grpo_advantages(rewards: List[float]) -> List[float]:
    """Compute GRPO advantages for a group of rollouts (same task).

    advantage_i = (reward_i - mean(rewards)) / std(rewards)

    If all rewards are the same (std=0), advantages are all 0.0.

    Args:
        rewards: List of reward values for one task group.

    Returns:
        List of advantage values, one per rollout.
    """
    if not rewards:
        return []

    arr = np.array(rewards)
    mean_r = np.mean(arr)
    std_r = np.std(arr)

    if std_r < 1e-8:
        return [0.0] * len(rewards)

    advantages = (arr - mean_r) / std_r
    return advantages.tolist()


def compute_group_statistics(
    group: List[Dict[str, Any]],
) -> Dict[str, float]:
    """Compute summary statistics for a group of rollouts.

    Args:
        group: List of dicts with 'reward' and optionally 'tests_pass'.

    Returns:
        Dictionary with statistics.
    """
    if not group:
        return {
            "count": 0,
            "mean_reward": 0.0,
            "max_reward": 0.0,
            "pass_rate": 0.0,
        }

    rewards = [r["reward"] for r in group]
    tests_pass = [r.get("tests_pass", False) for r in group]

    return {
        "count": len(group),
        "mean_reward": float(np.mean(rewards)),
        "std_reward": float(np.std(rewards)) if len(rewards) > 1 else 0.0,
        "max_reward": float(np.max(rewards)),
        "min_reward": float(np.min(rewards)),
        "pass_rate": float(np.mean(tests_pass)),
        "pass_count": int(np.sum(tests_pass)),
    }


class RewardTracker:
    """Track reward statistics across training iterations."""

    def __init__(self):
        self.history: List[Dict[str, Any]] = []
        self.iteration_rewards: List[float] = []

    def record_group(
        self,
        iteration: int,
        group_stats: Dict[str, float],
    ):
        """Record statistics for one group of rollouts."""
        self.history.append({
            "iteration": iteration,
            **group_stats,
        })
        self.iteration_rewards.append(group_stats.get("mean_reward", 0.0))

    def get_summary(self) -> Dict[str, Any]:
        """Get summary statistics across all recorded groups."""
        if not self.history:
            return {}

        all_rewards = [
            h.get("mean_reward", 0.0)
            for h in self.history
        ]
        all_pass_rates = [
            h.get("pass_rate", 0.0)
            for h in self.history
        ]

        return {
            "total_groups": len(self.history),
            "mean_reward": float(np.mean(all_rewards)),
            "final_reward": float(all_rewards[-1]) if all_rewards else 0.0,
            "reward_improvement": (
                float(all_rewards[-1] - all_rewards[0])
                if len(all_rewards) > 1 else 0.0
            ),
            "mean_pass_rate": float(np.mean(all_pass_rates)),
            "final_pass_rate": (
                float(all_pass_rates[-1])
                if all_pass_rates else 0.0
            ),
        }
