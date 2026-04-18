#!/usr/bin/env python3
"""Evol-instruct: create variations of existing tasks for SFT diversity.

Takes seed tasks, asks an LLM to create a variation, then verifies the solution.

Usage:
    python3 scripts/evol_instruct.py --seeds 200 --variations 3
    python3 scripts/evol_instruct.py --limit 5  # test run
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
RUN_ID = "2026-04-18-evol-instruct"
SEED_VAL = 42
MAX_VARIATIONS = 3

SYSTEM_PROMPT = (
    "You are a Clojure programming expert designing practice problems.\n"
    "Given a seed Clojure function problem, create a VARIATION that:\n"
    "- Tests a different aspect of Clojure (e.g., if seed uses recursion, variation uses transducers)\n"
    "- Or adds constraints (e.g., handle nil, work with infinite sequences)\n"
    "- Or changes the domain (e.g., if seed works with strings, variation works with vectors)\n"
    "The variation must be solvable in a single defn form.\n\n"
    "Output EXACTLY this structure:\n"
    "===PROMPT===\n"
    "(defn function_name\n"
    '  "Docstring with examples"\n'
    "  [params])\n"
    "===SOLUTION===\n"
    "(defn function_name\n"
    '  "Docstring with examples"\n'
    "  [params]\n"
    "  body)\n"
    "===TESTS===\n"
    "(deftest test-variation\n"
    "  (is (= expected (function args))))\n"
    "===END===\n"
)


def load_env():
    load_dotenv(str(ROOT / ".env"))


def parse_variation(text):
    """Parse LLM output into prompt, solution, tests."""
    prompt_m = re.search(r'===PROMPT===(.*?)===SOLUTION===', text, re.DOTALL)
    sol_m = re.search(r'===SOLUTION===(.*?)===TESTS===', text, re.DOTALL)
    test_m = re.search(r'===TESTS===(.*?)===END===', text, re.DOTALL)

    if not all([prompt_m, sol_m, test_m]):
        return None, None, None

    prompt = prompt_m.group(1).strip()
    solution = sol_m.group(1).strip()
    tests = test_m.group(1).strip()

    if not prompt.startswith("(defn") or not solution.startswith("(defn"):
        return None, None, None
    if not tests.startswith("(deftest"):
        return None, None, None

    return prompt, solution, tests


def write_test_file(name, test_code, test_dir):
    """Write a standalone test file."""
    content = "(require '[clojure.test :refer [deftest is run-test]])\n\n" + test_code + "\n\n(run-test test-variation)\n"
    (test_dir / (name + "_test.clj")).write_text(content)


def main():
    parser = argparse.ArgumentParser(description="Evol-instruct for SFT diversity")
    parser.add_argument("--seeds", type=int, default=200)
    parser.add_argument("--variations", type=int, default=MAX_VARIATIONS)
    parser.add_argument("--limit", type=int, default=None)
    parser.add_argument("--skip-gen", action="store_true")
    args = parser.parse_args()

    load_env()

    api_key = os.environ.get("OPENAI_API_KEY", "")
    base_url = os.environ.get("OPENAI_BASE_URL", "https://api.openai.com/v1")
    model = "gpt-5.4-mini-2026-03-17"

    if not api_key:
        print("Error: OPENAI_API_KEY not set")
        sys.exit(1)

    client = OpenAI(api_key=api_key, base_url=base_url)

    print("=" * 60)
    print(f"Evol-instruct: {args.seeds} seeds x {args.variations} variations")
    print(f"Model: {model}")
    print("=" * 60)

    # Load seed prompts
    prompts_dir = ROOT / "benchmark" / "prompts" / "multipl-e"
    all_prompts = sorted(prompts_dir.glob("*.clj"))
    random.seed(SEED_VAL)
    random.shuffle(all_prompts)
    seed_prompts = all_prompts[:args.seeds]
    if args.limit:
        seed_prompts = seed_prompts[:args.limit]
    print(f"Seed tasks: {len(seed_prompts)}")

    # Output directories
    cand_dir = ROOT / "benchmark" / "candidates" / RUN_ID
    test_dir = ROOT / "benchmark" / "tests" / RUN_ID
    cand_dir.mkdir(parents=True, exist_ok=True)
    test_dir.mkdir(parents=True, exist_ok=True)

    total_input = 0
    total_output = 0
    task_names = []

    # ── Phase 1: Generate ─────────────────────────────────────────────────────

    if not args.skip_gen:
        print(f"\n{'─' * 40}")
        print("Generating variations")
        print(f"{'─' * 40}")

        for i, seed_file in enumerate(seed_prompts, 1):
            seed_prompt = seed_file.read_text()
            seed_name = seed_file.stem

            for v in range(args.variations):
                var_name = f"{seed_name}_v{v}"
                var_file = cand_dir / f"{var_name}.clj"
                prompt_file = cand_dir / f"{var_name}_prompt.clj"
                test_file = test_dir / f"{var_name}_test.clj"

                # Resume check
                if var_file.exists() and prompt_file.exists() and test_file.exists():
                    code = var_file.read_text()
                    if code and not code.startswith(";; "):
                        task_names.append(var_name)
                        continue

                user_msg = (
                    "Here is a Clojure function problem:\n\n"
                    "```\n" + seed_prompt + "\n```\n\n"
                    f"Create variation #{v + 1}. Make it test a DIFFERENT aspect of Clojure."
                )

                try:
                    resp = client.chat.completions.create(
                        model=model,
                        messages=[
                            {"role": "system", "content": SYSTEM_PROMPT},
                            {"role": "user", "content": user_msg},
                        ],
                        temperature=0.9,
                        max_completion_tokens=2048,
                    )

                    text = resp.choices[0].message.content
                    prompt, solution, tests = parse_variation(text)

                    if resp.usage:
                        total_input += resp.usage.prompt_tokens
                        total_output += resp.usage.completion_tokens

                    if prompt and solution and tests:
                        var_file.write_text(solution)
                        prompt_file.write_text(prompt)
                        write_test_file(var_name, tests, test_dir)
                        task_names.append(var_name)
                    else:
                        var_file.write_text(";; Parse failed")
                        prompt_file.write_text(";; Parse failed")

                except Exception as e:
                    var_file.write_text(f";; Generation failed: {e}")

                time.sleep(0.3)

            if i % 20 == 0 or i == len(seed_prompts):
                print(f"  [{i}/{len(seed_prompts)}] seeds, {len(task_names)} variations")

        cost = total_input * 0.75 / 1_000_000 + total_output * 4.50 / 1_000_000
        print(f"\nGeneration cost: ${cost:.2f}")
        print(f"Valid variations: {len(task_names)}")
    else:
        # Rebuild from files
        task_names = sorted([
            f.stem for f in cand_dir.glob("*_prompt.clj")
        ])
        task_names = [n.replace("_prompt", "") for n in task_names]
        # Filter out failed ones
        task_names = [
            n for n in task_names
            if (cand_dir / f"{n}.clj").exists()
            and not (cand_dir / f"{n}.clj").read_text().startswith(";; ")
        ]

    # ── Phase 2: Evaluate ─────────────────────────────────────────────────────

    print(f"\n{'─' * 40}")
    print(f"Evaluating {len(task_names)} variations")
    print(f"{'─' * 40}")

    passed_count = 0
    failed_count = 0
    results = {}

    for i, name in enumerate(task_names, 1):
        cand_file = cand_dir / f"{name}.clj"
        test_file = test_dir / f"{name}_test.clj"

        if not cand_file.exists() or not test_file.exists():
            results[name] = False
            failed_count += 1
            continue

        cand_code = cand_file.read_text()
        test_code = test_file.read_text()

        # Write combined file and run
        combined = cand_code + "\n\n" + test_code
        combined_file = cand_dir / f"{name}_combined.clj"
        combined_file.write_text(combined)

        try:
            proc = subprocess.run(
                ["clojure", str(combined_file)],
                capture_output=True, text=True, timeout=15
            )
            output = proc.stdout + proc.stderr
            # "0 failures, 0 errors" means pass; "N failures" with N>0 means fail
            has_test_failures = bool(re.search(r'(\d+) failures', output))
            fail_count_match = re.search(r'(\d+) failures', output)
            fail_count = int(fail_count_match.group(1)) if fail_count_match else 0
            passed = proc.returncode == 0 and fail_count == 0 and "Ran" in output
            results[name] = passed
            if passed:
                passed_count += 1
            else:
                failed_count += 1
        except subprocess.TimeoutExpired:
            results[name] = False
            failed_count += 1

        if i % 50 == 0 or i == len(task_names):
            print(f"  [{i}/{len(task_names)}] {passed_count} passed, {failed_count} failed")

    print(f"\nEvaluation: {passed_count}/{len(task_names)} passed")

    # ── Phase 3: Collect and output ───────────────────────────────────────────

    print(f"\n{'=' * 60}")
    print("Collecting verified variations")
    print(f"{'=' * 60}")

    pairs = []
    for name, passed in results.items():
        if not passed:
            continue
        prompt_file = cand_dir / f"{name}_prompt.clj"
        cand_file = cand_dir / f"{name}.clj"
        if not prompt_file.exists() or not cand_file.exists():
            continue
        prompt = prompt_file.read_text().strip()
        solution = cand_file.read_text().strip()
        if prompt.startswith("(defn") and solution.startswith("(defn"):
            pairs.append((prompt, solution))

    print(f"Verified pairs: {len(pairs)}")

    random.seed(SEED_VAL)
    random.shuffle(pairs)
    val_n = max(1, int(len(pairs) * 0.1))
    val_pairs = pairs[:val_n]
    train_pairs = pairs[val_n:]

    out_dir = ROOT / "data" / "sft"
    with open(out_dir / "evol_train.jsonl", "w") as f:
        for prompt, code in train_pairs:
            f.write(json.dumps({"messages": [
                {"role": "user", "content": prompt},
                {"role": "assistant", "content": code},
            ]}) + "\n")

    with open(out_dir / "evol_val.jsonl", "w") as f:
        for prompt, code in val_pairs:
            f.write(json.dumps({"messages": [
                {"role": "user", "content": prompt},
                {"role": "assistant", "content": code},
            ]}) + "\n")

    print(f"  evol_train.jsonl: {len(train_pairs)}")
    print(f"  evol_val.jsonl:   {len(val_pairs)}")

    # Grand total
    totals = 0
    for fname in ["train.jsonl", "val.jsonl", "multi_sample_train.jsonl", "multi_sample_val.jsonl",
                   "evol_train.jsonl", "evol_val.jsonl"]:
        p = out_dir / fname
        if p.exists():
            n = sum(1 for _ in open(p))
            totals += n
            print(f"  {fname}: {n}")
    print(f"\n  GRAND TOTAL: {totals}")
    if totals >= 2000:
        print(f"  TARGET REACHED: {totals} >= 2,000")


if __name__ == "__main__":
    main()
