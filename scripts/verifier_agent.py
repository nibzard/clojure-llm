#!/usr/bin/env python3
"""Verifier Agent Loop — inference-time retry with error feedback.

For each held-out task:
  1. Generate a Clojure candidate from the model
  2. Run detailed verification (syntax → kondo → load → tests)
  3. If failed, construct targeted error feedback and retry
  4. Repeat up to K attempts, then move to next task

Unlike blind best-of-K, each retry gets the specific error signal injected
into the prompt, so the model can fix its mistakes. This should close the
gap between pass@1 and the best-of-K ceiling more efficiently.

Usage:
    python3 scripts/verifier_agent.py --K 8
    python3 scripts/verifier_agent.py --K 4 --limit 10   # quick test
    python3 scripts/verifier_agent.py --K 8 --resume      # resume interrupted run
    python3 scripts/verifier_agent.py --K 8 --checkpoint <url>  # specific model
"""

import argparse
import json
import os
import re
import sys
import time
from pathlib import Path

from dotenv import load_dotenv
from transformers import AutoTokenizer

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT))

from training.rlvr.evaluate import evaluate_single_detailed

# Default: SFT 30B (best ceiling at 83.8%, production model for verifier deployment)
SFT_30B_CHECKPOINT = (
    "tinker://6d56c642-fed2-539d-853b-8311cc4939ed:train:0/weights/checkpoint-step-600"
)
SFT_8B_CHECKPOINT = (
    "tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600"
)

# Tokenizer for 30B model — Qwen3 family shares the same tokenizer
TOKENIZER_30B = "Qwen/Qwen3-30B-A3B-Base"
TOKENIZER_8B = "Qwen/Qwen3-8B-Base"


def load_heldout_tasks():
    """Load held-out task IDs and their prompt/test paths."""
    from edn_format import Keyword, loads

    with open(ROOT / "data" / "sft" / "heldout_task_ids.json") as f:
        heldout_ids = set(json.load(f))

    with open(ROOT / "benchmark" / "tasks-v0.edn") as f:
        tasks = loads(f.read())

    heldout = []
    for t in tasks:
        tid = str(t[Keyword("id")])
        if tid in heldout_ids:
            heldout.append({
                "task_id": tid,
                "prompt_path": str(t[Keyword("prompt-ref")][Keyword("path")]),
                "tests_path": str(t[Keyword("tests-ref")][Keyword("path")]),
                "entrypoint": str(t[Keyword("entrypoint")]),
                "source": str(t[Keyword("source")]),
            })
    return heldout


def _find_balanced_defn(text, start_pos=0):
    """Find balanced defn form starting from start_pos in text.

    Returns (code, is_balanced) tuple:
      - (code_string, True) if parens balance to depth 0
      - (code_string, False) if parens never balance (truncated/incomplete)
      - (None, False) if no (defn found at all

    Skips parens inside string literals and line comments.
    """
    defn_pos = text.find("(defn", start_pos)
    if defn_pos < 0:
        return None, False

    candidate = text[defn_pos:]
    depth = 0
    in_string = False
    i = 0
    while i < len(candidate):
        ch = candidate[i]
        if in_string:
            if ch == '\\':
                i += 2  # skip escaped char
                continue
            elif ch == '"':
                in_string = False
        else:
            if ch == ';':
                newline = candidate.find('\n', i)
                if newline < 0:
                    break
                i = newline
            elif ch == '"':
                in_string = True
            elif ch == '(':
                depth += 1
            elif ch == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1], True
        i += 1

    return candidate, False


def extract_code(prompt_text, generated_text):
    """Extract clean Clojure defn from prompt + generated text.

    Strategy 1: Search generated text only for (defn).
    Strategy 2: If not found, find defn start in prompt and extract
                from combined text starting there.

    Returns (code, raw_len, extracted_len, was_truncated).
    """
    raw_len = len(generated_text)

    code, balanced = _find_balanced_defn(generated_text)

    if code is None:
        prompt_defn_pos = prompt_text.find("(defn")
        if prompt_defn_pos >= 0:
            combined = prompt_text + generated_text
            code, balanced = _find_balanced_defn(combined, prompt_defn_pos)
        else:
            code = generated_text
            balanced = True

    extracted_len = len(code)
    was_truncated = (extracted_len < raw_len) and not balanced

    return code, raw_len, extracted_len, was_truncated


def _build_error_feedback(details, failed_code, max_code_chars=300):
    """Construct targeted error feedback from detailed verification results.

    The feedback tells the model exactly what went wrong so it can fix it.
    """
    parts = []

    if not details.get("syntax_ok"):
        parts.append(";; VERIFIER: Syntax error — the code cannot be parsed by the Clojure reader.")
        error = details.get("error", "")
        if error:
            parts.append(f";; Error details: {_truncate(error, 200)}")

    elif not details.get("kondo_ok"):
        parts.append(";; VERIFIER: clj-kondo static analysis found errors.")
        error = details.get("error", "")
        if error:
            parts.append(f";; Kondo findings: {_truncate(error, 300)}")

    elif not details.get("load_ok"):
        parts.append(";; VERIFIER: The code has valid syntax and passes lint, but fails to load.")
        error = details.get("error", "")
        if error:
            parts.append(f";; Load error: {_truncate(error, 300)}")

    elif not details.get("tests_ok"):
        if details.get("timed_out"):
            parts.append(";; VERIFIER: Tests timed out. The function may have an infinite loop or is too slow.")
        else:
            parts.append(";; VERIFIER: Code loaded successfully but tests failed.")
            error = details.get("error", "")
            # Extract specific test failure info
            fail_lines = _extract_test_failures(error)
            if fail_lines:
                parts.append(f";; Test failures:\n{fail_lines}")
            elif error:
                parts.append(f";; Test output: {_truncate(error, 400)}")

    # Include the failed code so the model can see what it produced
    if failed_code:
        snippet = _truncate(failed_code, max_code_chars)
        parts.append(f";; Your previous attempt:\n{snippet}")

    parts.append(";; Please write a corrected implementation.\n")
    return "\n".join(parts)


def _build_minimal_feedback(details):
    """One-line error signal — no failed code, no verbose output.

    Designed for small models where verbose feedback shifts the prompt
    distribution and hurts more than it helps.
    """
    if not details.get("syntax_ok"):
        return ";; Previous attempt had a syntax error. Try again.\n"
    if not details.get("kondo_ok"):
        return ";; Previous attempt had lint errors. Try again.\n"
    if details.get("timed_out"):
        return ";; Previous attempt timed out. Avoid infinite loops.\n"
    if not details.get("load_ok"):
        return ";; Previous attempt failed to load. Check dependencies.\n"
    if not details.get("tests_ok"):
        return ";; Previous attempt passed lint but failed tests. Try a different approach.\n"
    return ";; Previous attempt failed. Try again.\n"


def _extract_test_failures(output, max_lines=20):
    """Extract relevant test failure lines from test output."""
    if not output:
        return ""

    lines = []
    for line in output.splitlines():
        stripped = line.strip()
        # Capture FAIL/ERROR blocks and expected/actual diffs
        if any(marker in stripped for marker in [
            "FAIL in", "ERROR in", "expected:", "actual:", "diff:",
            "AssertionError", "ClassCast", "NullPointer", "ArithmeticException",
            "IndexOutOfBounds", "IllegalArgumentException",
        ]):
            lines.append(stripped)

    if not lines:
        return ""

    text = "\n".join(lines[:max_lines])
    if len(lines) > max_lines:
        text += f"\n;; ... ({len(lines) - max_lines} more lines)"
    return text


def _truncate(text, max_chars):
    """Truncate text with ellipsis."""
    if len(text) <= max_chars:
        return text
    return text[:max_chars] + "..."


def generate_one(sc, tokenizer, prompt, temperature, max_tokens):
    """Generate a single sample from the model."""
    from tinker import ModelInput, SamplingParams

    params = SamplingParams(
        max_tokens=max_tokens, temperature=temperature, top_p=0.95,
    )

    tokens = tokenizer.encode(prompt, add_special_tokens=True)
    model_input = ModelInput.from_ints(tokens)

    response = sc.sample(model_input, 1, params).result()
    gen_tokens = list(response.sequences[0].tokens)
    gen_text = tokenizer.decode(gen_tokens)

    return extract_code(prompt, gen_text)


def run_agent_loop(sc, tokenizer, prompt_text, test_path, args):
    """Run the verifier agent loop for a single task.

    Hybrid blind-first strategy:
      - First `blind_attempts` retries use the original prompt (pure diversity)
      - After that, append minimal error feedback (one-line signal, no failed code)

    Returns a dict with all attempt details.
    """
    attempts = []
    last_details = None

    for k in range(args.K):
        temp = min(args.temperature + k * args.temp_increase, args.max_temp)

        # Determine prompt: blind for first N attempts, then with feedback
        if k < args.blind_attempts or last_details is None:
            current_prompt = prompt_text
        else:
            feedback = _build_minimal_feedback(last_details)
            current_prompt = prompt_text + "\n\n" + feedback

        try:
            code, raw_len, extracted_len, was_truncated = generate_one(
                sc, tokenizer, current_prompt, temp, args.max_tokens,
            )
        except Exception as e:
            attempts.append({
                "k": k, "passed": False, "error": str(e),
                "temperature": temp, "code": "",
            })
            break  # Can't recover from generation errors

        # Detailed evaluation
        details = evaluate_single_detailed(code, test_path, timeout=args.test_timeout)
        passed = details["passed"]
        last_details = details

        attempt = {
            "k": k,
            "passed": passed,
            "temperature": temp,
            "raw_length": raw_len,
            "extracted_length": extracted_len,
            "was_truncated": was_truncated,
            "syntax_ok": details["syntax_ok"],
            "kondo_ok": details["kondo_ok"],
            "load_ok": details["load_ok"],
            "tests_ok": details["tests_ok"],
            "timed_out": details["timed_out"],
        }
        attempts.append(attempt)

        if passed:
            return {
                "passed": True,
                "first_attempt_passed": k == 0,
                "attempts_until_pass": k + 1,
                "attempts": attempts,
            }

    # All attempts exhausted — return best result
    best = max(
        attempts,
        key=lambda a: (
            int(a.get("tests_ok", False)) * 4
            + int(a.get("load_ok", False)) * 2
            + int(a.get("kondo_ok", False)) * 1
        ),
    )
    return {
        "passed": False,
        "first_attempt_passed": False,
        "attempts_until_pass": None,
        "attempts": attempts,
        "best_attempt": best,
    }


def _save_results(output_path, progress, args):
    """Save results to JSON file."""
    total = len(progress)
    first_pass = sum(1 for v in progress.values() if v.get("first_attempt_passed"))
    any_pass = sum(1 for v in progress.values() if v.get("passed"))

    output_path.parent.mkdir(parents=True, exist_ok=True)
    with open(output_path, "w") as f:
        json.dump({
            "mode": "verifier_agent",
            "K": args.K,
            "temperature": args.temperature,
            "temp_increase": args.temp_increase,
            "max_temp": args.max_temp,
            "max_tokens": args.max_tokens,
            "test_timeout": args.test_timeout,
            "blind_attempts": args.blind_attempts,
            "checkpoint": args.checkpoint,
            "total_tasks": total,
            "pass_at_1": first_pass,
            "agent_pass": any_pass,
            "per_task": progress,
        }, f, indent=2)


def _print_report(progress, args):
    """Print final report with comparisons."""
    total = len(progress)
    if total == 0:
        return

    first_pass = sum(1 for v in progress.values() if v.get("first_attempt_passed"))
    any_pass = sum(1 for v in progress.values() if v.get("passed"))

    # Compute per-attempt cumulative pass rate
    attempt_curve = {}
    for max_k in range(1, args.K + 1):
        count = sum(
            1 for v in progress.values()
            if v.get("attempts_until_pass") is not None
            and v["attempts_until_pass"] <= max_k
        )
        attempt_curve[max_k] = count

    print(f"\n{'=' * 60}")
    print(f"VERIFIER AGENT RESULTS ({total} held-out tasks)")
    print(f"{'=' * 60}")
    print(f"  pass@1 (first attempt):  {first_pass}/{total} = {first_pass/total*100:.1f}%")
    print(f"  agent pass (any attempt): {any_pass}/{total} = {any_pass/total*100:.1f}%")
    print(f"  lift:                     +{any_pass - first_pass} tasks ({(any_pass - first_pass)/total*100:+.1f}pp)")

    # Per-attempt curve
    print(f"\n  Agent attempt curve:")
    for k in sorted(attempt_curve.keys()):
        if k <= 8 or k == args.K:
            print(f"    attempt {k:2d}: {attempt_curve[k]}/{total} = {attempt_curve[k]/total*100:.1f}%")

    # Average attempts needed
    passing = [v for v in progress.values() if v.get("passed")]
    if passing:
        avg_attempts = sum(v["attempts_until_pass"] for v in passing) / len(passing)
        print(f"\n  Avg attempts to pass: {avg_attempts:.1f}")

    # Verification stage breakdown for failed tasks
    failed = [v for v in progress.values() if not v.get("passed")]
    if failed:
        print(f"\n  Failed task breakdown (best attempt):")
        syntax_only = 0
        kondo_only = 0
        load_only = 0
        test_fail = 0
        all_fail = 0
        for v in failed:
            best = v.get("best_attempt", {})
            s = best.get("syntax_ok", False)
            k = best.get("kondo_ok", False)
            l = best.get("load_ok", False)
            t = best.get("tests_ok", False)
            if t:
                test_fail += 1  # should not happen
            elif l:
                load_only += 1  # loaded but tests failed
            elif k:
                kondo_only += 1  # kondo ok but load failed
            elif s:
                syntax_only += 1  # syntax ok but kondo failed
            else:
                all_fail += 1  # syntax failed
        print(f"    All fail (syntax):   {all_fail}")
        print(f"    Syntax only:         {syntax_only}")
        print(f"    Syntax + kondo:      {kondo_only}")
        print(f"    Loaded, tests fail:  {load_only}")

    # Comparison with baselines
    print(f"\n  Comparison with baselines (111 held-out):")
    print(f"    Agent pass@1:          {first_pass}/{total} = {first_pass/total*100:.1f}%")
    print(f"    Agent best-of-{args.K}:    {any_pass}/{total} = {any_pass/total*100:.1f}%")
    print(f"    GPT-5.4 pass@1:        71/111 = 64.0%")
    print(f"    GPT-5.4-mini pass@1:   66/111 = 59.5%")
    print(f"    Opus 4.7 pass@1:       50/111 = 45.0%")
    print(f"    30B SFT best-of-16:    93/111 = 83.8% (blind best-of-K ceiling)")
    print(f"    30B SFT pass@1:        58/111 = 52.3%")

    # Tasks gained by agent vs pass@1
    gained = [
        tid for tid, v in progress.items()
        if not v.get("first_attempt_passed") and v.get("passed")
    ]
    if gained:
        print(f"\n  Tasks gained by agent ({len(gained)}):")
        for tid in sorted(gained):
            v = progress[tid]
            k = v["attempts_until_pass"]
            print(f"    {tid} (passed on attempt {k})")

    # Tasks still unsolved
    unsolved = [
        tid for tid, v in progress.items() if not v.get("passed")
    ]
    if unsolved:
        print(f"\n  Still unsolved ({len(unsolved)}):")
        for tid in sorted(unsolved):
            print(f"    {tid}")


def main():
    parser = argparse.ArgumentParser(description="Verifier Agent Loop evaluation")
    parser.add_argument("--K", type=int, default=8, help="Max attempts per task")
    parser.add_argument("--limit", type=int, default=None, help="Limit to N tasks")
    parser.add_argument("--temperature", type=float, default=0.7, help="Base temperature")
    parser.add_argument("--temp-increase", type=float, default=0.05,
                        help="Temperature increase per retry")
    parser.add_argument("--max-temp", type=float, default=1.0, help="Max temperature cap")
    parser.add_argument("--max-tokens", type=int, default=8192, help="Max tokens per generation")
    parser.add_argument("--test-timeout", type=int, default=10, help="Test timeout in seconds")
    parser.add_argument("--blind-attempts", type=int, default=4,
                        help="First N attempts use original prompt (no feedback)")
    parser.add_argument("--checkpoint", default=SFT_8B_CHECKPOINT,
                        help="Tinker checkpoint URL")
    parser.add_argument("--tokenizer", default=None,
                        help="HuggingFace tokenizer name (auto-detected from checkpoint)")
    parser.add_argument("--output", default=None, help="Output JSON path")
    parser.add_argument("--resume", action="store_true", help="Resume from saved progress")
    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    # Auto-detect tokenizer from checkpoint
    if args.tokenizer is None:
        if "30B" in args.checkpoint or "30b" in args.checkpoint:
            tokenizer_name = TOKENIZER_30B
        else:
            tokenizer_name = TOKENIZER_8B
    else:
        tokenizer_name = args.tokenizer

    # Auto-generate output path if not specified
    if args.output is None:
        model_tag = "30b" if "30B" in args.checkpoint or "30b" in args.checkpoint else "8b"
        args.output = f"research/verifier-agent-{model_tag}-K{args.K}.json"

    # Load held-out tasks
    tasks = load_heldout_tasks()
    print(f"Loaded {len(tasks)} held-out tasks")

    if args.limit:
        tasks = tasks[:args.limit]
        print(f"  Limited to {args.limit}")

    # Load previous progress if resuming
    output_path = ROOT / args.output
    progress = {}
    done_tasks = set()
    if args.resume and output_path.exists():
        with open(output_path) as f:
            saved = json.load(f)
        progress = saved.get("per_task", {})
        done_tasks = set(progress.keys())
        tasks = [t for t in tasks if t["task_id"] not in done_tasks]
        print(f"  Resuming: {len(done_tasks)} tasks already done, {len(tasks)} remaining")

    # Connect to Tinker
    from tinker import ServiceClient

    print(f"\nConnecting to Tinker...")
    print(f"  Checkpoint: {args.checkpoint}")
    print(f"  Tokenizer:  {tokenizer_name}")
    client = ServiceClient()
    tc = client.create_training_client_from_state(args.checkpoint)
    save_result = tc.save_weights_for_sampler("verifier-agent").result()
    sc = client.create_sampling_client(model_path=save_result.path)
    print(f"  Sampler ready: {save_result.path}")

    tokenizer = AutoTokenizer.from_pretrained(tokenizer_name, trust_remote_code=True)

    # Run agent loop
    print(f"\nVerifying Agent Loop (K={args.K}, temp={args.temperature})")
    print("=" * 60)

    start = time.time()
    total_tasks = len(tasks) + len(done_tasks)

    for i, task in enumerate(tasks):
        tid = task["task_id"]
        prompt_path = ROOT / task["prompt_path"]
        test_path = str(ROOT / task["tests_path"])
        prompt_text = prompt_path.read_text().strip()

        result = run_agent_loop(sc, tokenizer, prompt_text, test_path, args)
        progress[tid] = result

        # Progress reporting
        elapsed = time.time() - start
        done = len(progress)
        first_pass = result.get("first_attempt_passed", False)
        agent_pass = result.get("passed", False)
        n_attempts = len(result.get("attempts", []))

        status = "PASS" if agent_pass else "FAIL"
        first = "pass" if first_pass else "FAIL"
        print(f"  [{done}/{total_tasks}] {tid}: "
              f"first={first} agent={status} ({n_attempts} attempts"
              + (f", passed on #{result['attempts_until_pass']}" if agent_pass and not first_pass else "")
              + ")")

        # Save progress incrementally
        _save_results(output_path, progress, args)

    # Final report
    _print_report(progress, args)
    _save_results(output_path, progress, args)
    print(f"\nResults saved to {output_path}")

    elapsed = time.time() - start
    print(f"Total time: {elapsed:.0f}s ({elapsed/60:.1f}min)")


if __name__ == "__main__":
    main()
