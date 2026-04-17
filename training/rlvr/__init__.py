"""
RLVR Training Package for Clojure LLM.

This package implements Reinforcement Learning from Verifier Rewards
using Pi as the code verification environment.

Components:
- env: ClojurePiEnv - Tinker environment for Clojure code verification
- pi_client: PiRPCClient - RPC client for Pi communication
- rewards: Reward shaping and GRPO advantage computation
- train: Main RLVR training loop
"""

from .env import ClojurePiEnv, ClojureTask, ClojurePiEnvBuilder, create_task_from_dict
from .pi_client import PiRPCClient, PiRPCError, create_pi_client
from .rewards import (
    compute_shaped_reward,
    compute_advantages,
    compute_group_statistics,
    RewardTracker,
)
from .train import RLVRConfig, train_rlvr

__all__ = [
    "ClojurePiEnv",
    "ClojureTask",
    "ClojurePiEnvBuilder",
    "create_task_from_dict",
    "PiRPCClient",
    "PiRPCError",
    "create_pi_client",
    "compute_shaped_reward",
    "compute_advantages",
    "compute_group_statistics",
    "RewardTracker",
    "RLVRConfig",
    "train_rlvr",
]
