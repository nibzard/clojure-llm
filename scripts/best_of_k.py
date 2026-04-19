#!/usr/bin/env python3
"""Best-of-K evaluation on held-out tasks.

For each task, generate K candidates at temperature 0.7 and evaluate each.
Report pass@1 and best-of-K (any candidate passes) to estimate the model's
ceiling with a perfect verifier.

Usage:
    python3 scripts/best_of_k.py --K 16
    python3 scripts/best_of_k.py --K 8 --limit 10   # quick test
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

from training.rlvr.evaluate import evaluate_single, load_training_tasks

RLVR_CHECKPOINT = "tinker://cf3778fc-5553-5e8d-be25-84859b2de080:train:0/weights/checkpoint-iter-10"
SFT_CHECKPOINT = "tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600"


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
                "source": str(t[Keyword("source")]),
            })
    return heldout


def extract_code(prompt_text, generated_text):
    combined = prompt_text + generated_text
    last_defn = combined.rfind("(defn")
    if last_defn >= 0:
        candidate = combined[last_defn:]
        depth = 0
        for i in range(len(candidate)):
            if candidate[i] == '(':
                depth += 1
            elif candidate[i] == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1]
        return candidate
    return combined


def main():
    parser = argparse.ArgumentParser(description="Best-of-K evaluation")
    parser.add_argument("--K", type=int, default=16, help="Number of samples per task")
    parser.add_argument("--limit", type=int, default=None, help="Limit to N tasks")
    parser.add_argument("--temperature", type=float, default=0.7)
    parser.add_argument("--max-tokens", type=int, default=512)
    parser.add_argument("--checkpoint", default=RLVR_CHECKPOINT)
    parser.add_argument("--output", default="research/best-of-k-results.json")
    parser.add_argument("--resume", action="store_true", help="Resume from saved progress")
    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    # Load held-out tasks
    tasks = load_heldout_tasks()
    print(f"Loaded {len(tasks)} held-out tasks")

    if args.limit:
        tasks = tasks[:args.limit]
        print(f"  Limited to {args.limit}")

    # Load previous progress if resuming
    output_path = ROOT / args.output
    progress = {}
    if args.resume and output_path.exists():
        with open(output_path) as f:
            saved = json.load(f)
        progress = saved.get("per_task", {})
        done_tasks = set(progress.keys())
        tasks = [t for t in tasks if t["task_id"] not in done_tasks]
        print(f"  Resuming: {len(done_tasks)} tasks already done, {len(tasks)} remaining")

    # Connect to Tinker
    from tinker import ModelInput, SamplingParams, ServiceClient

    print(f"\nConnecting to Tinker...")
    client = ServiceClient()
    print(f"Loading checkpoint: {args.checkpoint}")
    tc = client.create_training_client_from_state(args.checkpoint)
    save_result = tc.save_weights_for_sampler("best-of-k-eval").result()
    sc = client.create_sampling_client(model_path=save_result.path)
    print("  Sampling client ready")

    tokenizer = AutoTokenizer.from_pretrained("Qwen/Qwen3-8B-Base", trust_remote_code=True)

    params = SamplingParams(
        max_tokens=args.max_tokens,
        temperature=args.temperature,
        top_p=0.95,
    )

    # Run best-of-K
    print(f"\nBest-of-{args.K} evaluation (temperature={args.temperature})")
    print("=" * 60)

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
                samples.append({"k": k, "passed": passed})
            except Exception as e:
                samples.append({"k": k, "passed": False, "error": str(e)})

        any_pass = any(s["passed"] for s in samples)
        first_pass = samples[0]["passed"] if samples else False
        pass_count = sum(1 for s in samples if s["passed"])

        progress[tid] = {
            "source": task.get("source", ""),
            "first_pass": first_pass,
            "any_pass": any_pass,
            "pass_count": pass_count,
            "total_samples": args.K,
            "samples": samples,
        }

        elapsed = time.time() - start
        done = len(progress)
        rate = done / elapsed if elapsed > 0 else 0
        eta = (len(tasks) + done_tasks_count_if_resume(args, progress)) / rate - elapsed if rate > 0 else 0

        print(f"  [{done}/{len(tasks) + len(progress) - len(tasks)}] {tid}: "
              f"first={'pass' if first_pass else 'FAIL'} "
              f"best_of_{args.K}={'PASS' if any_pass else 'fail'} "
              f"({pass_count}/{args.K})")

        # Save progress incrementally
        _save_results(output_path, progress, args)

    # Final report
    _print_report(progress, args)

    _save_results(output_path, progress, args)
    print(f"\nResults saved to {output_path}")


def done_tasks_count_if_resume(args, progress):
    if args.resume:
        return 0
    return 0


def _save_results(output_path, progress, args):
    total = len(progress)
    first_pass = sum(1 for v in progress.values() if v["first_pass"])
    any_pass = sum(1 for v in progress.values() if v["any_pass"])

    output_path.parent.mkdir(parents=True, exist_ok=True)
    with open(output_path, "w") as f:
        json.dump({
            "K": args.K,
            "temperature": args.temperature,
            "checkpoint": args.checkpoint,
            "total_tasks": total,
            "pass_at_1": first_pass,
            "best_of_K": any_pass,
            "per_task": progress,
        }, f, indent=2)


def _print_report(progress, args):
    total = len(progress)
    if total == 0:
        return

    first_pass = sum(1 for v in progress.values() if v["first_pass"])
    any_pass = sum(1 for v in progress.values() if v["any_pass"])

    print(f"\n{'=' * 60}")
    print(f"BEST-OF-{args.K} RESULTS ({total} held-out tasks)")
    print(f"{'=' * 60}")
    print(f"  pass@1:      {first_pass}/{total} = {first_pass/total*100:.1f}%")
    print(f"  best-of-{args.K}:  {any_pass}/{total} = {any_pass/total*100:.1f}%")
    print(f"  lift:        +{any_pass - first_pass} tasks ({(any_pass - first_pass)/total*100:+.1f}pp)")

    # Compute best-of-k curve
    print(f"\n  Best-of-K curve:")
    for k in [1, 2, 4, 8, 16]:
        if k > args.K:
            break
        count = sum(
            1 for v in progress.values()
            if any(s["passed"] for s in v["samples"][:k])
        )
        print(f"    best-of-{k:2d}: {count}/{total} = {count/total*100:.1f}%")

    # Tasks gained by best-of-K (failed pass@1, passed best-of-K)
    gained = [tid for tid, v in progress.items() if not v["first_pass"] and v["any_pass"]]
    if gained:
        print(f"\n  Tasks gained by best-of-{args.K} ({len(gained)}):")
        for tid in sorted(gained):
            v = progress[tid]
            print(f"    {tid} ({v['pass_count']}/{args.K} pass)")

    # Comparison with baselines
    print(f"\n  Comparison:")
    print(f"    RLVR pass@1:      {first_pass}/{total} = {first_pass/total*100:.1f}%")
    print(f"    RLVR best-of-{args.K}:  {any_pass}/{total} = {any_pass/total*100:.1f}%")
    print(f"    Opus 4.7 pass@1:  50/111 = 45.0%")
    print(f"    GPT-5.4 pass@1:   71/111 = 64.0%")


if __name__ == "__main__":
    main()
