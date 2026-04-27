#!/usr/bin/env python3
"""Evaluate base models via Tinker sampling API on held-out tasks.

Runs pass@1 evaluation for any Tinker-available base model without training.

Usage:
    python3 scripts/eval_tinker_base.py --model Qwen/Qwen3.5-4B
    python3 scripts/eval_tinker_base.py --model Qwen/Qwen3.6-35B-A3B --limit 20
"""

import argparse
import json
import os
import sys
import time
from pathlib import Path

from dotenv import load_dotenv
from transformers import AutoTokenizer

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT))

from tinker import ModelInput, SamplingParams, ServiceClient
from training.rlvr.evaluate import evaluate_single

DEFAULT_MODELS = [
    "Qwen/Qwen3.5-4B",
    "Qwen/Qwen3.5-27B",
    "Qwen/Qwen3.6-27B",
    "Qwen/Qwen3.6-35B-A3B",
]


def load_heldout_tasks():
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
            })
    return heldout


def extract_code(prompt_text, generated_text):
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
                    return candidate[:i + 1]
        return candidate
    return generated_text


def evaluate_model(model_name, tasks, temperature=0.2, max_tokens=512, resume_path=None):
    print(f"\n{'=' * 60}")
    print(f"Base model: {model_name}")
    print(f"Tasks: {len(tasks)}, temp={temperature}")
    print(f"{'=' * 60}")

    client = ServiceClient()
    sc = client.create_sampling_client(base_model=model_name)

    tokenizer_name = model_name  # e.g. Qwen/Qwen3.5-4B
    tokenizer = AutoTokenizer.from_pretrained(tokenizer_name, trust_remote_code=True)

    params = SamplingParams(max_tokens=max_tokens, temperature=temperature, top_p=0.95)

    # Resume support
    results = {}
    if resume_path and resume_path.exists():
        with open(resume_path) as f:
            saved = json.load(f)
        results = saved.get("per_task", {})
        done_ids = set(results.keys())
        tasks = [t for t in tasks if t["task_id"] not in done_ids]
        print(f"  Resuming: {len(done_ids)} done, {len(tasks)} remaining")

    start = time.time()
    passed = sum(1 for v in results.values() if v.get("passed"))

    for i, task in enumerate(tasks):
        tid = task["task_id"]
        prompt_path = ROOT / task["prompt_path"]
        test_path = str(ROOT / task["tests_path"])
        prompt_text = prompt_path.read_text().strip()

        tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
        model_input = ModelInput.from_ints(tokens)

        try:
            response = sc.sample(model_input, 1, params).result()
            gen_tokens = list(response.sequences[0].tokens)
            gen_text = tokenizer.decode(gen_tokens)
            code = extract_code(prompt_text, gen_text)
            ok, err = evaluate_single(code, test_path, timeout=10)

            if ok:
                passed += 1
                status = "PASS"
            else:
                status = "FAIL"

            results[tid] = {"passed": ok, "error": err[:200] if err else ""}

        except Exception as e:
            status = f"ERROR: {e}"
            results[tid] = {"passed": False, "error": str(e)[:200]}

        total_done = len(results)
        elapsed = time.time() - start
        rate = (total_done - (len(results) - i - 1)) / elapsed if elapsed > 0 else 0
        pct = passed / total_done * 100 if total_done > 0 else 0
        remaining = len(tasks) - i - 1
        eta = remaining / (rate if rate > 0 else 1)

        print(f"  [{total_done}/{total_done + remaining}] {tid}: {status} "
              f"({passed}/{total_done} = {pct:.1f}%) ETA {eta:.0f}s")

        # Save incrementally
        if resume_path:
            _save(resume_path, model_name, results, temperature)

    elapsed = time.time() - start
    total = len(results)
    pct = passed / total * 100 if total > 0 else 0

    print(f"\n  RESULT: {passed}/{total} = {pct:.1f}% ({elapsed:.0f}s)")

    if resume_path:
        _save(resume_path, model_name, results, temperature)

    return passed, total


def _save(path, model_name, results, temperature):
    total = len(results)
    passed = sum(1 for v in results.values() if v.get("passed"))
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w") as f:
        json.dump({
            "model": model_name,
            "type": "base",
            "temperature": temperature,
            "pass_at_1": passed,
            "total": total,
            "per_task": results,
        }, f, indent=2)


def main():
    parser = argparse.ArgumentParser(description="Evaluate Tinker base models on held-out tasks")
    parser.add_argument("--model", default=None, help="Single model to evaluate")
    parser.add_argument("--all", action="store_true", help="Evaluate all default models")
    parser.add_argument("--limit", type=int, default=None, help="Limit tasks")
    parser.add_argument("--temperature", type=float, default=0.2)
    parser.add_argument("--max-tokens", type=int, default=512)
    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    tasks = load_heldout_tasks()
    print(f"Loaded {len(tasks)} held-out tasks")

    if args.limit:
        tasks = tasks[:args.limit]
        print(f"  Limited to {args.limit}")

    if args.model:
        models = [args.model]
    elif args.all:
        models = DEFAULT_MODELS
    else:
        print("Specify --model <name> or --all")
        print(f"Available: {', '.join(DEFAULT_MODELS)}")
        sys.exit(1)

    summary = {}
    for model_name in models:
        slug = model_name.replace("/", "-")
        out_path = ROOT / "research" / f"base-{slug}-results.json"
        p, t = evaluate_model(model_name, tasks, args.temperature, args.max_tokens,
                              resume_path=out_path)
        summary[model_name] = f"{p}/{t} = {p/t*100:.1f}%"

    print(f"\n{'=' * 60}")
    print("SUMMARY")
    print(f"{'=' * 60}")
    for m, r in summary.items():
        print(f"  {m}: {r}")
    # Add comparison baselines
    print(f"\n  Known baselines:")
    print(f"    SFT Qwen3-8B:   42/111 = 37.8%")
    print(f"    SFT Qwen3-30B:  58/111 = 52.3%")
    print(f"    Opus 4.7:       50/111 = 45.0%")
    print(f"    GPT-5.4:        71/111 = 64.0%")


if __name__ == "__main__":
    main()
