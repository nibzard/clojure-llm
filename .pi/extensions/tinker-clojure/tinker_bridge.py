#!/usr/bin/env python3
"""Tinker Bridge — persistent Python sidecar for verified Clojure code generation.

Reads JSON commands from stdin (one per line), writes JSON responses to stdout
(one per line). Holds a Tinker SamplingClient as a global after init so there
is no cold start on subsequent generate calls.

Protocol:
    init              → { checkpoint, base_tokenizer, use_chat_template? }
                      → { status, sampler_path }
    generate_clojure  → { prompt, num_samples, temperature, max_tokens, test_path? }
                      → { code, verified, attempts, max_attempts, checks, kondo_findings,
                          raw_length, extracted_length, was_truncated }
    shutdown          → {}
                      → { status }

The generate_clojure method runs an internal verification loop:
  for each sample: generate → syntax check → kondo lint → (optional) tests
  Returns the first fully-verified sample, or the best partial candidate.
"""

import json
import os
import re
import subprocess
import sys
import tempfile
import traceback
from pathlib import Path

# Add project root so training.rlvr.evaluate is importable
ROOT = Path(__file__).resolve().parent.parent.parent.parent
sys.path.insert(0, str(ROOT))

# Globals — populated by init()
sc = None
tokenizer = None
_use_chat_template = False


def _find_balanced_defn(text, start_pos=0):
    """Find balanced defn form starting from start_pos in text.

    Returns the extracted code string, or None if no defn found.
    Skips parens inside string literals and line comments.
    """
    defn_pos = text.find("(defn", start_pos)
    if defn_pos < 0:
        return None

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
                # Skip to end of line comment
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
                    return candidate[:i + 1]
        i += 1

    # Unbalanced — return everything from defn_pos
    return candidate


def extract_code(prompt_text, generated_text):
    """Extract clean Clojure defn from prompt + generated text.

    Strategy 1: Search generated text only for (defn — avoids picking up
                the prompt's defn via rfind on combined text.
    Strategy 2: If not found in generated text, find the defn start position
                in the prompt and extract from combined text starting there.

    Returns (code, raw_len, extracted_len, was_truncated).
    """
    raw_len = len(generated_text)

    # Strategy 1: Search generated text only
    code = _find_balanced_defn(generated_text)

    if code is None:
        # Strategy 2: Find defn start in prompt, then extract from combined
        prompt_defn_pos = prompt_text.find("(defn")
        if prompt_defn_pos >= 0:
            combined = prompt_text + generated_text
            code = _find_balanced_defn(combined, prompt_defn_pos)
        else:
            # No defn anywhere — return raw generated text
            code = generated_text

    extracted_len = len(code)
    was_truncated = extracted_len < raw_len

    return code, raw_len, extracted_len, was_truncated


# ---------------------------------------------------------------------------
# Internal verifier helpers (reuse well-tested logic from training.rlvr.evaluate)
# ---------------------------------------------------------------------------

def _check_syntax(code_path):
    """Clojure reader syntax check via subprocess."""
    expr = (
        f'(try (with-open [r (java.io.PushbackReader. (clojure.java.io/reader "{code_path}"))] '
        f'(loop [] (when-not (= ::eof (read r false ::eof)) (recur)))) '
        f'(catch Throwable _ (System/exit 1)))'
    )
    result = subprocess.run(
        ["clojure", "-M", "-e", expr],
        capture_output=True,
        text=True,
        cwd=str(ROOT),
    )
    return result.returncode == 0


def _run_kondo(code_path):
    """clj-kondo --no-summary lint, return (ok, output)."""
    try:
        result = subprocess.run(
            ["clj-kondo", "--lint", code_path, "--no-summary"],
            capture_output=True,
            text=True,
            cwd=str(ROOT),
        )
    except Exception as e:
        return False, str(e)
    output = result.stdout + result.stderr
    return result.returncode == 0, output


def _run_load_and_tests(code_path, test_path, timeout):
    """load-file + test via subprocess with timeout. Returns (load_ok, tests_ok, timed_out, output)."""
    expr = (
        f'(try (load-file "{code_path}") '
        f'(println "__LOAD_OK__") '
        f'(load-file "{test_path}") '
        f'(catch Throwable e '
        f'(binding [*out* *err*] '
        f'(println "ERROR:" (.getMessage e))) '
        f'(System/exit 1)))'
    )
    try:
        result = subprocess.run(
            ["clojure", "-M", "-e", expr],
            capture_output=True,
            text=True,
            timeout=timeout,
            cwd=str(ROOT),
        )
    except subprocess.TimeoutExpired:
        return False, False, True, f"Timeout after {timeout}s"
    output = result.stdout + result.stderr
    load_ok = "__LOAD_OK__" in output
    zero_failures = bool(re.search(r"0 failures,\s+0 errors\.?", output))
    tests_ok = (
        zero_failures
        and "FAIL in" not in output
        and "ERROR in" not in output
        and "ERROR:" not in output
    )
    return load_ok, tests_ok, False, output


def _extract_kondo_errors(kondo_output, max_chars=200):
    """Parse kondo text output, truncate to max_chars for feedback."""
    if not kondo_output:
        return ""
    # Take only error/finding lines (skip summary/stats)
    lines = []
    for line in kondo_output.strip().splitlines():
        # clj-kondo output format: <file>:<line>:<col>: <level>: <message>
        if ":" in line and any(
            lvl in line for lvl in ["error", "warning", "exception"]
        ):
            lines.append(line.strip())
    text = "; ".join(lines) if lines else kondo_output.strip()
    if len(text) > max_chars:
        text = text[:max_chars] + "..."
    return text


# ---------------------------------------------------------------------------
# Generation helper
# ---------------------------------------------------------------------------

def _generate_one_sample(prompt, temperature, max_tokens):
    """Generate a single sample from the Tinker model."""
    from tinker import ModelInput, SamplingParams

    sampling_params = SamplingParams(
        max_tokens=max_tokens, temperature=temperature, top_p=0.95,
    )

    # Use raw tokenization by default — matches SFT/RLVR training.
    # See training/sft/train.py:93-94 and training/rlvr/train.py:188.
    if _use_chat_template and hasattr(tokenizer, 'apply_chat_template'):
        messages = [{"role": "user", "content": prompt}]
        tokens = tokenizer.apply_chat_template(messages, add_generation_prompt=True)
    else:
        tokens = tokenizer.encode(prompt, add_special_tokens=True)

    model_input = ModelInput.from_ints(tokens)

    response = sc.sample(model_input, 1, sampling_params).result()
    gen_tokens = list(response.sequences[0].tokens)
    gen_text = tokenizer.decode(gen_tokens)
    return extract_code(prompt, gen_text)


# ---------------------------------------------------------------------------
# Response formatting helpers
# ---------------------------------------------------------------------------

def _format_success(code, attempt, max_attempts, checks, kondo_findings,
                    raw_length=None, extracted_length=None, was_truncated=False):
    """Build response dict for a fully-verified sample."""
    return {
        "code": code,
        "verified": True,
        "attempts": attempt + 1,
        "max_attempts": max_attempts,
        "checks": checks,
        "kondo_findings": kondo_findings,
        "raw_length": raw_length,
        "extracted_length": extracted_length,
        "was_truncated": was_truncated,
    }


def _format_best_effort(code, attempts, max_attempts, best_score, checks, kondo_findings,
                        raw_length=None, extracted_length=None, was_truncated=False):
    """Build response dict for the best partial candidate after all attempts."""
    return {
        "code": code,
        "verified": False,
        "attempts": attempts,
        "max_attempts": max_attempts,
        "checks": checks,
        "kondo_findings": kondo_findings,
        "best_score": best_score,
        "raw_length": raw_length,
        "extracted_length": extracted_length,
        "was_truncated": was_truncated,
    }


# ---------------------------------------------------------------------------
# Handlers
# ---------------------------------------------------------------------------

def handle_init(params):
    """Initialize Tinker sampling client from checkpoint."""
    global sc, tokenizer, _use_chat_template

    checkpoint = params["checkpoint"]
    _use_chat_template = params.get("use_chat_template", False)

    from dotenv import load_dotenv
    load_dotenv(ROOT / ".env")

    from tinker import ServiceClient
    from transformers import AutoTokenizer

    client = ServiceClient()
    tc = client.create_training_client_from_state(checkpoint)
    save_result = tc.save_weights_for_sampler("pi-clojure-ext").result()
    sc = client.create_sampling_client(model_path=save_result.path)

    base_tokenizer = params.get("base_tokenizer", "Qwen/Qwen3-8B-Base")
    tokenizer = AutoTokenizer.from_pretrained(base_tokenizer, trust_remote_code=True)

    return {"status": "ready", "sampler_path": save_result.path}


def handle_generate_clojure(params):
    """Generate Clojure code with internal verification loop.

    For each sample:
      1. Generate code
      2. Check syntax (score 0 if fails)
      3. Run kondo lint (score 1 if fails)
      4. If test_path provided: run tests (return immediately on pass)
         Else: return immediately (kondo-verified)
    After K attempts, return the best partial candidate.
    """
    prompt = params["prompt"]
    num_samples = params.get("num_samples", 4)
    base_temperature = params.get("temperature", 0.7)
    max_tokens = params.get("max_tokens", 512)
    test_path = params.get("test_path")
    temp_increase = params.get("temp_increase_per_retry", 0.1)
    max_temp = params.get("max_temp", 1.0)
    test_timeout = params.get("test_timeout_sec", 10)
    kondo_feedback_chars = params.get("kondo_feedback_max_chars", 200)

    best_code = None
    best_score = -1
    best_checks = {}
    best_kondo_findings = ""
    best_raw_length = None
    best_extracted_length = None
    best_was_truncated = False
    kondo_feedback = ""
    last_failed_code = ""

    for k in range(num_samples):
        # Increase temperature slightly on retries, capped at max_temp
        temp = min(base_temperature + k * temp_increase, max_temp)

        # Append kondo error feedback + failed code snippet on retries
        current_prompt = prompt
        if k > 0 and kondo_feedback:
            parts = [prompt, "\n\n;; Previous attempt had kondo errors: ", kondo_feedback]
            if last_failed_code:
                snippet = last_failed_code[:150]
                if len(last_failed_code) > 150:
                    snippet += "..."
                parts.append(f"\n;; Previous code (first 150 chars): {snippet}")
            parts.append("\n;; Please fix and try again.\n")
            current_prompt = "".join(parts)

        code, raw_len, extracted_len, was_truncated = _generate_one_sample(
            current_prompt, temp, max_tokens,
        )

        # Write to temp file for verification
        with tempfile.NamedTemporaryFile(
            mode="w", suffix=".clj", delete=False, dir="/tmp",
        ) as f:
            f.write(code)
            code_path = f.name

        try:
            # Step 1: Syntax check
            syntax_ok = _check_syntax(code_path)
            if not syntax_ok:
                if 0 > best_score:
                    best_code = code
                    best_score = 0
                    best_checks = {"syntax": False, "kondo": False, "tests": False}
                    best_kondo_findings = ""
                    best_raw_length = raw_len
                    best_extracted_length = extracted_len
                    best_was_truncated = was_truncated
                continue

            # Step 2: Kondo lint
            kondo_ok, kondo_output = _run_kondo(code_path)
            kondo_findings = _extract_kondo_errors(kondo_output, kondo_feedback_chars)

            if not kondo_ok:
                # Store kondo errors and failed code for feedback on next attempt
                kondo_feedback = kondo_findings
                last_failed_code = code
                if 1 > best_score:
                    best_code = code
                    best_score = 1
                    best_checks = {"syntax": True, "kondo": False, "tests": False}
                    best_kondo_findings = kondo_findings
                    best_raw_length = raw_len
                    best_extracted_length = extracted_len
                    best_was_truncated = was_truncated
                continue

            # Reset kondo feedback since this sample passed lint
            kondo_feedback = ""
            last_failed_code = ""

            # Step 3: Test verification (if test_path provided)
            if test_path:
                load_ok, tests_ok, timed_out, test_output = _run_load_and_tests(
                    code_path, test_path, test_timeout,
                )
                if load_ok and tests_ok and not timed_out:
                    return _format_success(
                        code, k, num_samples,
                        {"syntax": True, "kondo": True, "tests": True},
                        kondo_findings,
                        raw_len, extracted_len, was_truncated,
                    )
                # Track as best if score improves
                score = 2  # passed syntax + kondo
                if score > best_score:
                    best_code = code
                    best_score = score
                    best_checks = {"syntax": True, "kondo": True, "tests": False}
                    best_kondo_findings = kondo_findings
                    best_raw_length = raw_len
                    best_extracted_length = extracted_len
                    best_was_truncated = was_truncated
                continue
            else:
                # No test_path — kondo-verified is sufficient
                return _format_success(
                    code, k, num_samples,
                    {"syntax": True, "kondo": True, "tests": None},
                    kondo_findings,
                    raw_len, extracted_len, was_truncated,
                )
        finally:
            os.unlink(code_path)

    # All attempts exhausted — return best partial candidate
    if best_code is not None:
        return _format_best_effort(
            best_code, num_samples, num_samples,
            best_score, best_checks, best_kondo_findings,
            best_raw_length, best_extracted_length, best_was_truncated,
        )

    # Should not happen (at least one sample always generated), but handle gracefully
    return _format_best_effort(
        ";; Generation failed: no samples produced",
        num_samples, num_samples, -1,
        {"syntax": False, "kondo": False, "tests": False},
        "",
    )


def handle_shutdown():
    """Graceful shutdown."""
    return {"status": "shutdown"}


HANDLERS = {
    "init": handle_init,
    "generate_clojure": handle_generate_clojure,
    "shutdown": handle_shutdown,
}


def main():
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue

        try:
            cmd = json.loads(line)
        except json.JSONDecodeError as e:
            resp = {"error": f"Invalid JSON: {e}"}
            print(json.dumps(resp), flush=True)
            continue

        cmd_id = cmd.get("id", "?")
        method = cmd.get("method", "")
        params = cmd.get("params", {})

        handler = HANDLERS.get(method)
        if handler is None:
            resp = {"id": cmd_id, "error": f"Unknown method: {method}"}
            print(json.dumps(resp), flush=True)
            continue

        try:
            if method == "shutdown":
                result = handle_shutdown()
            else:
                result = handler(params)
            resp = {"id": cmd_id, "result": result}
        except Exception as e:
            resp = {"id": cmd_id, "error": str(e), "traceback": traceback.format_exc()}

        print(json.dumps(resp), flush=True)

        if method == "shutdown":
            sys.exit(0)


if __name__ == "__main__":
    main()
