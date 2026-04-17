"""
SFT Training Package for Clojure LLM.

This package implements Supervised Fine-Tuning using the Tinker platform.

Components:
- train: Main SFT training loop
- evaluate: Evaluation and comparison scripts
"""

from .train import SFTConfig, train_sft

__all__ = [
    "SFTConfig",
    "train_sft",
]
