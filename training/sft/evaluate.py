#!/usr/bin/env python3
"""
Evaluation Script for SFT Model.

This script evaluates a fine-tuned model against the benchmark tasks
and compares results with the base model.

Usage:
    python training/sft/evaluate.py --checkpoint checkpoints/sft/final
    python training/sft/evaluate.py --base --compare checkpoints/sft/final
"""

import argparse
import json
import os
from pathlib import Path
from typing import Any, Dict, List

import tinker
from tinker import ServiceClient
import yaml


def load_benchmark_tasks(tasks_path: str) -> List[Dict[str, Any]]:
    """Load benchmark tasks from EDN or JSON file."""
    # For now, assume JSON format for simplicity
    # In production, would handle EDN properly
    with open(tasks_path) as f:
        if tasks_path.endswith(".json"):
            return json.load(f)
        elif tasks_path.endswith(".edn"):
            # Would need proper EDN parser
            # For placeholder, return empty
            return []
    return []


def load_config(config_path: str) -> Dict:
    """Load configuration from YAML."""
    with open(config_path) as f:
        return yaml.safe_load(f)


def generate_solution(client, task: Dict, max_tokens: int = 512) -> str:
    """
    Generate a solution for a benchmark task.

    Args:
        client: Tinker sampling client
        task: Task dict with prompt information
        max_tokens: Maximum tokens to generate

    Returns:
        Generated solution code
    """
    # Extract prompt from task
    # Task format varies by source
    prompt = task.get("prompt", "")
    if not prompt and "prompt_ref" in task:
        # Would load from referenced file
        prompt = "// Task placeholder"

    # Generate using Tinker sampling
    response = client.sample(
        prompt,
        max_tokens=max_tokens,
        temperature=0.2,  # Low temperature for code generation
        top_p=0.95,
    )

    return response.text


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
    print(f"Loading checkpoint from: {checkpoint_path}")

    # Load sampling client from checkpoint
    # In Tinker, this would create a client that can sample from trained weights
    # client = SamplingClient.from_checkpoint(checkpoint_path)
    client = None  # Placeholder

    # Load benchmark tasks
    tasks = load_benchmark_tasks(tasks_path)
    if num_samples:
        tasks = tasks[:num_samples]

    print(f"Evaluating on {len(tasks)} tasks...")

    results = []
    pass_count = 0

    for i, task in enumerate(tasks):
        task_id = task.get("id", f"task-{i}")
        print(f"[{i+1}/{len(tasks)}] Generating solution for {task_id}...")

        try:
            # Generate solution
            solution = generate_solution(client, task)

            # Run tests (would use the benchmark runner)
            # For placeholder, just store the generation
            passed = False  # Would run actual tests

            result = {
                "task_id": task_id,
                "solution": solution,
                "passed": passed,
                "source": task.get("source", "unknown"),
            }
            results.append(result)

            if passed:
                pass_count += 1

        except Exception as e:
            print(f"  Error: {e}")
            results.append({
                "task_id": task_id,
                "solution": None,
                "passed": False,
                "error": str(e),
            })

    # Calculate metrics
    pass_rate = pass_count / len(tasks) if tasks else 0.0

    summary = {
        "checkpoint": checkpoint_path,
        "num_tasks": len(tasks),
        "pass_count": pass_count,
        "pass_rate": pass_rate,
        "results": results,
    }

    # Save results
    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    with open(output_path, "w") as f:
        json.dump(summary, f, indent=2)

    print(f"\nEvaluation complete!")
    print(f"  Pass rate: {pass_rate:.2%} ({pass_count}/{len(tasks)})")
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
