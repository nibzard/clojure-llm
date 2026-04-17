"""
Training package for Clojure LLM.

Provides SFT and RLVR training implementations using the Tinker platform.
"""

from . import sft
from . import rlvr

__all__ = ["sft", "rlvr"]
