#!/usr/bin/env python3
"""Export repo-derived JSONL files into the SFT source directory.

Reads raw repo-corpus files, applies a stable train/val split, and writes:
- data/repo-corpus/derived/repo_direct_{train,val}.jsonl
- data/repo-corpus/derived/repo_repair_{train,val}.jsonl
- data/sft/sources/repo_direct_{train,val}.jsonl
- data/sft/sources/repo_repair_{train,val}.jsonl
"""

import json
import random
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
CORPUS_DIR = ROOT / "data" / "repo-corpus"
RAW_DIR = CORPUS_DIR / "raw"
DERIVED_DIR = CORPUS_DIR / "derived"
SOURCES_DIR = ROOT / "data" / "sft" / "sources"
SEED = 42
VAL_FRACTION = 0.10


def load_jsonl(path):
    rows = []
    if not path.exists():
        return rows
    with open(path) as f:
        for line in f:
            line = line.strip()
            if line:
                rows.append(json.loads(line))
    return rows


def collect_rows(filename):
    rows = []
    for repo_dir in sorted(RAW_DIR.glob("*")):
        path = repo_dir / filename
        rows.extend(load_jsonl(path))
    return rows


def split_rows(rows):
    rows = list(rows)
    random.seed(SEED)
    random.shuffle(rows)
    val_n = max(1, int(len(rows) * VAL_FRACTION)) if rows else 0
    return rows[val_n:], rows[:val_n]


def write_jsonl(path, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w") as f:
        for row in rows:
            f.write(json.dumps(row) + "\n")


def strip_to_messages(rows):
    stripped = []
    for row in rows:
        if "messages" not in row:
            continue
        stripped.append({"messages": row["messages"]})
    return stripped


def main():
    print("=" * 60)
    print("Export repo-derived SFT sources")
    print("=" * 60)

    direct_rows = collect_rows("task_candidates.jsonl")
    repair_rows = collect_rows("repair_pairs.jsonl")

    direct_train, direct_val = split_rows(direct_rows)
    repair_train, repair_val = split_rows(repair_rows)

    outputs = {
        DERIVED_DIR / "repo_direct_train.jsonl": direct_train,
        DERIVED_DIR / "repo_direct_val.jsonl": direct_val,
        DERIVED_DIR / "repo_repair_train.jsonl": repair_train,
        DERIVED_DIR / "repo_repair_val.jsonl": repair_val,
        SOURCES_DIR / "repo_direct_train.jsonl": strip_to_messages(direct_train),
        SOURCES_DIR / "repo_direct_val.jsonl": strip_to_messages(direct_val),
        SOURCES_DIR / "repo_repair_train.jsonl": strip_to_messages(repair_train),
        SOURCES_DIR / "repo_repair_val.jsonl": strip_to_messages(repair_val),
    }

    for path, rows in outputs.items():
        write_jsonl(path, rows)
        print(f"  {path.relative_to(ROOT)}: {len(rows)} rows")


if __name__ == "__main__":
    main()
