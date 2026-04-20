#!/usr/bin/env python3
"""Fix loop for uncovered tasks.

Takes tasks where all baseline models failed, picks the best failing candidate,
sends to an LLM with the error message, and iterates up to 3 rounds.

Supports providers: openai (default), gemini.

Generates two types of training pairs:
  - Direct: (prompt → verified_solution)
  - Fix: (prompt + broken_code + error → fixed_code)

Usage:
    python3 scripts/fix_loop.py [--provider openai|gemini] [--dry-run] [--max-rounds N] [--limit N]
"""

import argparse
import json
import os
import re
import subprocess
import sys
import time
from pathlib import Path

from dotenv import load_dotenv

ROOT = Path(__file__).resolve().parent.parent

# Defaults (overridden by provider selection)
MAX_ROUNDS = 3
SEED = 42

SYSTEM_PROMPT = """You are an expert Clojure programmer. Your task is to fix \
a broken Clojure function implementation.

The code has been tested and failed. You will see:
1. The original function specification (prompt)
2. The broken code
3. The error message from evaluation

Fix the code. Output only the corrected `defn` form. No explanations, \
no test cases, no markdown fences."""

FIX_USER_TEMPLATE = """The following Clojure function should be implemented:

```
{prompt}
```

The current implementation is broken:

```
{broken_code}
```

Error from evaluation: {error}

Provide only the corrected `defn` form."""

DIRECT_USER_TEMPLATE = """Implement the following Clojure function:

```
{prompt}
```

Provide only the complete function implementation (`defn` form)."""


def load_env():
    """Load .env from project root."""
    env_path = ROOT / ".env"
    if not env_path.exists():
        print("Error: No .env file found")
        sys.exit(1)
    load_dotenv(env_path)


def get_client(provider):
    """Create API client based on provider. Returns (client_or_wrapper, model_name, run_id)."""
    if provider == "gemini":
        from google import genai
        api_key = os.environ.get("GEMINI_API_KEY", "")
        model = os.environ.get("GEMINI_MODEL", "gemini-3.1-pro-preview")
        if not api_key:
            print("Error: GEMINI_API_KEY not set in .env")
            sys.exit(1)
        client = genai.Client(api_key=api_key)
        run_id = f"2026-04-18-fix-loop-{model.replace('.', '-')}"
        return client, model, run_id
    else:
        from openai import OpenAI
        api_key = os.environ.get("OPENAI_API_KEY", "")
        base_url = os.environ.get("OPENAI_BASE_URL", "https://api.openai.com/v1")
        model = os.environ.get("OPENAI_MODEL", "gpt-5.4-mini-2026-03-17")
        if not api_key:
            print("Error: OPENAI_API_KEY not set in .env")
            sys.exit(1)
        client = OpenAI(api_key=api_key, base_url=base_url)
        run_id = f"2026-04-18-fix-loop-{model.replace('.', '-')}"
        return client, model, run_id


def extract_code(response_text):
    """Extract Clojure code from model response."""
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


def _usage_obj(input_tokens, output_tokens):
    """Create a simple namespace for token counting."""
    class Usage:
        pass
    u = Usage()
    u.prompt_tokens = input_tokens
    u.completion_tokens = output_tokens
    return u


def generate_fix_openai(client, model, prompt, broken_code, error_msg):
    """Call OpenAI-compatible API to fix a broken candidate."""
    user_msg = FIX_USER_TEMPLATE.format(
        prompt=prompt, broken_code=broken_code, error=error_msg,
    )
    resp = client.chat.completions.create(
        model=model,
        messages=[
            {"role": "system", "content": SYSTEM_PROMPT},
            {"role": "user", "content": user_msg},
        ],
        temperature=0.3,
        max_completion_tokens=1024,
    )
    usage = _usage_obj(resp.usage.prompt_tokens, resp.usage.completion_tokens)
    return extract_code(resp.choices[0].message.content), usage


def generate_direct_openai(client, model, prompt):
    """Call OpenAI-compatible API for direct generation."""
    user_msg = DIRECT_USER_TEMPLATE.format(prompt=prompt)
    resp = client.chat.completions.create(
        model=model,
        messages=[
            {"role": "system", "content": SYSTEM_PROMPT.replace("fix a broken", "implement a")},
            {"role": "user", "content": user_msg},
        ],
        temperature=0.3,
        max_completion_tokens=1024,
    )
    usage = _usage_obj(resp.usage.prompt_tokens, resp.usage.completion_tokens)
    return extract_code(resp.choices[0].message.content), usage


def _call_gemini(client, model, system_prompt, user_msg):
    """Shared Gemini call with proper thinking token handling."""
    from google.genai import types

    contents = [
        types.Content(role="user", parts=[types.Part.from_text(text=user_msg)]),
    ]
    config = types.GenerateContentConfig(
        system_instruction=system_prompt,
        temperature=0.3,
        max_output_tokens=16384,
    )

    # Collect only non-thought text chunks
    full_text = ""
    resp_obj = None
    for chunk in client.models.generate_content_stream(
        model=model, contents=contents, config=config,
    ):
        # chunk.candidates[0].content.parts may contain thought parts
        if chunk.candidates:
            for part in chunk.candidates[0].content.parts:
                if hasattr(part, 'thought') and part.thought:
                    continue  # skip thinking output
                if part.text:
                    full_text += part.text
        resp_obj = chunk

    # Extract usage
    usage = _usage_obj(0, 0)
    if resp_obj and hasattr(resp_obj, 'usage_metadata'):
        um = resp_obj.usage_metadata
        usage.prompt_tokens = getattr(um, 'prompt_token_count', 0) or 0
        usage.completion_tokens = getattr(um, 'candidates_token_count', 0) or 0

    return extract_code(full_text), usage


def generate_fix_gemini(client, model, prompt, broken_code, error_msg):
    """Call Gemini API to fix a broken candidate."""
    user_msg = FIX_USER_TEMPLATE.format(
        prompt=prompt, broken_code=broken_code, error=error_msg,
    )
    return _call_gemini(client, model, SYSTEM_PROMPT, user_msg)


def generate_direct_gemini(client, model, prompt):
    """Call Gemini API for direct generation."""
    user_msg = DIRECT_USER_TEMPLATE.format(prompt=prompt)
    return _call_gemini(
        client, model,
        SYSTEM_PROMPT.replace("fix a broken", "implement a"),
        user_msg,
    )


def generate_fix(client, model, provider, prompt, broken_code, error_msg):
    if provider == "gemini":
        return generate_fix_gemini(client, model, prompt, broken_code, error_msg)
    return generate_fix_openai(client, model, prompt, broken_code, error_msg)


def generate_direct(client, model, provider, prompt):
    if provider == "gemini":
        return generate_direct_gemini(client, model, prompt)
    return generate_direct_openai(client, model, prompt)


def create_run_manifest(run_id, model, task_ids):
    """Create a run manifest EDN for the fix-loop candidates."""
    manifest = (
        '{:task-ids [' +
        ' '.join(f'"{tid}"' for tid in sorted(task_ids)) + ']\n'
        ' :policy {:kind :fix-loop}\n'
        f' :tasks-file "benchmark/tasks-v0.edn"\n'
        ' :prompting {:template :fix-loop-v1 :temperature 0.3 :top-p 0.95 :samples 1}\n'
        ' :executor {:kind :local-process :isolation :task-subprocess :network :none}\n'
        f' :created-at "{time.strftime("%Y-%m-%dT%H:%M:%S.000000Z")}"\n'
        f' :run-id "{run_id}"\n'
        ' :benchmark-version :clj-bench/v1\n'
        f' :model {{:provider :manual :id "{model}"}}}}\n'
    )
    manifest_path = ROOT / "benchmark" / "runs" / f"{run_id}.edn"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(manifest)
    return manifest_path


def evaluate_run(manifest_path):
    """Run the Clojure evaluator on the fix-loop candidates."""
    cmd = ["clojure", "-M:bench", "evaluate", str(manifest_path)]
    print(f"\n  Evaluating: {' '.join(cmd)}")
    result = subprocess.run(cmd, capture_output=True, text=True, timeout=600)
    if result.returncode != 0:
        print(f"  Evaluation error: {result.stderr[-500:]}")
    return result


def load_results_for_run(run_id):
    """Load evaluation results for a run. Returns dict: task_id → result."""
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
        notes_val = r[Keyword("notes")]
        notes = str(notes_val) if notes_val is not None else None
        results[task_id] = {"outcome": outcome, "notes": notes}
    return results


def main():
    parser = argparse.ArgumentParser(description="Fix loop for uncovered tasks")
    parser.add_argument("--provider", default="openai", choices=["openai", "gemini"],
                        help="API provider to use")
    parser.add_argument("--dry-run", action="store_true", help="Show plan without calling API")
    parser.add_argument("--max-rounds", type=int, default=MAX_ROUNDS)
    parser.add_argument("--limit", type=int, default=None, help="Limit to N tasks")
    args = parser.parse_args()

    load_env()
    client, model, run_id = get_client(args.provider)

    print("=" * 60)
    print(f"Fix loop for uncovered tasks — {args.provider}")
    print("=" * 60)

    # Load uncovered tasks
    uncovered_path = ROOT / "data" / "sft" / "uncovered_tasks.json"
    if not uncovered_path.exists():
        print("\nError: Run extract_training_data.py first to generate uncovered_tasks.json")
        sys.exit(1)

    with open(uncovered_path) as f:
        uncovered = json.loads(f.read())

    if not uncovered:
        print("\nNo uncovered tasks — all tasks have verified solutions!")
        return

    if args.limit:
        uncovered = uncovered[:args.limit]

    print(f"\nUncovered tasks: {len(uncovered)}")
    print(f"Max rounds:      {args.max_rounds}")
    print(f"Model:           {model}")
    print(f"Run ID:          {run_id}")

    # Set up candidate output directory
    cand_dir = ROOT / "benchmark" / "candidates" / run_id
    cand_dir.mkdir(parents=True, exist_ok=True)

    train_path = ROOT / "data" / "sft" / "train.jsonl"
    val_path = ROOT / "data" / "sft" / "val.jsonl"

    if args.dry_run:
        print("\n[DRY RUN] Would process:")
        for t in uncovered:
            has_fail = t.get("best_failing")
            source = "HE" if "humaneval" in t["task_id"] else "MB"
            print(f"  {t['task_id']} ({source}) — "
                  f"{'has failing candidate' if has_fail else 'no failing candidate'}")
        return

    import random
    random.seed(SEED)

    # Assign uncovered tasks to train/val (same stratification)
    humaneval_uncovered = [t for t in uncovered if "humaneval" in t["task_id"]]
    mbpp_uncovered = [t for t in uncovered if "mbpp" in t["task_id"]]
    random.shuffle(humaneval_uncovered)
    random.shuffle(mbpp_uncovered)

    val_tasks = humaneval_uncovered[:max(1, int(len(humaneval_uncovered) * 0.1))] + \
                mbpp_uncovered[:max(1, int(len(mbpp_uncovered) * 0.1))]
    val_task_ids = {t["task_id"] for t in val_tasks}

    # Track all training pairs generated
    new_direct_pairs = []
    new_fix_pairs = []
    total_input_tokens = 0
    total_output_tokens = 0

    # ── Fix loop ─────────────────────────────────────────────────────────────

    remaining = {t["task_id"]: t for t in uncovered}

    for round_num in range(1, args.max_rounds + 1):
        if not remaining:
            break

        print(f"\n{'─' * 40}")
        print(f"Round {round_num}/{args.max_rounds}: {len(remaining)} tasks")
        print(f"{'─' * 40}")

        round_tasks = list(remaining.values())

        for i, task in enumerate(round_tasks, 1):
            task_id = task["task_id"]
            prompt_path = ROOT / task["prompt_path"]
            prompt_text = prompt_path.read_text()
            prompt_stem = prompt_path.stem

            is_val = task_id in val_task_ids
            best_fail = task.get("best_failing")

            if best_fail and round_num == 1:
                broken_code = best_fail["code"]
                error_kind = best_fail["error_kind"]
                notes = best_fail["notes"]
                error_msg = f"{error_kind}: {notes}"

                print(f"  [{i}/{len(round_tasks)}] {task_id} — fixing ({best_fail['model']})")

                try:
                    fixed_code, usage = generate_fix(
                        client, model, args.provider, prompt_text, broken_code, error_msg
                    )
                    total_input_tokens += usage.prompt_tokens
                    total_output_tokens += usage.completion_tokens
                except Exception as e:
                    print(f"    API error: {e}")
                    continue
            else:
                print(f"  [{i}/{len(round_tasks)}] {task_id} — direct generation")

                try:
                    fixed_code, usage = generate_direct(
                        client, model, args.provider, prompt_text
                    )
                    total_input_tokens += usage.prompt_tokens
                    total_output_tokens += usage.completion_tokens
                except Exception as e:
                    print(f"    API error: {e}")
                    continue

            # Save candidate
            cand_file = cand_dir / f"{prompt_stem}.clj"
            cand_file.write_text(fixed_code)
            print(f"    → saved ({len(fixed_code)} chars)")

            # Store fix-pair training data
            if best_fail and round_num == 1:
                new_fix_pairs.append({
                    "task_id": task_id,
                    "is_val": is_val,
                    "messages": [
                        {"role": "user", "content": FIX_USER_TEMPLATE.format(
                            prompt=prompt_text,
                            broken_code=broken_code,
                            error=error_msg,
                        )},
                        {"role": "assistant", "content": fixed_code},
                    ],
                })

            time.sleep(0.3)

        # Evaluate this round
        task_ids = list(remaining.keys())
        manifest_path = create_run_manifest(run_id, model, task_ids)

        # Clear previous round results for this run if re-evaluating
        results_dir = ROOT / "benchmark" / "results" / run_id
        if round_num > 1 and results_dir.exists():
            import shutil
            shutil.rmtree(results_dir)

        evaluate_run(manifest_path)

        # Check results
        results = load_results_for_run(run_id)
        passed = set()
        still_failing = {}

        for task_id in remaining:
            if task_id in results and results[task_id]["outcome"] == ":pass":
                passed.add(task_id)
            else:
                still_failing[task_id] = remaining[task_id]
                # Update best_failing for next round
                prompt_path = ROOT / remaining[task_id]["prompt_path"]
                prompt_stem = prompt_path.stem
                cand_file = cand_dir / f"{prompt_stem}.clj"
                if cand_file.exists():
                    new_code = cand_file.read_text()
                    r = results.get(task_id, {})
                    still_failing[task_id]["best_failing"] = {
                        "model": model,
                        "run_id": run_id,
                        "code": new_code,
                        "error_kind": "unknown",
                        "notes": r.get("notes", "evaluation failed"),
                        "syntax_ok": True,
                        "kondo_ok": True,
                    }

        print(f"\n  Round {round_num} results: {len(passed)} passed, {len(still_failing)} still failing")

        # Create direct pairs for newly passing tasks
        for task_id in passed:
            prompt_path = ROOT / remaining[task_id]["prompt_path"]
            prompt_text = prompt_path.read_text()
            prompt_stem = prompt_path.stem
            cand_file = cand_dir / f"{prompt_stem}.clj"
            solution = cand_file.read_text()
            is_val = task_id in val_task_ids

            new_direct_pairs.append({
                "task_id": task_id,
                "is_val": is_val,
                "messages": [
                    {"role": "user", "content": prompt_text},
                    {"role": "assistant", "content": solution},
                ],
            })

        remaining = still_failing

    # ── Write final output ───────────────────────────────────────────────────

    train_lines = []
    val_lines = []

    for pair in new_direct_pairs:
        line = json.dumps({"messages": pair["messages"]})
        if pair["is_val"]:
            val_lines.append(line)
        else:
            train_lines.append(line)

    for pair in new_fix_pairs:
        line = json.dumps({"messages": pair["messages"]})
        if pair["is_val"]:
            val_lines.append(line)
        else:
            train_lines.append(line)

    with open(train_path, "a") as f:
        for line in train_lines:
            f.write(line + "\n")

    with open(val_path, "a") as f:
        for line in val_lines:
            f.write(line + "\n")

    # ── Final stats ──────────────────────────────────────────────────────────

    total_remaining = len(remaining)
    total_fixed = len(new_direct_pairs)
    total_fix_pairs = len(new_fix_pairs)

    print(f"\n{'=' * 60}")
    print(f"Fix loop complete — {args.provider}")
    print(f"{'=' * 60}")
    print(f"  Tasks fixed:        {total_fixed}")
    print(f"  Fix pairs created:  {total_fix_pairs}")
    print(f"  Still uncovered:    {total_remaining}")
    print(f"  New train pairs:    {len(train_lines)}")
    print(f"  New val pairs:      {len(val_lines)}")
    print(f"  Input tokens:       {total_input_tokens:,}")
    print(f"  Output tokens:      {total_output_tokens:,}")

    if args.provider == "gemini":
        # Gemini 2.5 Pro pricing (approximate)
        input_cost = total_input_tokens * 1.25 / 1_000_000
        output_cost = total_output_tokens * 10.00 / 1_000_000
    else:
        input_cost = total_input_tokens * 0.75 / 1_000_000
        output_cost = total_output_tokens * 4.50 / 1_000_000
    print(f"  Estimated cost:     ${input_cost + output_cost:.2f}")

    final_train = sum(1 for _ in open(train_path))
    final_val = sum(1 for _ in open(val_path))
    print(f"\n  Final train.jsonl:  {final_train} pairs")
    print(f"  Final val.jsonl:    {final_val} pairs")
    print(f"  Total training:     {final_train + final_val} pairs")

    if total_remaining > 0:
        print(f"\n  Still uncovered tasks:")
        for tid in sorted(remaining.keys())[:10]:
            print(f"    {tid}")
        if total_remaining > 10:
            print(f"    ... and {total_remaining - 10} more")


if __name__ == "__main__":
    main()
