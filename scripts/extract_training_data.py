#!/usr/bin/env python3
"""Extract verified training pairs from baseline runs.

Reads results + candidates from all three baseline runs, collects every task
where at least one model passed, and outputs SFT training data as JSONL.

Outputs:
  data/sft/verified_solutions.json  — intermediate: task_id → [solutions]
  data/sft/uncovered_tasks.json     — task IDs with no passing solution
  data/sft/train.jsonl              — 90% of direct training pairs
  data/sft/val.jsonl                — 10% held out

Usage:
    python3 scripts/extract_training_data.py
"""

import json
import random
import sys
from collections import defaultdict
from pathlib import Path

from edn_format import Keyword, loads

ROOT = Path(__file__).resolve().parent.parent

# Baseline runs with human-readable labels
BASELINE_RUNS = {
    "2026-04-17-gpt-5-4-direct": "GPT-5.4",
    "2026-04-17-gpt-5-4-mini-2026-03-17-direct": "GPT-5.4-mini",
    "2026-04-17-claude-opus-4-7-direct": "Opus-4.7",
}

VAL_FRACTION = 0.10
SEED = 42


# ── EDN helpers ──────────────────────────────────────────────────────────────

def edn_get(d, key_str):
    """Access an EDN dict by keyword name."""
    return d[Keyword(key_str)]


def edn_get_in(d, *key_strs):
    """Nested access on EDN dicts."""
    val = d
    for k in key_strs:
        val = val[Keyword(k)]
        if val is None:
            return None
    return val


# ── Data loading ─────────────────────────────────────────────────────────────

def load_tasks():
    """Load task definitions. Returns dict: task_id → {prompt_path, source, tests_path}."""
    tasks_file = ROOT / "benchmark" / "tasks-v0.edn"
    with open(tasks_file) as f:
        tasks = loads(f.read())

    result = {}
    for t in tasks:
        task_id = str(edn_get(t, "id"))
        prompt_path = str(edn_get_in(t, "prompt-ref", "path"))
        tests_path = str(edn_get_in(t, "tests-ref", "path"))
        source = str(edn_get(t, "source"))
        result[task_id] = {
            "prompt_path": prompt_path,
            "tests_path": tests_path,
            "source": source,
        }
    return result


def load_results(run_id):
    """Load all evaluation results for a run. Returns dict: task_id → {outcome, notes, ...}."""
    results_dir = ROOT / "benchmark" / "results" / run_id
    results = {}
    for edn_file in sorted(results_dir.glob("*.edn")):
        with open(edn_file) as f:
            r = loads(f.read())
        task_id = str(edn_get(r, "task-id"))
        results[task_id] = {
            "outcome": str(edn_get(r, "outcome")),
            "error_kind": str(edn_get(r, "error-kind")),
            "notes": str(edn_get(r, "notes")),
            "syntax_ok": bool(edn_get(r, "syntax-ok")),
            "kondo_ok": bool(edn_get(r, "kondo-ok")),
            "tests_ok": bool(edn_get(r, "tests-ok")),
        }
    return results


def task_id_to_prompt_stem(task_id, tasks):
    """Map task_id (e.g. 'humaneval-clj-001') to prompt filename stem (e.g. 'HumanEval_0_has_close_elements')."""
    prompt_path = tasks[task_id]["prompt_path"]
    return Path(prompt_path).stem


def read_candidate(run_id, prompt_stem):
    """Read candidate code from a run directory."""
    path = ROOT / "benchmark" / "candidates" / run_id / f"{prompt_stem}.clj"
    if not path.exists():
        return None
    code = path.read_text()
    if code.startswith(";; Generation failed"):
        return None
    return code


def read_prompt(task_id, tasks):
    """Read the prompt text for a task."""
    prompt_path = ROOT / tasks[task_id]["prompt_path"]
    return Path(prompt_path).read_text()


# ── Main extraction ──────────────────────────────────────────────────────────

def main():
    print("=" * 60)
    print("Extracting verified training data from baselines")
    print("=" * 60)

    # Load task definitions
    tasks = load_tasks()
    all_task_ids = sorted(tasks.keys())
    print(f"\nTotal tasks: {len(all_task_ids)}")

    # Load results + candidates for each baseline run
    run_data = {}
    for run_id, label in BASELINE_RUNS.items():
        results = load_results(run_id)
        pass_count = sum(1 for r in results.values() if r["outcome"] == ":pass")
        print(f"  {label}: {pass_count}/{len(results)} pass ({run_id})")
        run_data[run_id] = {
            "label": label,
            "results": results,
        }

    # Collect verified solutions: task_id → [{run_id, model, code}]
    verified = defaultdict(list)
    # Also collect failing candidates for fix-loop later
    failing = defaultdict(list)

    for task_id in all_task_ids:
        prompt_stem = task_id_to_prompt_stem(task_id, tasks)
        for run_id, info in run_data.items():
            results = info["results"]
            if task_id not in results:
                continue
            r = results[task_id]
            code = read_candidate(run_id, prompt_stem)
            if code is None:
                continue
            entry = {
                "run_id": run_id,
                "model": info["label"],
                "code": code,
                "outcome": r["outcome"],
            }
            if r["outcome"] == ":pass":
                verified[task_id].append(entry)
            else:
                entry["error_kind"] = r["error_kind"]
                entry["notes"] = r["notes"]
                entry["syntax_ok"] = r["syntax_ok"]
                entry["kondo_ok"] = r["kondo_ok"]
                failing[task_id].append(entry)

    covered = sorted(verified.keys())
    uncovered = sorted(tid for tid in all_task_ids if tid not in verified)

    print(f"\n{'─' * 40}")
    print(f"Tasks with at least one passing solution: {len(covered)}")
    print(f"Uncovered tasks (all models failed):      {len(uncovered)}")

    # Report per-source breakdown
    for src in ["humaneval", "mbpp"]:
        src_tasks = [t for t in all_task_ids if src in t]
        src_covered = [t for t in covered if src in t]
        print(f"  {src}: {len(src_covered)}/{len(src_tasks)} covered")

    # Deduplication: prefer GPT-5.4 solutions (best pass rate), then mini, then Opus
    # For each task, keep all unique verified solutions (different models may have different approaches)
    # But for training, we want at most 1 solution per task to avoid over-representation
    # Use the best model's solution (GPT-5.4 > GPT-5.4-mini > Opus)
    model_priority = {"GPT-5.4": 0, "GPT-5.4-mini": 1, "Opus-4.7": 2}

    solutions_by_task = {}
    for task_id in covered:
        solns = verified[task_id]
        # Pick best model's solution
        solns_sorted = sorted(solns, key=lambda s: model_priority.get(s["model"], 99))
        solutions_by_task[task_id] = solns_sorted[0]["code"]

    # ── Output intermediate data ─────────────────────────────────────────────

    out_dir = ROOT / "data" / "sft"
    out_dir.mkdir(parents=True, exist_ok=True)

    # Verified solutions (for fix_loop.py to use)
    verified_json = {
        tid: {
            "solution": solutions_by_task[tid],
            "source": tasks[tid]["source"],
            "contributors": [s["model"] for s in verified[tid]],
        }
        for tid in covered
    }
    (out_dir / "verified_solutions.json").write_text(
        json.dumps(verified_json, indent=2)
    )

    # Uncovered tasks (for fix_loop.py)
    uncovered_data = []
    for tid in uncovered:
        # Include best failing candidate info
        best_fail = None
        if tid in failing:
            # Prefer candidates that got furthest (syntax ok > kondo ok > test fail)
            def fail_score(f):
                return int(f["syntax_ok"]) * 2 + int(f["kondo_ok"]) * 1
            best = max(failing[tid], key=fail_score)
            prompt_stem = task_id_to_prompt_stem(tid, tasks)
            best_fail = {
                "model": best["model"],
                "run_id": best["run_id"],
                "code": best["code"],
                "error_kind": best["error_kind"],
                "notes": best["notes"],
                "syntax_ok": best["syntax_ok"],
                "kondo_ok": best["kondo_ok"],
            }
        uncovered_data.append({
            "task_id": tid,
            "prompt_path": tasks[tid]["prompt_path"],
            "tests_path": tasks[tid]["tests_path"],
            "source": tasks[tid]["source"],
            "best_failing": best_fail,
        })
    (out_dir / "uncovered_tasks.json").write_text(
        json.dumps(uncovered_data, indent=2)
    )

    # ── Format as JSONL training pairs ───────────────────────────────────────

    random.seed(SEED)

    direct_pairs = []
    for task_id in covered:
        prompt = read_prompt(task_id, tasks)
        solution = solutions_by_task[task_id]
        source = "humaneval" if "humaneval" in task_id else "mbpp"
        direct_pairs.append({
            "task_id": task_id,
            "source": source,
            "messages": [
                {"role": "user", "content": prompt},
                {"role": "assistant", "content": solution},
            ],
        })

    # Stratified split: 90% train, 10% val, no task in both
    humaneval_pairs = [p for p in direct_pairs if p["source"] == "humaneval"]
    mbpp_pairs = [p for p in direct_pairs if p["source"] == "mbpp"]

    random.shuffle(humaneval_pairs)
    random.shuffle(mbpp_pairs)

    he_val_n = max(1, int(len(humaneval_pairs) * VAL_FRACTION))
    mb_val_n = max(1, int(len(mbpp_pairs) * VAL_FRACTION))

    val_pairs = humaneval_pairs[:he_val_n] + mbpp_pairs[:mb_val_n]
    train_pairs = humaneval_pairs[he_val_n:] + mbpp_pairs[mb_val_n:]

    # Write JSONL
    train_path = out_dir / "train.jsonl"
    val_path = out_dir / "val.jsonl"

    with open(train_path, "w") as f:
        for p in train_pairs:
            f.write(json.dumps({"messages": p["messages"]}) + "\n")

    with open(val_path, "w") as f:
        for p in val_pairs:
            f.write(json.dumps({"messages": p["messages"]}) + "\n")

    # ── Stats ────────────────────────────────────────────────────────────────

    print(f"\n{'─' * 40}")
    print(f"Output:")
    print(f"  train.jsonl: {len(train_pairs)} pairs")
    print(f"    humaneval: {len([p for p in train_pairs if p['source'] == 'humaneval'])}")
    print(f"    mbpp:      {len([p for p in train_pairs if p['source'] == 'mbpp'])}")
    print(f"  val.jsonl:   {len(val_pairs)} pairs")
    print(f"    humaneval: {len([p for p in val_pairs if p['source'] == 'humaneval'])}")
    print(f"    mbpp:      {len([p for p in val_pairs if p['source'] == 'mbpp'])}")
    print(f"  uncovered:   {len(uncovered)} tasks → data/sft/uncovered_tasks.json")

    # Verify no overlap
    train_ids = {p["task_id"] for p in train_pairs}
    val_ids = {p["task_id"] for p in val_pairs}
    overlap = train_ids & val_ids
    assert not overlap, f"Train/val overlap: {overlap}"
    print(f"\n  Train/val overlap: none ✓")

    # Verify all solutions start with (defn
    bad = [p["task_id"] for p in train_pairs + val_pairs
           if not p["messages"][1]["content"].strip().startswith("(defn")]
    if bad:
        print(f"  WARNING: {len(bad)} solutions don't start with (defn")
    else:
        print(f"  All solutions start with (defn) ✓")

    print(f"\n{'=' * 60}")
    print(f"Next: run fix_loop.py to cover {len(uncovered)} remaining tasks")
    print(f"{'=' * 60}")


if __name__ == "__main__":
    main()
