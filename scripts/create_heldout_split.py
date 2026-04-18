#!/usr/bin/env python3
"""Create a held-out eval split for fair benchmark evaluation.

Stratified 80/20 split of MultiPL-E tasks by source (HumanEval/MBPP).
Held-out tasks are NEVER used in training — clean evaluation only.

Usage:
    python3 scripts/create_heldout_split.py
    python3 scripts/create_heldout_split.py --fraction 0.25
"""

import argparse
import json
import random
import sys
from pathlib import Path

from edn_format import Keyword, loads

ROOT = Path(__file__).resolve().parent.parent
SEED = 42


def load_tasks():
    """Load task definitions, group by source."""
    with open(ROOT / "benchmark" / "tasks-v0.edn") as f:
        tasks = loads(f.read())

    by_source = {}
    for t in tasks:
        source = str(t[Keyword("source")])
        task_id = str(t[Keyword("id")])
        by_source.setdefault(source, []).append(task_id)

    return by_source


def main():
    parser = argparse.ArgumentParser(description="Create held-out eval split")
    parser.add_argument("--fraction", type=float, default=0.20,
                        help="Fraction to hold out (default 0.20)")
    parser.add_argument("--seed", type=int, default=SEED)
    args = parser.parse_args()

    by_source = load_tasks()

    print("=" * 50)
    print(f"Held-out split: {(1 - args.fraction) * 100:.0f}/{args.fraction * 100:.0f}")
    print("=" * 50)

    random.seed(args.seed)

    train_ids = []
    heldout_ids = []

    for source, ids in sorted(by_source.items()):
        random.shuffle(ids)
        n = max(1, int(len(ids) * args.fraction))
        heldout = ids[:n]
        train = ids[n:]
        heldout_ids.extend(heldout)
        train_ids.extend(train)
        print(f"  {source}: {len(train)} train, {len(heldout)} held-out")

    print(f"\n  Total train:    {len(train_ids)}")
    print(f"  Total held-out: {len(heldout_ids)}")

    # Save
    out_dir = ROOT / "data" / "sft"
    out_dir.mkdir(parents=True, exist_ok=True)

    heldout_path = out_dir / "heldout_task_ids.json"
    train_path = out_dir / "train_task_ids.json"

    with open(heldout_path, "w") as f:
        json.dump(sorted(heldout_ids), f, indent=2)
    with open(train_path, "w") as f:
        json.dump(sorted(train_ids), f, indent=2)

    print(f"\n  Saved: {heldout_path}")
    print(f"  Saved: {train_path}")

    # Verify no overlap
    assert not set(train_ids) & set(heldout_ids), "Train/holdout overlap!"
    print(f"  No overlap: OK")

    # Show a few held-out IDs
    print(f"\n  Held-out sample: {sorted(heldout_ids)[:5]} ...")


if __name__ == "__main__":
    main()
