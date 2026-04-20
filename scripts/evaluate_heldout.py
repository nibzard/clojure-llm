#!/usr/bin/env python3
"""Evaluate trained model on held-out tasks and compare with baselines.

Generates candidates for 111 held-out tasks using the trained model,
evaluates via the Clojure benchmark pipeline, then compares pass@1
against baseline models on the same held-out tasks.

Supports both SFT and RLVR checkpoints via --checkpoint flag.

Usage:
    python3 scripts/evaluate_heldout.py
    python3 scripts/evaluate_heldout.py --checkpoint checkpoints/rlvr/final
    python3 scripts/evaluate_heldout.py --limit 10  # test
    python3 scripts/evaluate_heldout.py --skip-gen --skip-eval  # just comparison table
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
from transformers import AutoTokenizer

ROOT = Path(__file__).resolve().parent.parent

BASELINE_RUNS = {
    "2026-04-19-2026-04-17-gpt-5-4-direct-v1": "GPT-5.4",
    "2026-04-19-2026-04-17-gpt-5-4-mini-2026-03-17-direct-v1": "GPT-5.4-mini",
    "2026-04-19-2026-04-17-claude-opus-4-7-direct-v1": "Opus-4.7",
}

# Model evaluation run IDs (for comparison table)
MODEL_RUNS = {
    "sft": {
        "run_id": "2026-04-19-2026-04-18-sft-qwen3-8b-heldout-v1",
        "label": "SFT Qwen3-8B",
        "checkpoint": "tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600",
    },
    "rlvr_v1_prev": {
        "run_id": "2026-04-19-2026-04-18-rlvr-qwen3-8b-heldout-v1",
        "label": "RLVR Qwen3-8B (Prev)",
    },
}

# Default checkpoint (SFT)
DEFAULT_CHECKPOINT = "tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600"


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


def create_run_manifest(task_ids, run_id, model_label="sft-qwen3-8b"):
    """Create a benchmark run manifest."""
    manifest = (
        '{:task-ids [' +
        ' '.join(f'"{tid}"' for tid in sorted(task_ids)) + ']\n'
        ' :policy {:kind :direct}\n'
        f' :tasks-file "benchmark/tasks-v0.edn"\n'
        ' :prompting {:template :sft-eval :temperature 0.2 :top-p 0.95 :samples 1}\n'
        ' :executor {:kind :local-process :isolation :task-subprocess :network :none}\n'
        f' :created-at "{time.strftime("%Y-%m-%dT%H:%M:%S.000000Z")}"\n'
        f' :run-id "{run_id}"\n'
        ' :benchmark-version :clj-bench/v1\n'
        f' :model {{:provider :tinker :id "{model_label}"}}}}\n'
    )
    manifest_path = ROOT / "benchmark" / "runs" / f"{run_id}.edn"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(manifest)
    return manifest_path


def extract_code(prompt_text, generated_text):
    """Extract clean Clojure defn from prompt + generated text.

    The model may regenerate the full function or just the body.
    We want a single clean (defn ...) form.
    """
    combined = prompt_text + generated_text

    # Strategy 1: find the LAST (defn form (the model often regenerates the sig)
    last_defn = combined.rfind("(defn")
    if last_defn >= 0:
        candidate = combined[last_defn:]
        # Find matching closing paren
        depth = 0
        for i in range(len(candidate)):
            if candidate[i] == '(':
                depth += 1
            elif candidate[i] == ')':
                depth -= 1
                if depth == 0:
                    return candidate[:i + 1]
        return candidate

    # Strategy 2: if no defn in generated, just use prompt + generated as-is
    # (the generated text should be the body)
    return combined


def generate_candidates(sc, tokenizer, tasks, cand_dir, limit=None):
    """Generate candidates for held-out tasks."""
    from tinker import ModelInput, SamplingParams

    if limit:
        tasks = tasks[:limit]

    params = SamplingParams(max_tokens=512, temperature=0.2, top_p=0.95)

    for i, task in enumerate(tasks, 1):
        prompt_path = ROOT / task["prompt_path"]
        prompt_text = prompt_path.read_text().strip()

        # Tokenize
        tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
        model_input = ModelInput.from_ints(tokens)

        # Generate
        try:
            response = sc.sample(model_input, 1, params).result()
            generated_tokens = response.sequences[0].tokens
            generated_text = tokenizer.decode(generated_tokens)

            # Extract clean function from prompt + generated text
            code = extract_code(prompt_text, generated_text)

        except Exception as e:
            code = f";; Generation failed: {e}"

        # Save candidate (filename = prompt stem)
        stem = Path(task["prompt_path"]).stem
        out_file = cand_dir / f"{stem}.clj"
        out_file.write_text(code)

        if i % 20 == 0 or i == len(tasks):
            print(f"  [{i}/{len(tasks)}] generated")

    return len(tasks)


def run_evaluation(manifest_path):
    """Run Clojure benchmark evaluation."""
    cmd = ["clojure", "-M:bench", "evaluate", str(manifest_path)]
    print(f"\nRunning: {' '.join(cmd)}")
    result = subprocess.run(cmd, capture_output=True, text=True, timeout=600)
    if result.returncode != 0:
        print(f"  Error: {result.stderr[-500:]}")
    return result


def load_eval_results(run_id):
    """Load evaluation results for a run."""
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
        results[task_id] = outcome
    return results


def compare_baselines(heldout_tasks, model_label="SFT Qwen3-8B", model_run_id=None):
    """Compare model with baselines on held-out tasks."""
    heldout_ids = {t["task_id"] for t in heldout_tasks}

    # If a specific run_id is given, use it; otherwise use the default SFT run
    if model_run_id:
        run_id = model_run_id
    else:
        run_id = MODEL_RUNS["sft"]["run_id"]

    model_results = load_eval_results(run_id)
    model_pass = sum(1 for o in model_results.values() if o == ":pass")
    model_total = len(model_results)

    print(f"\n{'=' * 60}")
    print("HELD-OUT EVALUATION RESULTS (111 tasks)")
    print(f"{'=' * 60}")

    # Model results
    if model_total > 0:
        print(f"  {model_label:20s} {model_pass}/{model_total} = {model_pass/model_total*100:.1f}%")
    else:
        print(f"  {model_label:20s} (not yet evaluated)")

    # Also show SFT baseline if we're evaluating RLVR
    if model_run_id and model_run_id != MODEL_RUNS["sft"]["run_id"]:
        sft_results = load_eval_results(MODEL_RUNS["sft"]["run_id"])
        sft_pass = sum(1 for o in sft_results.values() if o == ":pass")
        sft_total = len(sft_results)
        if sft_total > 0:
            print(f"  {'SFT Qwen3-8B':20s} {sft_pass}/{sft_total} = {sft_pass/sft_total*100:.1f}%")

    # Baselines (sorted by pass rate, descending)
    baseline_rows = []
    for run_id, label in BASELINE_RUNS.items():
        all_results = load_eval_results(run_id)
        heldout_results = {tid: o for tid, o in all_results.items() if tid in heldout_ids}
        passed = sum(1 for o in heldout_results.values() if o == ":pass")
        total = len(heldout_results)
        if total > 0:
            baseline_rows.append((label, passed, total))

    for label, passed, total in sorted(baseline_rows, key=lambda x: x[1]/x[2], reverse=True):
        print(f"  {label:20s} {passed}/{total} = {passed/total*100:.1f}%")

    # Per-task comparison (model vs baselines)
    if model_total > 0:
        print(f"\n  --- Per-task analysis ---")
        only_model = 0
        only_baseline = 0
        both = 0
        neither = 0

        for task in heldout_tasks:
            tid = task["task_id"]
            model_passed = model_results.get(tid) == ":pass"

            any_baseline = False
            for run_id in BASELINE_RUNS:
                br = load_eval_results(run_id)
                if br.get(tid) == ":pass":
                    any_baseline = True
                    break

            if model_passed and any_baseline:
                both += 1
            elif model_passed:
                only_model += 1
            elif any_baseline:
                only_baseline += 1
            else:
                neither += 1

        print(f"  Both pass:           {both}")
        print(f"  Only {model_label}:  {only_model}")
        print(f"  Only baseline:       {only_baseline}")
        print(f"  Neither:             {neither}")

        # Success check
        if model_total > 0:
            model_pct = model_pass / model_total * 100
            opus_results = load_eval_results("2026-04-17-claude-opus-4-7-direct")
            opus_heldout = {tid: o for tid, o in opus_results.items() if tid in heldout_ids}
            opus_pass = sum(1 for o in opus_heldout.values() if o == ":pass")
            opus_total = len(opus_heldout)
            if opus_total > 0:
                opus_pct = opus_pass / opus_total * 100
                if model_pct > opus_pct:
                    print(f"\n  ** {model_label} ({model_pct:.1f}%) BEATS Opus 4.7 ({opus_pct:.1f}%) **")
                else:
                    gap = opus_pct - model_pct
                    print(f"\n  {model_label} ({model_pct:.1f}%) is {gap:.1f}% behind Opus 4.7 ({opus_pct:.1f}%)")


def main():
    parser = argparse.ArgumentParser(description="Evaluate model on held-out tasks")
    parser.add_argument("--limit", type=int, default=None)
    parser.add_argument("--skip-gen", action="store_true")
    parser.add_argument("--skip-eval", action="store_true")
    parser.add_argument(
        "--checkpoint",
        default=None,
        help="Tinker checkpoint path (e.g., tinker://... or checkpoints/rlvr/final)",
    )
    parser.add_argument(
        "--label",
        default=None,
        help="Model label for results table (e.g., 'RLVR Qwen3-8B')",
    )
    parser.add_argument(
        "--run-id",
        default=None,
        help="Override run ID for candidates/results",
    )
    args = parser.parse_args()

    load_dotenv(ROOT / ".env")

    # Determine checkpoint and run configuration
    checkpoint = args.checkpoint or DEFAULT_CHECKPOINT
    is_rlvr = "rlvr" in checkpoint.lower() if args.checkpoint else False
    model_label = args.label or ("RLVR Qwen3-8B" if is_rlvr else "SFT Qwen3-8B")

    if args.run_id:
        run_id = args.run_id
    elif is_rlvr:
        run_id = f"2026-04-18-rlvr-qwen3-8b-heldout"
    else:
        run_id = MODEL_RUNS["sft"]["run_id"]

    from tinker import ServiceClient

    print("=" * 60)
    print(f"{model_label} Evaluation on Held-Out Tasks")
    print("=" * 60)
    print(f"  Checkpoint: {checkpoint}")
    print(f"  Run ID:     {run_id}")

    # Load held-out tasks
    heldout_tasks = load_heldout_tasks()
    print(f"\nHeld-out tasks: {len(heldout_tasks)}")

    if args.limit:
        heldout_tasks = heldout_tasks[:args.limit]
        print(f"  Limited to: {args.limit}")

    cand_dir = ROOT / "benchmark" / "candidates" / run_id
    cand_dir.mkdir(parents=True, exist_ok=True)

    # -- Phase 1: Generate candidates ------------------------------------------

    if not args.skip_gen:
        print(f"\n{'─' * 40}")
        print(f"Generating candidates with {model_label}")
        print(f"{'─' * 40}")

        print("\nConnecting to Tinker...")
        client = ServiceClient()

        print(f"Loading checkpoint: {checkpoint}")
        tc = client.create_training_client_from_state(checkpoint)
        save_result = tc.save_weights_for_sampler(f"eval-heldout-{run_id}").result()
        print(f"  Sampler path: {save_result.path}")

        sc = client.create_sampling_client(model_path=save_result.path)
        print("  Sampling client ready")

        print("\nLoading tokenizer...")
        tokenizer = AutoTokenizer.from_pretrained(
            "Qwen/Qwen3-8B-Base", trust_remote_code=True
        )

        generate_candidates(sc, tokenizer, heldout_tasks, cand_dir, args.limit)

    # -- Phase 2: Evaluate -----------------------------------------------------

    if not args.skip_eval:
        print(f"\n{'─' * 40}")
        print("Running Clojure evaluation")
        print(f"{'─' * 40}")

        task_ids = [t["task_id"] for t in heldout_tasks]
        model_id_tag = "rlvr-qwen3-8b" if is_rlvr else "sft-qwen3-8b"
        manifest_path = create_run_manifest(task_ids, run_id, model_label=model_id_tag)
        run_evaluation(manifest_path)

    # -- Phase 3: Compare ------------------------------------------------------

    compare_baselines(heldout_tasks, model_label=model_label, model_run_id=run_id)


if __name__ == "__main__":
    main()
