#!/usr/bin/env python3
"""Extract 4clojure problems, generate solutions, and verify them.

Clones the 4clojure repo, parses problems from data_set.clj,
generates solutions via GPT-5.4-mini, evaluates against tests,
and outputs verified training pairs as JSONL.

Only function-type problems (where __ appears in call position) are kept.
Value-type problems (where __ is a literal) are skipped.

Usage:
    python3 scripts/extract_4clojure.py
    python3 scripts/extract_4clojure.py --limit 10  # test run
    python3 scripts/extract_4clojure.py --skip-gen  # just collect existing
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
SEED = 42
RUN_ID = "2026-04-18-4clojure"

SYSTEM_PROMPT = """You are an expert Clojure programmer. Your task is to \
implement a Clojure function according to the given specification.

The function must pass all the provided test cases. Some problems restrict \
certain functions — do not use them.

Output ONLY the `defn` form. No test cases, no example usage, \
no explanatory text, no markdown fences, no namespace declarations."""

USER_TEMPLATE = """Implement a Clojure function that solves this problem:

**Problem:** {title}

**Description:** {description}

**Function name:** `{fn_name}`
**Parameters:** {params}

{restricted_line}

The function must pass these tests:
```
{tests}
```

Provide ONLY the complete `defn` form for `{fn_name}`."""


def load_env():
    env_path = ROOT / ".env"
    load_dotenv(str(env_path))


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


def title_to_fn_name(title):
    """Convert problem title to kebab-case function name."""
    name = title.lower()
    name = re.sub(r"[^a-z0-9\s-]", "", name)
    name = re.sub(r"[\s]+", "-", name)
    name = re.sub(r"-+", "-", name)
    name = name.strip("-")
    return f"problem-{name}"


def parse_problems(data_set_path):
    """Parse 4clojure problems from data_set.clj.

    The file contains insert! calls with EDN maps. We extract each map
    by finding {:_id ...} blocks with brace matching.
    """
    from edn_format import loads

    with open(data_set_path) as f:
        content = f.read()

    maps = []
    i = 0
    while True:
        start = content.find("{:_id", i)
        if start == -1:
            break
        depth = 0
        for j in range(start, len(content)):
            if content[j] == '{':
                depth += 1
            elif content[j] == '}':
                depth -= 1
                if depth == 0:
                    maps.append(content[start:j + 1])
                    i = j + 1
                    break
        else:
            i = start + 1

    problems = []
    for m in maps:
        try:
            parsed = loads(m)
            problems.append(parsed)
        except Exception as e:
            print(f"  Warning: failed to parse problem map: {e}")

    return problems


def classify_and_convert(problem):
    """Convert a 4clojure problem to our prompt/test format.

    Returns (fn_name, params_str, prompt, test_code, is_function_type) or None.
    """
    from edn_format import Keyword

    raw_id = problem[Keyword("_id")]
    # Skip non-problem maps (e.g. {:_id "problems"})
    if not isinstance(raw_id, int):
        return None
    pid = raw_id
    title = str(problem.get(Keyword("title"), f"Problem {pid}"))
    description = str(problem.get(Keyword("description"), ""))
    tests_raw = problem.get(Keyword("tests"), [])
    restricted = problem.get(Keyword("restricted"), [])

    if not tests_raw:
        return None

    tests = [str(t) for t in tests_raw]
    tags = [str(t) for t in (problem.get(Keyword("tags"), []))]

    fn_name = title_to_fn_name(title)

    # Classify: function-type or value-type
    # Function-type: tests have (__ args...) — __ in call position
    # Value-type: tests have (= __ value) — __ as a value
    fn_pattern = re.compile(r'\(__\s')
    is_function = any(fn_pattern.search(t) for t in tests)

    if not is_function:
        return None  # Skip value-type problems

    # Determine parameter count from the first test that calls __
    max_arity = 0
    for t in tests:
        m = re.search(r'\(__(.+?)\)', t)
        if m:
            # Count top-level items in the argument list
            args_str = m.group(1).strip()
            if not args_str:
                arity = 0
            else:
                depth = 0
                arity = 1
                for ch in args_str:
                    if ch in '([{':
                        depth += 1
                    elif ch in ')]}':
                        depth -= 1
                    elif ch == ' ' and depth == 0:
                        arity += 1
            max_arity = max(max_arity, arity)

    # Generate parameter names
    param_names = ["a", "b", "c", "d", "e", "f"]
    params = param_names[:max_arity]
    params_str = " ".join(params)

    # Convert tests: replace __ with fn_name, wrap in (is ...)
    converted_tests = []
    for t in tests:
        # Replace (__ args...) with (fn_name args...)
        converted = re.sub(r'\(__', f"({fn_name}", t)
        converted_tests.append(f"  (is (= {converted}))")

    test_code = "(require '[clojure.test :refer [deftest is run-tests]])\n\n"
    test_code += f"(deftest test-{fn_name}\n"
    test_code += "\n".join(converted_tests)
    test_code += f")\n\n(run-tests)"

    # Build prompt
    restricted_list = [str(r) for r in restricted] if restricted else []
    restricted_line = ""
    if restricted_list:
        restricted_line = f"**Restricted (do NOT use):** {', '.join(restricted_list)}"

    prompt = USER_TEMPLATE.format(
        title=title,
        description=description,
        fn_name=fn_name,
        params=f"[{params_str}]" if params_str else "[]",
        restricted_line=restricted_line,
        tests="\n".join(tests),
    )

    return fn_name, params_str, prompt, test_code, tags


def write_combined(fn_name, solution, test_code, combined_dir):
    """Write combined solution+test file for evaluation."""
    content = solution + "\n\n" + test_code + "\n"
    combined_file = combined_dir / f"{fn_name}_combined.clj"
    combined_file.write_text(content)
    return combined_file


def evaluate_candidate(combined_file, timeout=15):
    """Run clojure on a combined file, return True if all tests pass."""
    try:
        proc = subprocess.run(
            ["clojure", str(combined_file)],
            capture_output=True, text=True, timeout=timeout
        )
        output = proc.stdout + proc.stderr

        fail_match = re.search(r'(\d+) failures', output)
        err_match = re.search(r'(\d+) errors', output)
        fails = int(fail_match.group(1)) if fail_match else 0
        errors = int(err_match.group(1)) if err_match else 0

        return proc.returncode == 0 and fails == 0 and errors == 0 and "Ran" in output
    except subprocess.TimeoutExpired:
        return False


def main():
    parser = argparse.ArgumentParser(description="Extract 4clojure problems for SFT")
    parser.add_argument("--limit", type=int, default=None)
    parser.add_argument("--skip-gen", action="store_true")
    parser.add_argument("--difficulty", type=str, default=None,
                        choices=["elementary", "easy", "medium", "hard"],
                        help="Only include problems with this difficulty tag")
    args = parser.parse_args()

    load_env()

    api_key = os.environ.get("OPENAI_API_KEY", "")
    base_url = os.environ.get("OPENAI_BASE_URL", "https://api.openai.com/v1")
    model = os.environ.get("OPENAI_MODEL", "gpt-5.4-mini-2026-03-17")

    if not api_key:
        print("Error: OPENAI_API_KEY not set")
        sys.exit(1)

    client = OpenAI(api_key=api_key, base_url=base_url)

    print("=" * 60)
    print("4clojure problem extraction")
    print("=" * 60)

    # Clone repo if needed
    repo_path = Path("/tmp/4clojure")
    data_set_path = repo_path / "src" / "foreclojure" / "data_set.clj"

    if not data_set_path.exists():
        print("\nCloning 4clojure repo...")
        subprocess.run(
            ["git", "clone", "--depth", "1",
             "https://github.com/4clojure/4clojure.git", str(repo_path)],
            capture_output=True, timeout=120
        )
        if not data_set_path.exists():
            print(f"Error: {data_set_path} not found after clone")
            sys.exit(1)

    # Parse problems
    problems = parse_problems(data_set_path)
    print(f"\nParsed {len(problems)} problems from data_set.clj")

    # Convert to our format
    converted = []
    skipped_value = 0
    skipped_parse = 0

    for p in problems:
        result = classify_and_convert(p)
        if result is None:
            # Check if it was value-type or parse failure
            from edn_format import Keyword
            tests_raw = p.get(Keyword("tests"), [])
            tests = [str(t) for t in tests_raw]
            if tests and not any(re.search(r'\(__\s', t) for t in tests):
                skipped_value += 1
            else:
                skipped_parse += 1
            continue

        fn_name, params_str, prompt, test_code, tags = result

        # Filter by difficulty if requested
        if args.difficulty and args.difficulty not in tags:
            continue

        converted.append({
            "fn_name": fn_name,
            "params": params_str,
            "prompt": prompt,
            "test_code": test_code,
            "tags": tags,
            "problem_id": int(p[Keyword("_id")]),
        })

    print(f"  Function-type problems: {len(converted)}")
    print(f"  Skipped (value-type):   {skipped_value}")
    print(f"  Skipped (parse fail):   {skipped_parse}")

    if args.limit:
        converted = converted[:args.limit]
        print(f"\n  Limited to: {args.limit}")

    # Output directories
    cand_dir = ROOT / "benchmark" / "candidates" / RUN_ID
    test_dir = ROOT / "benchmark" / "tests" / RUN_ID
    combined_dir = cand_dir / "combined"
    cand_dir.mkdir(parents=True, exist_ok=True)
    test_dir.mkdir(parents=True, exist_ok=True)
    combined_dir.mkdir(parents=True, exist_ok=True)

    total_input = 0
    total_output = 0

    # ── Phase 1: Generate solutions ──────────────────────────────────────────

    if not args.skip_gen:
        print(f"\n{'─' * 40}")
        print(f"Generating solutions for {len(converted)} problems")
        print(f"{'─' * 40}")

        for i, prob in enumerate(converted, 1):
            fn_name = prob["fn_name"]
            cand_file = cand_dir / f"{fn_name}.clj"

            # Resume check
            if cand_file.exists():
                code = cand_file.read_text()
                if code and not code.startswith(";; "):
                    continue

            try:
                resp = client.chat.completions.create(
                    model=model,
                    messages=[
                        {"role": "system", "content": SYSTEM_PROMPT},
                        {"role": "user", "content": prob["prompt"]},
                    ],
                    temperature=0.3,
                    max_completion_tokens=1024,
                )

                code = extract_code(resp.choices[0].message.content)
                cand_file.write_text(code)

                if resp.usage:
                    total_input += resp.usage.prompt_tokens
                    total_output += resp.usage.completion_tokens

            except Exception as e:
                cand_file.write_text(f";; Generation failed: {e}")

            time.sleep(0.3)

            if i % 10 == 0 or i == len(converted):
                print(f"  [{i}/{len(converted)}] generated")

        cost = total_input * 0.75 / 1_000_000 + total_output * 4.50 / 1_000_000
        print(f"\nGeneration cost: ${cost:.2f}")

    # ── Phase 2: Evaluate ────────────────────────────────────────────────────

    print(f"\n{'─' * 40}")
    print(f"Evaluating {len(converted)} solutions")
    print(f"{'─' * 40}")

    passed = []
    failed = 0

    for i, prob in enumerate(converted, 1):
        fn_name = prob["fn_name"]
        cand_file = cand_dir / f"{fn_name}.clj"

        if not cand_file.exists():
            failed += 1
            continue

        code = cand_file.read_text()
        if code.startswith(";; "):
            failed += 1
            continue

        combined_file = write_combined(fn_name, code, prob["test_code"], combined_dir)

        if evaluate_candidate(combined_file):
            passed.append(prob)
        else:
            failed += 1

        if i % 10 == 0 or i == len(converted):
            print(f"  [{i}/{len(converted)}] {len(passed)} passed, {failed} failed")

    print(f"\nEvaluation: {len(passed)}/{len(converted)} passed")

    # ── Phase 3: Output JSONL ────────────────────────────────────────────────

    print(f"\n{'─' * 40}")
    print("Collecting verified pairs")
    print(f"{'─' * 40}")

    pairs = []
    for prob in passed:
        fn_name = prob["fn_name"]
        cand_file = cand_dir / f"{fn_name}.clj"
        if not cand_file.exists():
            continue
        code = cand_file.read_text().strip()
        if not code.startswith("(defn"):
            continue
        pairs.append({
            "messages": [
                {"role": "user", "content": prob["prompt"]},
                {"role": "assistant", "content": code},
            ],
            "tags": prob["tags"],
            "problem_id": prob["problem_id"],
        })

    # Split train/val (stratified by difficulty)
    random.seed(SEED)
    random.shuffle(pairs)

    # Simple 90/10 split
    val_n = max(1, int(len(pairs) * 0.1))
    val_pairs = pairs[:val_n]
    train_pairs = pairs[val_n:]

    out_dir = ROOT / "data" / "sft"
    with open(out_dir / "4clojure_train.jsonl", "w") as f:
        for p in train_pairs:
            f.write(json.dumps({"messages": p["messages"]}) + "\n")

    with open(out_dir / "4clojure_val.jsonl", "w") as f:
        for p in val_pairs:
            f.write(json.dumps({"messages": p["messages"]}) + "\n")

    # Tag distribution
    tag_counts = {}
    for p in pairs:
        for t in p["tags"]:
            tag_counts[t] = tag_counts.get(t, 0) + 1

    print(f"  Verified pairs: {len(pairs)}")
    print(f"  4clojure_train.jsonl: {len(train_pairs)}")
    print(f"  4clojure_val.jsonl:   {len(val_pairs)}")
    print(f"\n  Tag distribution:")
    for tag, count in sorted(tag_counts.items()):
        print(f"    {tag}: {count}")


if __name__ == "__main__":
    main()
