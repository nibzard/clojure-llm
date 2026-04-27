#!/usr/bin/env python3
"""Evaluate a Tinker checkpoint on held-out tasks with best-of-K."""

import json
import sys
import time
from pathlib import Path

from dotenv import load_dotenv
from transformers import AutoTokenizer

ROOT = Path(__file__).resolve().parent.parent
load_dotenv(ROOT / ".env")

sys.path.insert(0, str(ROOT))
from training.rlvr.evaluate import evaluate_single


def load_heldout_tasks():
    from edn_format import Keyword, loads

    with open(ROOT / "data" / "sft" / "heldout_task_ids.json") as f:
        heldout_ids = set(json.load(f))
    with open(ROOT / "benchmark" / "tasks-v0.edn") as f:
        tasks_edn = loads(f.read())

    tasks = []
    for t in tasks_edn:
        tid = str(t[Keyword("id")])
        if tid in heldout_ids:
            tasks.append({
                "task_id": tid,
                "prompt_path": str(t[Keyword("prompt-ref")][Keyword("path")]),
                "tests_path": str(t[Keyword("tests-ref")][Keyword("path")]),
            })
    return tasks


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
    return combined


def main():
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument("--checkpoint", required=True)
    parser.add_argument("--K", type=int, default=1, help="Samples per task for best-of-K")
    parser.add_argument("--temperature", type=float, default=0.7)
    parser.add_argument("--output", required=True)
    args = parser.parse_args()

    tasks = load_heldout_tasks()
    print(f"Loaded {len(tasks)} held-out tasks")

    from tinker import ModelInput, SamplingParams, ServiceClient

    print(f"Connecting to Tinker...")
    client = ServiceClient()
    print(f"Loading checkpoint: {args.checkpoint}")
    sc = client.create_sampling_client(model_path=args.checkpoint)
    print("Sampling client ready")

    tokenizer = AutoTokenizer.from_pretrained("Qwen/Qwen3-8B-Base", trust_remote_code=True)
    params = SamplingParams(max_tokens=512, temperature=args.temperature, top_p=0.95)

    results = {}
    start = time.time()

    for i, task in enumerate(tasks):
        tid = task["task_id"]
        prompt_path = ROOT / task["prompt_path"]
        test_path = str(ROOT / task["tests_path"])
        prompt_text = prompt_path.read_text().strip()

        tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
        model_input = ModelInput.from_ints(tokens)

        samples = []
        for k in range(args.K):
            try:
                response = sc.sample(model_input, 1, params).result()
                gen_tokens = list(response.sequences[0].tokens)
                gen_text = tokenizer.decode(gen_tokens)
                code = extract_code(prompt_text, gen_text)
                passed, _ = evaluate_single(code, test_path, timeout=10)
            except Exception:
                passed = False
            samples.append(passed)

        first_pass = samples[0] if samples else False
        any_pass = any(samples)
        pass_count_k = sum(samples)

        results[tid] = {
            "first_pass": first_pass,
            "any_pass": any_pass,
            "pass_count": pass_count_k,
            "total_samples": args.K,
            "samples": samples,
        }

        elapsed = time.time() - start
        done = i + 1
        first_pass_total = sum(1 for v in results.values() if v["first_pass"])
        any_pass_total = sum(1 for v in results.values() if v["any_pass"])
        rate = done / elapsed if elapsed > 0 else 0
        eta = (len(tasks) - done) / rate if rate > 0 else 0
        print(f"  [{done}/{len(tasks)}] {tid}: first={'pass' if first_pass else 'FAIL'} "
              f"best_of_{args.K}={'PASS' if any_pass else 'fail'} "
              f"({pass_count_k}/{args.K}) | p1={first_pass_total} boK={any_pass_total} | ETA={eta:.0f}s")

    total = len(tasks)
    first_pass = sum(1 for v in results.values() if v["first_pass"])
    any_pass = sum(1 for v in results.values() if v["any_pass"])

    output_data = {
        "K": args.K,
        "temperature": args.temperature,
        "checkpoint": args.checkpoint,
        "total_tasks": total,
        "pass_at_1": first_pass,
        "pass_at_1_pct": f"{first_pass/total*100:.1f}%",
        "best_of_K": any_pass,
        "best_of_K_pct": f"{any_pass/total*100:.1f}%",
        "per_task": results,
    }
    with open(args.output, "w") as f:
        json.dump(output_data, f, indent=2)

    print(f"\n{'='*60}")
    print(f"BEST-OF-{args.K} RESULTS ({total} held-out tasks)")
    print(f"{'='*60}")
    print(f"  pass@1:         {first_pass}/{total} = {first_pass/total*100:.1f}%")
    print(f"  best-of-{args.K}:     {any_pass}/{total} = {any_pass/total*100:.1f}%")
    print(f"  lift:           +{any_pass - first_pass} tasks")
    print(f"\n  Best-of-K curve:")
    for k in [1, 2, 4, 8, 16]:
        if k > args.K:
            break
        count = sum(1 for v in results.values() if any(s for s in v["samples"][:k]))
        print(f"    best-of-{k:2d}: {count}/{total} = {count/total*100:.1f}%")
    print(f"\nSaved to {args.output}")


if __name__ == "__main__":
    main()
