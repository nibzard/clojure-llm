"""
Reward shaping and advantage computation for RLVR training.

Implements multi-component reward shaping for Clojure code verification
and GRPO (Group Relative Policy Optimization) advantage computation.
"""

from typing import Any, Dict, List

import numpy as np


def compute_shaped_reward(
    eval_ok: bool,
    kondo_clean: bool,
    namespace_loads: bool,
    tests_pass: bool,
    weights: Dict[str, float],
    eval_value: float = 0.1,
    kondo_value: float = 0.2,
    namespace_value: float = 0.1,
    test_value: float = 0.6,
) -> float:
    """
    Compute shaped reward from multiple verification components.

    The reward is a weighted sum of:
    - eval_ok: Code evaluates without throwing
    - kondo_clean: No linter warnings
    - namespace_loads: Namespace can be required
    - tests_pass: All unit tests pass

    Args:
        eval_ok: Whether code evaluates successfully
        kondo_clean: Whether clj-kondo passes
        namespace_loads: Whether namespace loads
        tests_pass: Whether all tests pass
        weights: Weight for each component (must sum to 1.0)
        eval_value: Base value for eval component
        kondo_value: Base value for kondo component
        namespace_value: Base value for namespace component
        test_value: Base value for tests component

    Returns:
        Shaped reward value (typically 0.0 to 1.0)
    """
    reward = 0.0

    if eval_ok:
        reward += weights.get("eval", 0.1) * eval_value

    if kondo_clean:
        reward += weights.get("kondo", 0.2) * kondo_value

    if namespace_loads:
        reward += weights.get("namespace", 0.1) * namespace_value

    if tests_pass:
        # Tests passing is the primary signal
        reward += weights.get("tests", 0.6) * test_value

        # Bonus for all components passing
        if eval_ok and kondo_clean and namespace_loads:
            reward += 0.1  # Small bonus for perfect code

    return reward


def compute_advantages(
    group: List[Dict[str, Any]],
    kl_penalty_coeff: float = 0.1,
) -> List[float]:
    """
    Compute GRPO (Group Relative Policy Optimization) advantages.

    GRPO computes advantages relative to the group mean without an explicit
    value function. Advantages are normalized rewards within each group.

    Args:
        group: List of rollout results, each containing:
            - reward: Raw reward from environment
            - log_prob: Log probability of the action
            - kl_div: KL divergence from reference policy (optional)
        kl_penalty_coeff: Coefficient for KL divergence penalty

    Returns:
        List of advantage values, one per rollout in the group
    """
    if not group:
        return []

    # Extract rewards
    rewards = np.array([r["reward"] for r in group])

    # Compute group mean
    group_mean = np.mean(rewards)

    # Compute raw advantages (reward - group_mean)
    raw_advantages = rewards - group_mean

    # Apply KL penalty if provided
    if "kl_div" in group[0]:
        kl_divs = np.array([r.get("kl_div", 0.0) for r in group])
        advantages = raw_advantages - kl_penalty_coeff * kl_divs
    else:
        advantages = raw_advantages

    # Normalize advantages (zero mean, unit std)
    if len(advantages) > 1 and np.std(advantages) > 0:
        advantages = (advantages - np.mean(advantages)) / np.std(advantages)

    return advantages.tolist()


def compute_group_statistics(
    group: List[Dict[str, Any]],
) -> Dict[str, float]:
    """
    Compute summary statistics for a group of rollouts.

    Args:
        group: List of rollout results

    Returns:
        Dictionary with statistics
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
    """
    Track reward statistics across training iterations.
    """

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

    def get_best_iteration(self) -> Dict[str, Any]:
        """Get the iteration with the highest reward."""
        if not self.history:
            return {}

        best = max(self.history, key=lambda h: h.get("mean_reward", 0.0))
        return best
