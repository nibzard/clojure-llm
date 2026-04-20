#!/usr/bin/env python3
"""Baseline runner for clj-bench benchmark.

Reads task prompts from benchmark/prompts/multipl-e/, sends each to an
OpenAI-compatible API for code generation, and saves candidates to
benchmark/candidates/{run-id}/.

Usage:
    python scripts/run_baseline.py [--run-id RUN_ID] [--condition CONDITION]
                                    [--limit N] [--dry-run]

Requires a .env file in the project root with API credentials.
"""

import argparse
import json
import os
import re
import sys
import time
from pathlib import Path

from dotenv import load_dotenv
from openai import OpenAI
import anthropic

# Project root
ROOT = Path(__file__).resolve().parent.parent
PROMPTS_DIR = ROOT / "benchmark" / "prompts" / "multipl-e"
CANDIDATES_DIR = ROOT / "benchmark" / "candidates"

# Direct generation system prompt (matches .pi/prompts/benchmark-direct.md)
SYSTEM_PROMPT = """You are an expert Clojure programmer. Your task is to \
implement a Clojure function according to the given specification.

Guidelines:
- Write pure, functional code when possible
- Use Clojure's core library functions effectively
- Handle nil and empty inputs appropriately
- Follow the Clojure style guide (kebab-case, ? for predicates, etc.)
- Avoid mutable state unless the problem explicitly requires it
- Include comments only for complex logic

Output only the function implementation. No test cases, no example usage, \
no explanatory text, no namespace declarations. The function should be ready \
to evaluate in a Clojure REPL."""

USER_TEMPLATE = """Implement the following Clojure function:

```
{prompt}
```

Provide only the complete function implementation (`defn` form)."""


def load_env():
    """Load .env from project root."""
    env_path = ROOT / ".env"
    if not env_path.exists():
        print(f"Error: No .env file found at {env_path}")
        print("Copy .env.example to .env and fill in API credentials.")
        sys.exit(1)
    load_dotenv(env_path)


def get_prompt_files():
    """Return sorted list of prompt .clj files."""
    files = sorted(PROMPTS_DIR.glob("*.clj"))
    if not files:
        print(f"Error: No prompt files found in {PROMPTS_DIR}")
        sys.exit(1)
    return files


def extract_entrypoint(prompt_text):
    """Extract function name from (defn name ...) in prompt."""
    m = re.search(r'\(defn\s+([\w-]+)', prompt_text)
    return m.group(1) if m else "unknown"


def extract_code(response_text):
    """Extract Clojure code from model response.

    Models may wrap code in markdown blocks. Try to extract just the defn form.
    """
    text = response_text.strip()

    # Try to find code in markdown block
    code_block = re.search(r'```(?:clojure|clj)?\s*\n(.*?)```', text, re.DOTALL)
    if code_block:
        text = code_block.group(1).strip()

    # If it starts with (defn, use as-is
    if text.startswith("(defn"):
        return text

    # Try to find the defn form
    match = re.search(r'(\(defn\s+.*)', text, re.DOTALL)
    if match:
        candidate = match.group(1)
        # Try to balance parens
        depth = 0
        for i, ch in enumerate(candidate):
            if ch == '(':
                depth += 1
            elif ch == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1]

    return text


def run_anthropic(condition, run_id, limit=None, dry_run=False):
    """Run baseline generation using Anthropic API."""
    load_env()

    api_key = os.environ.get("ANTHROPIC_API_KEY", "")
    model = os.environ.get("ANTHROPIC_MODEL", "claude-opus-4-7")

    if not api_key:
        print("Error: ANTHROPIC_API_KEY not set in .env")
        sys.exit(1)

    client = anthropic.Anthropic(api_key=api_key)

    prompt_files = get_prompt_files()
    if limit:
        prompt_files = prompt_files[:limit]

    out_dir = CANDIDATES_DIR / run_id
    if not dry_run:
        out_dir.mkdir(parents=True, exist_ok=True)

    print(f"Condition: {condition}")
    print(f"Run ID:    {run_id}")
    print(f"Model:     {model}")
    print(f"Tasks:     {len(prompt_files)}")
    print(f"Output:    {out_dir}")
    print()

    total_input_tokens = 0
    total_output_tokens = 0
    successes = 0
    failures = 0

    for i, prompt_file in enumerate(prompt_files, 1):
        task_name = prompt_file.stem
        prompt_text = prompt_file.read_text()
        entrypoint = extract_entrypoint(prompt_text)

        print(f"[{i}/{len(prompt_files)}] {task_name} (fn: {entrypoint})")

        if dry_run:
            print(f"  [DRY RUN] Would send to {model}")
            continue

        user_msg = USER_TEMPLATE.format(prompt=prompt_text)

        try:
            resp = client.messages.create(
                model=model,
                max_tokens=1024,
                system=SYSTEM_PROMPT,
                messages=[{"role": "user", "content": user_msg}],
                temperature=0.2,
            )

            code = extract_code(resp.content[0].text)
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(code)

            total_input_tokens += resp.usage.input_tokens
            total_output_tokens += resp.usage.output_tokens

            successes += 1
            print(f"  -> OK ({len(code)} chars)")

        except Exception as e:
            failures += 1
            print(f"  -> ERROR: {e}")
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(f";; Generation failed: {e}")

        # Rate limit for Anthropic
        time.sleep(0.5)

    print()
    print("=" * 50)
    print(f"Done. {successes} successes, {failures} failures")
    print(f"Input tokens:  {total_input_tokens:,}")
    print(f"Output tokens: {total_output_tokens:,}")

    pricing = {
        "claude-opus-4-7": (15.00, 75.00),
        "claude-opus-4-6": (15.00, 75.00),
        "claude-sonnet-4-6": (3.00, 15.00),
    }
    input_price, output_price = pricing.get(model, (15.00, 75.00))
    input_cost = total_input_tokens * input_price / 1_000_000
    output_cost = total_output_tokens * output_price / 1_000_000
    print(f"Estimated cost: ${input_cost + output_cost:.4f}")
    print(f"  Input:  ${input_cost:.4f}")
    print(f"  Output: ${output_cost:.4f}")


# Condition → env var prefix for OpenAI-compatible providers
OPENAI_CONDITION_ENV = {
    "B": "OPENAI",
    "C": "QWEN",
}


def _get_openai_creds(condition):
    """Read OpenAI-compatible API credentials based on condition label."""
    prefix = OPENAI_CONDITION_ENV.get(condition, "OPENAI")
    api_key = os.environ.get(f"{prefix}_API_KEY", "")
    base_url = os.environ.get(f"{prefix}_BASE_URL", "https://api.openai.com/v1")
    model = os.environ.get(f"{prefix}_MODEL", "gpt-4o")

    if not api_key:
        print(f"Error: {prefix}_API_KEY not set in .env (condition {condition})")
        sys.exit(1)

    return api_key, base_url, model


def _needs_generation(out_dir, prompt_file):
    """Check if a task still needs generation (no valid candidate exists)."""
    existing = out_dir / f"{prompt_file.stem}.clj"
    if not existing.exists():
        return True
    content = existing.read_text()
    return content.startswith(";; Generation failed")


def run_baseline(condition, run_id, limit=None, dry_run=False, resume=False):
    """Run baseline generation for all tasks."""
    load_env()

    api_key, base_url, model = _get_openai_creds(condition)
    client = OpenAI(api_key=api_key, base_url=base_url)

    prompt_files = get_prompt_files()
    if limit:
        prompt_files = prompt_files[:limit]

    out_dir = CANDIDATES_DIR / run_id
    if not dry_run:
        out_dir.mkdir(parents=True, exist_ok=True)

    if resume:
        original_count = len(prompt_files)
        prompt_files = [f for f in prompt_files if _needs_generation(out_dir, f)]
        skipped = original_count - len(prompt_files)
        if skipped:
            print(f"Resuming: skipping {skipped} already-completed tasks")

    print(f"Condition: {condition}")
    print(f"Run ID:    {run_id}")
    print(f"Model:     {model}")
    print(f"Tasks:     {len(prompt_files)}")
    print(f"Output:    {out_dir}")
    print()

    total_input_tokens = 0
    total_output_tokens = 0
    successes = 0
    failures = 0

    for i, prompt_file in enumerate(prompt_files, 1):
        task_name = prompt_file.stem  # e.g. HumanEval_0_has_close_elements
        prompt_text = prompt_file.read_text()
        entrypoint = extract_entrypoint(prompt_text)

        print(f"[{i}/{len(prompt_files)}] {task_name} (fn: {entrypoint})")

        if dry_run:
            print(f"  [DRY RUN] Would send to {model}")
            continue

        user_msg = USER_TEMPLATE.format(prompt=prompt_text)

        try:
            resp = client.chat.completions.create(
                model=model,
                messages=[
                    {"role": "system", "content": SYSTEM_PROMPT},
                    {"role": "user", "content": user_msg},
                ],
                temperature=0.2,
                max_completion_tokens=1024,
            )

            code = extract_code(resp.choices[0].message.content)

            # Write candidate
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(code)

            # Token accounting
            usage = resp.usage
            if usage:
                total_input_tokens += usage.prompt_tokens
                total_output_tokens += usage.completion_tokens

            successes += 1
            print(f"  -> OK ({len(code)} chars)")

        except Exception as e:
            failures += 1
            print(f"  -> ERROR: {e}")
            # Write empty file so evaluator can count it as crash
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(f";; Generation failed: {e}")

        # Rate limit: ~3 requests/sec to stay well under limits
        time.sleep(0.35)

    print()
    print("=" * 50)
    print(f"Done. {successes} successes, {failures} failures")
    print(f"Input tokens:  {total_input_tokens:,}")
    print(f"Output tokens: {total_output_tokens:,}")

    # Cost estimate
    pricing = {
        "gpt-5.4": (2.50, 15.00),
        "gpt-5.4-mini": (0.75, 4.50),
        "gpt-5.4-mini-2026-03-17": (0.75, 4.50),
        "claude-opus-4-7": (15.00, 75.00),
        "claude-opus-4-6": (15.00, 75.00),
        "claude-sonnet-4-6": (3.00, 15.00),
    }
    input_price, output_price = pricing.get(model, (0.75, 4.50))
    input_cost = total_input_tokens * input_price / 1_000_000
    output_cost = total_output_tokens * output_price / 1_000_000
    print(f"Estimated cost: ${input_cost + output_cost:.4f}")
    print(f"  Input:  ${input_cost:.4f}")
    print(f"  Output: ${output_cost:.4f}")


GEMINI_PRICING = {
    "gemini-2.5-pro": (1.25, 10.00),
    "gemini-2.5-flash": (0.15, 3.50),
    "gemini-3.1-pro-preview": (1.25, 10.00),
}


def run_gemini(condition, run_id, limit=None, dry_run=False, resume=False):
    """Run baseline generation using native Google GenAI SDK with low thinking."""
    from google import genai
    from google.genai import types

    api_key = os.environ.get("GEMINI_API_KEY", "")
    model = os.environ.get("GEMINI_MODEL", "gemini-3.1-pro-preview")

    if not api_key:
        print("Error: GEMINI_API_KEY not set in .env")
        sys.exit(1)

    client = genai.Client(api_key=api_key)

    prompt_files = get_prompt_files()
    if limit:
        prompt_files = prompt_files[:limit]

    out_dir = CANDIDATES_DIR / run_id
    if not dry_run:
        out_dir.mkdir(parents=True, exist_ok=True)

    if resume:
        original_count = len(prompt_files)
        prompt_files = [f for f in prompt_files if _needs_generation(out_dir, f)]
        skipped = original_count - len(prompt_files)
        if skipped:
            print(f"Resuming: skipping {skipped} already-completed tasks")

    print(f"Condition: {condition}")
    print(f"Run ID:    {run_id}")
    print(f"Provider:  gemini")
    print(f"Model:     {model}")
    print(f"Thinking:  low")
    print(f"Tasks:     {len(prompt_files)}")
    print(f"Output:    {out_dir}")
    print()

    total_input_tokens = 0
    total_output_tokens = 0
    successes = 0
    failures = 0

    for i, prompt_file in enumerate(prompt_files, 1):
        task_name = prompt_file.stem
        prompt_text = prompt_file.read_text()
        entrypoint = extract_entrypoint(prompt_text)

        print(f"[{i}/{len(prompt_files)}] {task_name} (fn: {entrypoint})")

        if dry_run:
            print(f"  [DRY RUN] Would send to {model}")
            continue

        user_msg = USER_TEMPLATE.format(prompt=prompt_text)

        try:
            contents = [
                types.Content(role="user", parts=[types.Part.from_text(text=user_msg)]),
            ]
            config = types.GenerateContentConfig(
                system_instruction=SYSTEM_PROMPT,
                temperature=0.2,
                max_output_tokens=16384,
                thinking_config=types.ThinkingConfig(thinking_level="low"),
            )

            # Stream and collect non-thought text
            full_text = ""
            resp_obj = None
            for chunk in client.models.generate_content_stream(
                model=model, contents=contents, config=config,
            ):
                if chunk.candidates:
                    for part in chunk.candidates[0].content.parts:
                        if hasattr(part, "thought") and part.thought:
                            continue
                        if part.text:
                            full_text += part.text
                resp_obj = chunk

            code = extract_code(full_text)
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(code)

            if resp_obj and hasattr(resp_obj, "usage_metadata"):
                um = resp_obj.usage_metadata
                total_input_tokens += getattr(um, "prompt_token_count", 0) or 0
                total_output_tokens += getattr(um, "candidates_token_count", 0) or 0

            successes += 1
            print(f"  -> OK ({len(code)} chars)")

        except Exception as e:
            failures += 1
            print(f"  -> ERROR: {e}")
            out_file = out_dir / f"{task_name}.clj"
            out_file.write_text(f";; Generation failed: {e}")

        # Rate limit
        time.sleep(0.35)

    print()
    print("=" * 50)
    print(f"Done. {successes} successes, {failures} failures")
    print(f"Input tokens:  {total_input_tokens:,}")
    print(f"Output tokens: {total_output_tokens:,}")

    input_price, output_price = GEMINI_PRICING.get(model, (1.25, 10.00))
    input_cost = total_input_tokens * input_price / 1_000_000
    output_cost = total_output_tokens * output_price / 1_000_000
    print(f"Estimated cost: ${input_cost + output_cost:.4f}")
    print(f"  Input:  ${input_cost:.4f}")
    print(f"  Output: ${output_cost:.4f}")


def main():
    parser = argparse.ArgumentParser(description="Run clj-bench baseline")
    parser.add_argument("--run-id", default=None,
                        help="Run ID (default: auto-generated from date + model)")
    parser.add_argument("--condition", default="B",
                        help="Condition label: A=Opus, B=GPT, C=Qwen")
    parser.add_argument("--provider", default="openai",
                        choices=["openai", "anthropic", "gemini"],
                        help="API provider to use")
    parser.add_argument("--limit", type=int, default=None,
                        help="Limit to first N tasks")
    parser.add_argument("--dry-run", action="store_true",
                        help="Show what would be done without calling API")
    parser.add_argument("--resume", action="store_true",
                        help="Skip tasks that already have valid candidates")
    args = parser.parse_args()

    load_env()

    if args.provider == "anthropic":
        model = os.environ.get("ANTHROPIC_MODEL", "claude-opus-4-7")
    elif args.provider == "gemini":
        model = os.environ.get("GEMINI_MODEL", "gemini-3.1-pro-preview")
    else:
        _, _, model = _get_openai_creds(args.condition)

    if not args.run_id:
        date_str = time.strftime("%Y-%m-%d")
        model_slug = model.replace("/", "-").replace(".", "-")
        args.run_id = f"{date_str}-{model_slug}-direct"

    if args.provider == "anthropic":
        run_anthropic(args.condition, args.run_id, args.limit, args.dry_run)
    elif args.provider == "gemini":
        run_gemini(args.condition, args.run_id, args.limit, args.dry_run, args.resume)
    else:
        run_baseline(args.condition, args.run_id, args.limit, args.dry_run, args.resume)


if __name__ == "__main__":
    main()
