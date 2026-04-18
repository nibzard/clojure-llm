#!/usr/bin/env python3
"""Multi-sample generation for SFT training data.

Runs N samples per task at higher temperature, evaluates each,
and keeps all unique verified solutions as training pairs.

Phase 1: Generate N samples × 558 tasks via GPT-5.4-mini
Phase 2: Evaluate each sample set (separate run per sample index)
Phase 3: Collect all unique verified solutions, dedup, output JSONL

Usage:
    python3 scripts/multi_sample.py --n 5 --temperature 0.8
    python3 scripts/multi_sample.py --n 5 --skip-gen       # just evaluate
    python3 scripts/multi_sample.py --n 5 --skip-eval      # just generate
    python3 scripts/multi_sample.py --limit 10              # test with 10 tasks
"""

import argparse
import json
import os
import random
import re
import subprocess
import sys
import time
from pathlib import Path

from dotenv import load_dotenv
from openai import OpenAI

ROOT = Path(__file__).resolve().parent.parent

BASE_RUN_ID = "2026-04-18-multi-sample"
SEED = 42

SYSTEM_PROMPT = """You are an expert Clojure programmer. Your task is to \
implement a Clojure function according to the given specification.

Guidelines:
- Write pure, functional code when possible
- Use Clojure's core library functions effectively
- Handle nil and empty inputs appropriately
- Follow the Clojure style guide (kebab-case, ? for predicates, etc.)
- Avoid mutable state unless the problem explicitly requires it

Output only the function implementation. No test cases, no example usage, \
no explanatory text, no namespace declarations."""

USER_TEMPLATE = """Implement the following Clojure function:

```
{prompt}
```

Provide only the complete function implementation (`defn` form)."""


def load_env():
    env_path = ROOT / ".env"
    if not env_path.exists():
        print("Error: No .env file found")
        sys.exit(1)
    load_dotenv(str(env_path))


def extract_code(response_text):
    text = response_text.strip()
    code_block = re.search(r'```(?:clojure|clj)?\s*\n(.*?)```', text, re.DOTALL)
    if code_block:
        text = code_block.group(1).strip()
    if text.startswith("(defn"):
        return text
    match = re.search(r'(\(defn\s+.*)', text, re.DOTALL)
    if match:
        candidate = match.group(1)
        depth = 0
        for i, ch in enumerate(candidate):
            if ch == '(':
                depth += 1
            elif ch == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1]
    return text


def load_prompts():
    """Load all task prompts. Returns [(prompt_stem, prompt_text)]."""
    prompts_dir = ROOT / "benchmark" / "prompts" / "multipl-e"
    return [(f.stem, f.read_text()) for f in sorted(prompts_dir.glob("*.clj"))]


def load_task_id_mapping():
    """Map prompt_stem → task_id."""
    from edn_format import Keyword, loads

    with open(ROOT / "benchmark" / "tasks-v0.edn") as f:
        tasks = loads(f.read())

    mapping = {}
    for t in tasks:
        tid = str(t[Keyword("id")])
        pp = str(t[Keyword("prompt-ref")][Keyword("path")])
        mapping[Path(pp).stem] = tid
    return mapping


def create_run_manifest(run_id, task_ids, model):
    manifest = (
        '{:task-ids [' +
        ' '.join(f'"{tid}"' for tid in sorted(task_ids)) + ']\n'
        ' :policy {:kind :direct}\n'
        f' :tasks-file "benchmark/tasks-v0.edn"\n'
        ' :prompting {:template :multi-sample :temperature 0.8 :top-p 0.95 :samples 1}\n'
        ' :executor {:kind :container :image "clj-bench/eval:dev" :network :none}\n'
        f' :created-at "{time.strftime("%Y-%m-%dT%H:%M:%S.000000Z")}"\n'
        f' :run-id "{run_id}"\n'
        ' :benchmark-version :clj-bench/v0\n'
        f' :model {{:provider :manual :id "{model}"}}}}\n'
    )
    manifest_path = ROOT / "benchmark" / "runs" / f"{run_id}.edn"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(manifest)
    return manifest_path


def evaluate_run(manifest_path):
    cmd = ["clojure", "-M:bench", "evaluate", str(manifest_path)]
    result = subprocess.run(cmd, capture_output=True, text=True, timeout=600)
    if result.returncode != 0:
        print(f"    Eval error: {result.stderr[-200:]}")
    return result


def load_results(run_id):
    from edn_format import Keyword, loads

    results_dir = ROOT / "benchmark" / "results" / run_id
    results = {}
    if not results_dir.exists():
        return results
    for edn_file in results_dir.glob("*.edn"):
        with open(edn_file) as f:
            r = loads(f.read())
        task_id = str(r[Keyword("task-id")])
        outcome = str(r[Keyword("outcome")])
        results[task_id] = outcome
    return results


# ── Phase 1: Generate ───────────────────────────────────────────────────────

def generate_samples(client, model, prompts, n, temperature, limit=None):
    """Generate N samples for all tasks."""
    if limit:
        prompts = prompts[:limit]

    total_input = 0
    total_output = 0

    for sample_idx in range(n):
        run_id = f"{BASE_RUN_ID}-{sample_idx}"
        cand_dir = ROOT / "benchmark" / "candidates" / run_id
        cand_dir.mkdir(parents=True, exist_ok=True)

        print(f"\n{'─' * 40}")
        print(f"Sample {sample_idx + 1}/{n}: {len(prompts)} tasks")
        print(f"{'─' * 40}")

        generated = 0
        skipped = 0

        for i, (stem, prompt_text) in enumerate(prompts, 1):
            out_file = cand_dir / f"{stem}.clj"

            # Resume: skip if valid candidate already exists
            if out_file.exists():
                content = out_file.read_text()
                if content and not content.startswith(";; Generation failed"):
                    skipped += 1
                    continue

            user_msg = USER_TEMPLATE.format(prompt=prompt_text)

            try:
                resp = client.chat.completions.create(
                    model=model,
                    messages=[
                        {"role": "system", "content": SYSTEM_PROMPT},
                        {"role": "user", "content": user_msg},
                    ],
                    temperature=temperature,
                    max_completion_tokens=1024,
                )
                code = extract_code(resp.choices[0].message.content)
                out_file.write_text(code)
                generated += 1

                usage = resp.usage
                if usage:
                    total_input += usage.prompt_tokens
                    total_output += usage.completion_tokens

            except Exception as e:
                out_file.write_text(f";; Generation failed: {e}")

            if i % 100 == 0:
                print(f"  [{i}/{len(prompts)}] {generated} generated, {skipped} skipped")

            time.sleep(0.3)

        print(f"  Done: {generated} generated, {skipped} resumed")

    input_cost = total_input * 0.75 / 1_000_000
    output_cost = total_output * 4.50 / 1_000_000
    print(f"\nGeneration cost: ${input_cost + output_cost:.2f}")


# ── Phase 2: Evaluate ───────────────────────────────────────────────────────

def evaluate_samples(prompts, n, task_ids, limit=None):
    """Evaluate all sample runs."""
    if limit:
        prompts = prompts[:limit]
        task_ids = task_ids[:limit]

    print(f"\n{'=' * 60}")
    print(f"Evaluating {n} sample runs")
    print(f"{'=' * 60}")

    for sample_idx in range(n):
        run_id = f"{BASE_RUN_ID}-{sample_idx}"
        results_dir = ROOT / "benchmark" / "results" / run_id

        # Skip if already fully evaluated
        if results_dir.exists():
            existing = len(list(results_dir.glob("*.edn")))
            if existing == len(prompts):
                results = load_results(run_id)
                passed = sum(1 for o in results.values() if o == ":pass")
                print(f"  Sample {sample_idx}: already evaluated ({passed}/{len(prompts)} pass)")
                continue

        manifest_path = create_run_manifest(run_id, task_ids, "gpt-5.4-mini")
        print(f"\n  Evaluating sample {sample_idx}...")
        evaluate_run(manifest_path)

        results = load_results(run_id)
        passed = sum(1 for o in results.values() if o == ":pass")
        print(f"  → {passed}/{len(results)} passed")


# ── Phase 3: Collect ────────────────────────────────────────────────────────

def collect_results(prompts, n, task_mapping):
    """Collect all unique verified solutions from all sample runs."""
    print(f"\n{'=' * 60}")
    print("Collecting verified solutions")
    print(f"{'=' * 60}")

    # Load existing solutions to avoid duplicates
    existing_solutions = set()
    for fname in ["data/sft/train.jsonl", "data/sft/val.jsonl"]:
        fpath = ROOT / fname
        if fpath.exists():
            with open(fpath) as f:
                for line in f:
                    obj = json.loads(line)
                    sol = obj["messages"][1]["content"].strip()
                    existing_solutions.add(sol)

    print(f"Existing solutions in train/val: {len(existing_solutions)}")

    # Collect per-sample pass counts
    sample_pass_counts = []
    all_solutions = []  # (prompt_text, solution, task_id, sample_idx)
    seen = set()

    for sample_idx in range(n):
        run_id = f"{BASE_RUN_ID}-{sample_idx}"
        results = load_results(run_id)
        cand_dir = ROOT / "benchmark" / "candidates" / run_id

        passed = 0
        new = 0

        for stem, prompt_text in prompts:
            task_id = task_mapping[stem]
            if task_id not in results or results[task_id] != ":pass":
                continue

            passed += 1
            cand_file = cand_dir / f"{stem}.clj"
            if not cand_file.exists():
                continue

            code = cand_file.read_text().strip()
            if not code.startswith("(defn"):
                continue

            # Dedup by solution content (same code = same solution)
            if code in seen or code in existing_solutions:
                continue

            seen.add(code)
            new += 1
            all_solutions.append((prompt_text, code, task_id, sample_idx))

        sample_pass_counts.append(passed)
        print(f"  Sample {sample_idx}: {passed} passed, {new} new unique")

    print(f"\nTotal new unique solutions: {len(all_solutions)}")
    return all_solutions


def write_output(solutions):
    """Write solutions to JSONL with stratified train/val split."""
    random.seed(SEED)
    random.shuffle(solutions)

    he = [(p, c, t) for p, c, t, _ in solutions if "humaneval" in t]
    mb = [(p, c, t) for p, c, t, _ in solutions if "mbpp" in t]

    val_n_he = max(1, int(len(he) * 0.1))
    val_n_mb = max(1, int(len(mb) * 0.1))

    val_pairs = he[:val_n_he] + mb[:val_n_mb]
    train_pairs = he[val_n_he:] + mb[val_n_mb:]

    out_dir = ROOT / "data" / "sft"

    with open(out_dir / "multi_sample_train.jsonl", "w") as f:
        for prompt, code, _ in train_pairs:
            f.write(json.dumps({"messages": [
                {"role": "user", "content": prompt},
                {"role": "assistant", "content": code},
            ]}) + "\n")

    with open(out_dir / "multi_sample_val.jsonl", "w") as f:
        for prompt, code, _ in val_pairs:
            f.write(json.dumps({"messages": [
                {"role": "user", "content": prompt},
                {"role": "assistant", "content": code},
            ]}) + "\n")

    print(f"\nOutput files:")
    print(f"  multi_sample_train.jsonl: {len(train_pairs)} pairs")
    print(f"  multi_sample_val.jsonl:   {len(val_pairs)} pairs")
    print(f"  Total new pairs:          {len(solutions)}")

    # Count final totals
    existing_train = sum(1 for _ in open(out_dir / "train.jsonl")) if (out_dir / "train.jsonl").exists() else 0
    existing_val = sum(1 for _ in open(out_dir / "val.jsonl")) if (out_dir / "val.jsonl").exists() else 0
    total = existing_train + existing_val + len(solutions)
    print(f"\n  Existing pairs:     {existing_train + existing_val}")
    print(f"  New pairs:          {len(solutions)}")
    print(f"  Grand total:        {total}")

    if total >= 2000:
        print(f"\n  ✓ TARGET REACHED: {total} >= 2,000 pairs")
    else:
        print(f"\n  Still need ~{2000 - total} more pairs")


# ── Main ─────────────────────────────────────────────────────────────────────

def main():
    parser = argparse.ArgumentParser(description="Multi-sample generation for SFT data")
    parser.add_argument("--n", type=int, default=5, help="Samples per task")
    parser.add_argument("--temperature", type=float, default=0.8)
    parser.add_argument("--limit", type=int, default=None, help="Limit to N tasks")
    parser.add_argument("--skip-gen", action="store_true")
    parser.add_argument("--skip-eval", action="store_true")
    args = parser.parse_args()

    load_env()

    api_key = os.environ.get("OPENAI_API_KEY", "")
    base_url = os.environ.get("OPENAI_BASE_URL", "https://api.openai.com/v1")
    model = os.environ.get("OPENAI_MODEL", "gpt-5.4-mini")

    if not api_key:
        print("Error: OPENAI_API_KEY not set")
        sys.exit(1)

    client = OpenAI(api_key=api_key, base_url=base_url)

    print("=" * 60)
    print(f"Multi-sample: n={args.n}, temp={args.temperature}, model={model}")
    print("=" * 60)

    prompts = load_prompts()
    task_mapping = load_task_id_mapping()
    task_ids = [task_mapping[stem] for stem, _ in prompts]

    if args.limit:
        prompts = prompts[:args.limit]
        task_ids = task_ids[:args.limit]

    print(f"Tasks: {len(prompts)}")
    print(f"Samples per task: {args.n}")
    print(f"Total API calls: {len(prompts) * args.n}")

    # Phase 1: Generate
    if not args.skip_gen:
        generate_samples(client, model, prompts, args.n, args.temperature, args.limit)

    # Phase 2: Evaluate
    if not args.skip_eval:
        evaluate_samples(prompts, args.n, task_ids, args.limit)

    # Phase 3: Collect and write
    solutions = collect_results(prompts, args.n, task_mapping)
    write_output(solutions)


if __name__ == "__main__":
    main()
