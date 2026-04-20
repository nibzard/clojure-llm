#!/usr/bin/env python3
"""Tinker Bridge — persistent Python sidecar for Clojure code generation.

Reads JSON commands from stdin (one per line), writes JSON responses to stdout
(one per line). Holds a Tinker SamplingClient as a global after init so there
is no cold start on subsequent generate calls.

Protocol:
    init    → { checkpoint }          → { status: "ready" }
    generate → { prompt, num_samples, temperature, max_tokens }
                                      → { samples: [...], num_generated }
    generate_with_verify → { prompt, num_samples, temperature, max_tokens, test_path }
                                      → { best_sample, best_k, total, verified }
    shutdown → {}                     → { status: "shutdown" }
"""

import json
import sys
import traceback
from pathlib import Path

# Add project root so training.rlvr.evaluate is importable
ROOT = Path(__file__).resolve().parent.parent.parent.parent
sys.path.insert(0, str(ROOT))

# Globals — populated by init()
sc = None
tokenizer = None


def extract_code(prompt_text, generated_text):
    """Extract clean Clojure defn from prompt + generated text.

    Reuses logic from scripts/best_of_k.py lines 55-68.
    """
    combined = prompt_text + generated_text
    last_defn = combined.rfind("(defn")
    if last_defn >= 0:
        candidate = combined[last_defn:]
        depth = 0
        for i in range(len(candidate)):
            if candidate[i] == "(":
                depth += 1
            elif candidate[i] == ")":
                depth -= 1
                if depth == 0:
                    return candidate[: i + 1]
        return candidate
    return combined


def handle_init(params):
    """Initialize Tinker sampling client from checkpoint."""
    global sc, tokenizer

    checkpoint = params["checkpoint"]

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


def handle_generate(params):
    """Generate Clojure code samples."""
    from tinker import ModelInput, SamplingParams

    prompt = params["prompt"]
    num_samples = params.get("num_samples", 1)
    temperature = params.get("temperature", 0.7)
    max_tokens = params.get("max_tokens", 512)

    sampling_params = SamplingParams(max_tokens=max_tokens, temperature=temperature, top_p=0.95)
    tokens = tokenizer.encode(prompt, add_special_tokens=True)
    model_input = ModelInput.from_ints(tokens)

    samples = []
    for _ in range(num_samples):
        response = sc.sample(model_input, 1, sampling_params).result()
        gen_tokens = list(response.sequences[0].tokens)
        gen_text = tokenizer.decode(gen_tokens)
        code = extract_code(prompt, gen_text)
        samples.append(code)

    return {"samples": samples, "num_generated": len(samples)}


def handle_generate_with_verify(params):
    """Generate K samples, verify each against test file, return best."""
    from training.rlvr.evaluate import evaluate_single
    from tinker import ModelInput, SamplingParams

    prompt = params["prompt"]
    num_samples = params.get("num_samples", 4)
    temperature = params.get("temperature", 0.7)
    max_tokens = params.get("max_tokens", 512)
    test_path = params["test_path"]

    sampling_params = SamplingParams(max_tokens=max_tokens, temperature=temperature, top_p=0.95)
    tokens = tokenizer.encode(prompt, add_special_tokens=True)
    model_input = ModelInput.from_ints(tokens)

    best_sample = None
    best_k = -1
    total = 0
    verified = False

    for k in range(num_samples):
        try:
            response = sc.sample(model_input, 1, sampling_params).result()
            gen_tokens = list(response.sequences[0].tokens)
            gen_text = tokenizer.decode(gen_tokens)
            code = extract_code(prompt, gen_text)
            total += 1

            passed, _ = evaluate_single(code, test_path, timeout=10)
            if passed:
                best_sample = code
                best_k = k
                verified = True
                break  # Return first passing sample
        except Exception:
            total += 1

    # If nothing passed, return the first sample anyway
    if best_sample is None and total > 0:
        response = sc.sample(model_input, 1, sampling_params).result()
        gen_tokens = list(response.sequences[0].tokens)
        gen_text = tokenizer.decode(gen_tokens)
        best_sample = extract_code(prompt, gen_text)

    return {
        "best_sample": best_sample,
        "best_k": best_k,
        "total": total,
        "verified": verified,
    }


def handle_shutdown():
    """Graceful shutdown."""
    return {"status": "shutdown"}


HANDLERS = {
    "init": handle_init,
    "generate": handle_generate,
    "generate_with_verify": handle_generate_with_verify,
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
