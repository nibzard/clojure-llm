"""
SFT Training Package for Clojure LLM.

This package implements Supervised Fine-Tuning using the Tinker platform.

Components:
- train: Main SFT training loop
- evaluate: Evaluation and comparison scripts
"""

from .train import train as train_sft

__all__ = [
    "train_sft",
]
