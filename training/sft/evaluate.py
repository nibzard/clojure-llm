#!/usr/bin/env python3
"""Evaluate a base or fine-tuned model against the benchmark pipeline."""

import argparse
import json
import os
import time
import uuid
from pathlib import Path
from typing import Any, Dict, List

from tinker import ServiceClient
from transformers import AutoTokenizer
import yaml


ROOT = Path(__file__).resolve().parents[2]
DEFAULT_TOKENIZER = "Qwen/Qwen3-8B-Base"


def load_benchmark_tasks(tasks_path: str) -> List[Dict[str, Any]]:
    """Load benchmark tasks from JSON or EDN."""
    if tasks_path.endswith(".json"):
        with open(tasks_path) as f:
            return json.load(f)

    if tasks_path.endswith(".edn"):
        from edn_format import Keyword, loads

        with open(tasks_path) as f:
            raw_tasks = loads(f.read())

        tasks = []
        for task in raw_tasks:
            tasks.append(
                {
                    "id": str(task[Keyword("id")]),
                    "source": str(task[Keyword("source")]),
                    "prompt_path": str(task[Keyword("prompt-ref")][Keyword("path")]),
                    "tests_path": str(task[Keyword("tests-ref")][Keyword("path")]),
                    "entrypoint": str(task[Keyword("entrypoint")]),
                }
            )
        return tasks

    raise ValueError(f"Unsupported task file format: {tasks_path}")


def load_config(config_path: str) -> Dict:
    """Load configuration from YAML."""
    with open(config_path) as f:
        return yaml.safe_load(f)


def _make_run_id(label: str) -> str:
    slug = "".join(ch if ch.isalnum() or ch in "-_" else "-" for ch in label.lower()).strip("-")
    return f"{time.strftime('%Y-%m-%d')}-sft-eval-{slug}-{uuid.uuid4().hex[:8]}"


def _load_sampler(checkpoint_or_model: str):
    client = ServiceClient()
    if checkpoint_or_model.startswith("tinker://"):
        tc = client.create_training_client_from_state(checkpoint_or_model)
        save_result = tc.save_weights_for_sampler(f"sft-eval-{uuid.uuid4().hex[:8]}").result()
        sampler_path = save_result.path
    else:
        sampler_path = checkpoint_or_model
    sc = client.create_sampling_client(model_path=sampler_path)
    return client, sc


def _extract_code(prompt_text: str, generated_text: str) -> str:
    combined = prompt_text + generated_text
    last_defn = combined.rfind("(defn")
    if last_defn >= 0:
        candidate = combined[last_defn:]
        depth = 0
        for i, ch in enumerate(candidate):
            if ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
                if depth == 0:
                    return candidate[: i + 1]
        return candidate
    return combined


def generate_solution(sc, tokenizer, task: Dict[str, Any], max_tokens: int = 512) -> str:
    """Generate a solution for one benchmark task via Tinker sampling."""
    from tinker import ModelInput, SamplingParams

    prompt_path = ROOT / task["prompt_path"]
    prompt_text = prompt_path.read_text().strip()
    params = SamplingParams(max_tokens=max_tokens, temperature=0.2, top_p=0.95)

    tokens = tokenizer.encode(prompt_text, add_special_tokens=True)
    model_input = ModelInput.from_ints(tokens)
    response = sc.sample(model_input, 1, params).result()
    generated_tokens = response.sequences[0].tokens
    generated_text = tokenizer.decode(generated_tokens)
    return _extract_code(prompt_text, generated_text)


def _create_run_manifest(task_ids: List[str], run_id: str, model_label: str) -> Path:
    manifest = (
        "{:task-ids ["
        + " ".join(f'"{task_id}"' for task_id in task_ids)
        + "]\n"
        + ' :policy {:kind :direct}\n'
        + ' :tasks-file "benchmark/tasks-v0.edn"\n'
        + ' :prompting {:template :sft-eval :temperature 0.2 :top-p 0.95 :samples 1}\n'
        + ' :executor {:kind :local-process :isolation :task-subprocess :network :none}\n'
        + f' :created-at "{time.strftime("%Y-%m-%dT%H:%M:%S.000000Z")}"\n'
        + f' :run-id "{run_id}"\n'
        + ' :benchmark-version :clj-bench/v1\n'
        + f' :model {{:provider :tinker :id "{model_label}"}}}}\n'
    )
    manifest_path = ROOT / "benchmark" / "runs" / f"{run_id}.edn"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(manifest)
    return manifest_path


def _run_benchmark_eval(manifest_path: Path) -> None:
    import subprocess

    result = subprocess.run(
        ["clojure", "-M:bench", "evaluate", str(manifest_path)],
        cwd=ROOT,
        capture_output=True,
        text=True,
        timeout=600,
    )
    if result.returncode != 0:
        raise RuntimeError(result.stderr[-500:] or result.stdout[-500:] or "benchmark evaluation failed")


def _load_eval_results(run_id: str) -> Dict[str, Dict[str, Any]]:
    from edn_format import Keyword, loads

    results_dir = ROOT / "benchmark" / "results" / run_id
    results: Dict[str, Dict[str, Any]] = {}
    for edn_file in sorted(results_dir.glob("*.edn")):
        with open(edn_file) as f:
            parsed = loads(f.read())
        task_id = str(parsed[Keyword("task-id")])
        results[task_id] = {
            "outcome": str(parsed[Keyword("outcome")]),
            "syntax_ok": bool(parsed[Keyword("syntax-ok")]),
            "kondo_ok": bool(parsed[Keyword("kondo-ok")]),
            "tests_ok": bool(parsed[Keyword("tests-ok")]),
            "wall_ms": int(parsed[Keyword("wall-ms")]),
        }
    return results


def evaluate_on_benchmark(
    checkpoint_path: str,
    tasks_path: str,
    output_path: str,
    num_samples: int = None,
) -> Dict[str, Any]:
    """
    Evaluate a model checkpoint on benchmark tasks.

    Args:
        checkpoint_path: Path to model checkpoint
        tasks_path: Path to benchmark tasks file
        output_path: Path to save results JSON
        num_samples: Optional limit on number of tasks

    Returns:
        Evaluation results dict
    """
    print(f"Loading model/checkpoint from: {checkpoint_path}")
    tasks = load_benchmark_tasks(tasks_path)
    if num_samples:
        tasks = tasks[:num_samples]

    print(f"Evaluating on {len(tasks)} tasks...")

    _, sc = _load_sampler(checkpoint_path)
    tokenizer = AutoTokenizer.from_pretrained(DEFAULT_TOKENIZER, trust_remote_code=True)
    run_id = _make_run_id(Path(checkpoint_path).name or "model")
    cand_dir = ROOT / "benchmark" / "candidates" / run_id
    cand_dir.mkdir(parents=True, exist_ok=True)

    results = []
    pass_count = 0

    for i, task in enumerate(tasks):
        task_id = task.get("id", f"task-{i}")
        print(f"[{i+1}/{len(tasks)}] Generating solution for {task_id}...")

        try:
            solution = generate_solution(sc, tokenizer, task)
            stem = Path(task["prompt_path"]).stem
            (cand_dir / f"{stem}.clj").write_text(solution)
        except Exception as e:
            print(f"  Error: {e}")
            stem = Path(task["prompt_path"]).stem
            (cand_dir / f"{stem}.clj").write_text(f";; Generation failed: {e}")

    manifest_path = _create_run_manifest([task["id"] for task in tasks], run_id, checkpoint_path)
    _run_benchmark_eval(manifest_path)
    eval_results = _load_eval_results(run_id)

    for task in tasks:
        task_id = task["id"]
        eval_result = eval_results.get(task_id, {})
        passed = eval_result.get("outcome") == ":pass"
        result = {
            "task_id": task_id,
            "passed": passed,
            "source": task.get("source", "unknown"),
            "outcome": eval_result.get("outcome", ":missing"),
            "syntax_ok": eval_result.get("syntax_ok", False),
            "kondo_ok": eval_result.get("kondo_ok", False),
            "tests_ok": eval_result.get("tests_ok", False),
            "wall_ms": eval_result.get("wall_ms"),
        }
        results.append(result)
        if passed:
            pass_count += 1

    # Calculate metrics
    pass_rate = pass_count / len(tasks) if tasks else 0.0

    summary = {
        "checkpoint": checkpoint_path,
        "run_id": run_id,
        "num_tasks": len(tasks),
        "pass_count": pass_count,
        "pass_rate": pass_rate,
        "results": results,
    }

    # Save results
    output_dir = os.path.dirname(output_path)
    if output_dir:
        os.makedirs(output_dir, exist_ok=True)
    with open(output_path, "w") as f:
        json.dump(summary, f, indent=2)

    print(f"\nEvaluation complete!")
    print(f"  Pass rate: {pass_rate:.2%} ({pass_count}/{len(tasks)})")
    print(f"  Benchmark run ID: {run_id}")
    print(f"  Results saved to: {output_path}")

    return summary


def compare_models(
    base_results: Dict,
    sft_results: Dict,
    output_path: str,
):
    """
    Compare base model and SFT model results.

    Creates a comparison report showing improvements and regressions.
    """
    base_by_task = {r["task_id"]: r for r in base_results["results"]}
    sft_by_task = {r["task_id"]: r for r in sft_results["results"]}

    improvements = []
    regressions = []
    unchanged = []

    for task_id in base_by_task:
        if task_id not in sft_by_task:
            continue

        base_passed = base_by_task[task_id].get("passed", False)
        sft_passed = sft_by_task[task_id].get("passed", False)

        if sft_passed and not base_passed:
            improvements.append(task_id)
        elif base_passed and not sft_passed:
            regressions.append(task_id)
        elif sft_passed == base_passed:
            unchanged.append(task_id)

    comparison = {
        "base_pass_rate": base_results["pass_rate"],
        "sft_pass_rate": sft_results["pass_rate"],
        "improvement": sft_results["pass_rate"] - base_results["pass_rate"],
        "improvements": improvements,
        "regressions": regressions,
        "unchanged": unchanged,
        "improvement_count": len(improvements),
        "regression_count": len(regressions),
    }

    # Save comparison
    with open(output_path, "w") as f:
        json.dump(comparison, f, indent=2)

    print(f"\n=== Model Comparison ===")
    print(f"Base model pass rate:   {base_results['pass_rate']:.2%}")
    print(f"SFT model pass rate:    {sft_results['pass_rate']:.2%}")
    print(f"Improvement:            {comparison['improvement']:+.2%}")
    print(f"Improvements:           {len(improvements)} tasks")
    print(f"Regressions:            {len(regressions)} tasks")
    print(f"Unchanged:              {len(unchanged)} tasks")
    print(f"Comparison saved to:    {output_path}")

    return comparison


def main():
    parser = argparse.ArgumentParser(description="Evaluate SFT model")
    parser.add_argument(
        "--checkpoint",
        type=str,
        default=None,
        help="Path to SFT checkpoint to evaluate"
    )
    parser.add_argument(
        "--base",
        action="store_true",
        help="Evaluate base model for comparison"
    )
    parser.add_argument(
        "--compare",
        type=str,
        default=None,
        help="Path to checkpoint to compare against base"
    )
    parser.add_argument(
        "--config",
        type=str,
        default="training/sft/config.yaml",
        help="Path to config file"
    )
    parser.add_argument(
        "--tasks",
        type=str,
        default="benchmark/tasks-v0.edn",
        help="Path to benchmark tasks file"
    )
    parser.add_argument(
        "--output",
        type=str,
        default=None,
        help="Output path for results"
    )
    parser.add_argument(
        "--num-samples",
        type=int,
        default=None,
        help="Limit number of tasks to evaluate"
    )

    args = parser.parse_args()

    # Load config for default paths
    config = load_config(args.config)

    if args.base:
        # Evaluate base model
        model_name = config.get("model", "Qwen3-8B-Base")
        output_path = args.output or f"results/eval-base-{model_name}.json"
        evaluate_on_benchmark(
            checkpoint_path=model_name,  # Base model uses model name
            tasks_path=args.tasks,
            output_path=output_path,
            num_samples=args.num_samples,
        )

    elif args.compare:
        # Compare base vs SFT
        model_name = config.get("model", "Qwen3-8B-Base")

        # First evaluate base
        base_output = f"results/eval-base-comparison.json"
        base_results = evaluate_on_benchmark(
            checkpoint_path=model_name,
            tasks_path=args.tasks,
            output_path=base_output,
            num_samples=args.num_samples,
        )

        # Then evaluate SFT
        sft_output = f"results/eval-sft-comparison.json"
        sft_results = evaluate_on_benchmark(
            checkpoint_path=args.compare,
            tasks_path=args.tasks,
            output_path=sft_output,
            num_samples=args.num_samples,
        )

        # Create comparison
        compare_output = args.output or "results/comparison.json"
        compare_models(base_results, sft_results, compare_output)

    else:
        # Evaluate single checkpoint
        if not args.checkpoint:
            parser.error("--checkpoint or --base or --compare required")

        output_path = args.output or f"results/eval-{Path(args.checkpoint).name}.json"
        evaluate_on_benchmark(
            checkpoint_path=args.checkpoint,
            tasks_path=args.tasks,
            output_path=output_path,
            num_samples=args.num_samples,
        )


if __name__ == "__main__":
    main()
