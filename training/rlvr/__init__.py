"""
RLVR Training Package for Clojure LLM.

Reinforcement Learning from Verifier Rewards using Clojure subprocess
evaluation and Tinker's GRPO training API.

Components:
- train: GRPO training loop with Tinker importance_sampling loss
- evaluate: Lightweight Clojure evaluator (subprocess-based)
- rewards: GRPO advantage computation and reward tracking
"""

from .rewards import compute_grpo_advantages, compute_group_statistics, RewardTracker
from .train import RLVRConfig, train as train_rlvr

__all__ = [
    "compute_grpo_advantages",
    "compute_group_statistics",
    "RewardTracker",
    "RLVRConfig",
    "train_rlvr",
]
