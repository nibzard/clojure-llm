#!/usr/bin/env python3
"""Create v1 reevaluation manifests for frozen benchmark runs.

Usage:
    python3 scripts/plan_v1_reeval.py
    python3 scripts/plan_v1_reeval.py --date-prefix 2026-04-19
"""

import argparse
import subprocess
from pathlib import Path


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_RUNS = [
    "2026-04-17-gpt-5-4-direct",
    "2026-04-17-gpt-5-4-mini-2026-03-17-direct",
    "2026-04-17-claude-opus-4-7-direct",
    "2026-04-18-sft-qwen3-8b-heldout",
    "2026-04-18-rlvr-qwen3-8b-heldout",
]


def main():
    parser = argparse.ArgumentParser(description="Plan v1 reevaluation manifests")
    parser.add_argument("--date-prefix", default="2026-04-19")
    parser.add_argument("--runs", nargs="*", default=DEFAULT_RUNS)
    args = parser.parse_args()

    for old_run in args.runs:
        new_run = f"{args.date_prefix}-{old_run}-v1"
        cmd = [
            "clojure",
            "-M:bench",
            "plan-reeval",
            new_run,
            old_run,
        ]
        print(" ".join(cmd))
        subprocess.run(cmd, cwd=ROOT, check=True)


if __name__ == "__main__":
    main()
