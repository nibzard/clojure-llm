#!/usr/bin/env python3
"""Rebuild SFT training data with held-out exclusion + 4clojure.

Filters out all pairs for held-out tasks, appends 4clojure pairs,
and outputs clean training data ready for Tinker.

Matching strategy: extract the function name from each training pair's
prompt (the `(defn function_name` prefix) and compare against the
entrypoint field in the task definitions. This is robust to whitespace
differences between prompt files and JSONL content.

Usage:
    python3 scripts/rebuild_training_data.py
"""

import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent


def load_jsonl(path):
    """Load JSONL file, return list of records."""
    if not path.exists():
        return []
    records = []
    with open(path) as f:
        for line in f:
            line = line.strip()
            if line:
                records.append(json.loads(line))
    return records


def load_json(path):
    """Load JSON file."""
    if not path.exists():
        return None
    with open(path) as f:
        return json.load(f)


def extract_fn_name(content):
    """Extract function name from a prompt that starts with (defn name."""
    m = re.match(r'\(defn\s+(\S+)', content.strip())
    return m.group(1).replace("-", "_") if m else None


def main():
    sft_dir = ROOT / "data" / "sft"

    print("=" * 60)
    print("Rebuilding SFT training data")
    print("=" * 60)

    # Load held-out task IDs
    heldout_ids = set(load_json(sft_dir / "heldout_task_ids.json") or [])
    if not heldout_ids:
        print("Error: No heldout_task_ids.json found. Run create_heldout_split.py first.")
        sys.exit(1)

    print(f"\nHeld-out tasks: {len(heldout_ids)}")

    # Build mapping: entrypoint → task_id
    from edn_format import Keyword, loads

    with open(ROOT / "benchmark" / "tasks-v0.edn") as f:
        tasks = loads(f.read())

    entrypoint_to_task_id = {}
    for t in tasks:
        task_id = str(t[Keyword("id")])
        entrypoint = str(t[Keyword("entrypoint")])
        entrypoint_to_task_id[entrypoint] = task_id

    print(f"Entrypoints mapped: {len(entrypoint_to_task_id)}")

    # Build set of held-out entrypoint names for fast lookup
    heldout_entrypoints = set()
    for ep, tid in entrypoint_to_task_id.items():
        if tid in heldout_ids:
            heldout_entrypoints.add(ep)

    # Build a set of held-out function names from prompt files
    # (more reliable than entrypoint field — some differ)
    heldout_fn_names = set()
    for t in tasks:
        task_id = str(t[Keyword("id")])
        if task_id in heldout_ids:
            prompt_path = ROOT / str(t[Keyword("prompt-ref")][Keyword("path")])
            if prompt_path.exists():
                prompt_text = prompt_path.read_text()
                m = re.match(r"\(defn\s+(\S+)", prompt_text.strip())
                if m:
                    fn = m.group(1)
                    heldout_fn_names.add(fn)
                    heldout_fn_names.add(fn.replace("-", "_"))
                    heldout_fn_names.add(fn.replace("_", "-"))

    print(f"Held-out entrypoints: {len(heldout_entrypoints)}")
    print(f"Held-out fn names:    {len(heldout_fn_names)}")

    # ── Load existing training data ──────────────────────────────────────────

    sources = [
        ("final_train.jsonl", "final_val.jsonl"),
        ("4clojure_train.jsonl", "4clojure_val.jsonl"),
    ]

    train_pairs = []
    val_pairs = []
    stats = {"heldout_removed": 0, "loaded": 0, "4clojure": 0}

    for train_file, val_file in sources:
        is_4clojure = "4clojure" in train_file

        for fname, target_list in [(train_file, train_pairs), (val_file, val_pairs)]:
            path = sft_dir / fname
            if not path.exists():
                continue

            records = load_jsonl(path)
            for r in records:
                content = r["messages"][0]["content"]

                if not is_4clojure:
                    # Check if the function name in the prompt matches a held-out task
                    fn_name = extract_fn_name(content)
                    if fn_name and fn_name in heldout_fn_names:
                        stats["heldout_removed"] += 1
                        continue
                    # Also check for function name inside templates
                    # e.g. "Implement the following Clojure function:\n```\n(defn name ..."
                    if not fn_name:
                        m = re.search(r'\(defn\s+(\S+)', content)
                        if m:
                            found_fn = m.group(1).replace("-", "_")
                            if found_fn in heldout_fn_names:
                                stats["heldout_removed"] += 1
                                continue

                target_list.append(r)
                if is_4clojure:
                    stats["4clojure"] += 1
                stats["loaded"] += 1

    print(f"\n  Pairs loaded:        {stats['loaded']}")
    print(f"  Held-out removed:    {stats['heldout_removed']}")
    print(f"  4clojure added:      {stats['4clojure']}")

    # ── Write output ─────────────────────────────────────────────────────────

    train_path = sft_dir / "sft_train.jsonl"
    val_path = sft_dir / "sft_val.jsonl"

    with open(train_path, "w") as f:
        for r in train_pairs:
            f.write(json.dumps({"messages": r["messages"]}) + "\n")

    with open(val_path, "w") as f:
        for r in val_pairs:
            f.write(json.dumps({"messages": r["messages"]}) + "\n")

    print(f"\n  sft_train.jsonl: {len(train_pairs)} pairs")
    print(f"  sft_val.jsonl:   {len(val_pairs)} pairs")
    print(f"  Total:           {len(train_pairs) + len(val_pairs)} pairs")

    # ── Verify ───────────────────────────────────────────────────────────────

    # Check no held-out function name appears in output's defn form
    leaked = 0
    for r in train_pairs + val_pairs:
        content = r["messages"][0]["content"]
        m = re.search(r'\(defn\s+(\S+)', content)
        if m:
            found_fn = m.group(1).replace("-", "_")
            if found_fn in heldout_fn_names:
                leaked += 1
    if leaked:
        print(f"\n  WARNING: {leaked} held-out pairs leaked!")
    else:
        print(f"\n  No held-out leakage: OK")

    # ── Summary ──────────────────────────────────────────────────────────────

    print(f"\n{'=' * 60}")
    print("Summary")
    print(f"{'=' * 60}")
    print(f"  Original data:   2,459 pairs (final_train + final_val)")
    print(f"  4clojure added:  {stats['4clojure']} pairs")
    print(f"  Held-out tasks:  {len(heldout_ids)} ({stats['heldout_removed']} pairs removed)")
    print(f"  Final training:  {len(train_pairs) + len(val_pairs)} pairs")

    target = 2000
    if len(train_pairs) + len(val_pairs) >= target:
        print(f"\n  TARGET MET: {len(train_pairs) + len(val_pairs)} >= {target}")
    else:
        print(f"\n  Short of target by: {target - len(train_pairs) - len(val_pairs)}")


if __name__ == "__main__":
    main()
