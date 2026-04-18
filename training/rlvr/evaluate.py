#!/usr/bin/env python3
"""
Lightweight Clojure evaluator for RLVR training rollouts.

Provides two evaluation modes:
1. evaluate_single() - subprocess-based eval for a single code/test pair
2. evaluate_batch() - write candidates + manifest, run via clojure -M:bench evaluate

Binary reward: 1.0 (tests pass) or 0.0 (fail).
"""

import json
import os
import re
import subprocess
import tempfile
import time
from pathlib import Path
from typing import Dict, List, Optional, Tuple

ROOT = Path(__file__).resolve().parent.parent.parent


def evaluate_single(
    code: str,
    test_path: str,
    timeout: int = 10,
) -> Tuple[bool, str]:
    """Evaluate a single Clojure code snippet against its test file.

    Uses a Clojure subprocess that loads the code file and test file,
    then checks for test passage.

    Args:
        code: The generated Clojure code (defn form).
        test_path: Absolute path to the test .clj file.
        timeout: Timeout in seconds for the subprocess.

    Returns:
        (passed: bool, error_info: str)
    """
    # Write code to a temp file
    with tempfile.NamedTemporaryFile(
        mode="w", suffix=".clj", delete=False, dir="/tmp"
    ) as f:
        f.write(code)
        code_path = f.name

    try:
        # Build a Clojure command that loads code then test.
        # Use plain `clojure` (no alias) with -e for inline evaluation.
        # The test file uses `(def candidate fn-name)` + `(run-test ...)`
        # so we load-file the candidate first, then the test.
        expr = (
            f'(try (load-file "{code_path}") '
            f'(load-file "{test_path}") '
            f'(catch Exception e (binding [*out* *err*] (println "ERROR:" (.getMessage e)))))'
        )
        cmd = ["clojure", "-e", expr]

        result = subprocess.run(
            cmd,
            capture_output=True,
            text=True,
            timeout=timeout,
            cwd=str(ROOT),
        )

        output = result.stdout + result.stderr

        # Check for test results in output
        # clojure.test run-test prints:
        #   nil  (on success, since run-test returns the result map)
        # And the test runner itself prints to stdout:
        #   "Ran 1 tests containing N assertions.\n0 failures, 0 errors."
        # But with -e, we get back the return value.
        # Check for explicit failure signals:
        if "FAIL in" in output or "ERROR in" in output:
            return False, output[-500:]

        if "failures, 0 errors" in output:
            match = re.search(r"(\d+) failures, (\d+) errors", output)
            if match and int(match.group(1)) == 0 and int(match.group(2)) == 0:
                return True, ""
            return False, output[-500:]

        # If there was an exception during load, return code is non-zero
        if result.returncode != 0:
            return False, output[-500:]

        # If we got here with return code 0, the load succeeded.
        # The test file calls (run-test ...) at load time which may
        # throw on failure. If no exception, tests passed.
        if "ERROR:" in output:
            return False, output[-500:]

        # Success if no failures detected
        return True, ""


    except subprocess.TimeoutExpired:
        return False, f"Timeout after {timeout}s"
    except Exception as e:
        return False, str(e)
    finally:
        os.unlink(code_path)


def evaluate_batch(
    candidates: Dict[str, str],
    tasks: List[Dict],
    run_id: str,
    entrypoint_map: Optional[Dict[str, str]] = None,
) -> Dict[str, float]:
    """Evaluate a batch of candidates using the benchmark pipeline.

    Writes all candidates to benchmark/candidates/{run_id}/,
    creates a run manifest, runs clojure -M:bench evaluate,
    then parses results.

    Args:
        candidates: Dict mapping task_id -> code string.
        tasks: List of task dicts with task_id, prompt_path, tests_path, entrypoint.
        run_id: Unique identifier for this evaluation run.
        entrypoint_map: Optional mapping of task_id -> entrypoint function name.

    Returns:
        Dict mapping task_id -> reward (1.0 or 0.0).
    """
    from edn_format import Keyword, loads

    cand_dir = ROOT / "benchmark" / "candidates" / run_id
    cand_dir.mkdir(parents=True, exist_ok=True)

    # Write candidate files
    task_ids = []
    for task in tasks:
        tid = task["task_id"]
        if tid not in candidates:
            continue
        task_ids.append(tid)

        # Use the prompt file stem as the candidate filename
        # (matching how evaluate.clj looks up candidates)
        prompt_stem = Path(task["prompt_path"]).stem
        out_file = cand_dir / f"{prompt_stem}.clj"
        out_file.write_text(candidates[tid])

    if not task_ids:
        return {}

    # Create run manifest
    manifest_path = _create_manifest(task_ids, run_id)

    # Run evaluation
    cmd = ["clojure", "-M:bench", "evaluate", str(manifest_path)]
    try:
        result = subprocess.run(
            cmd,
            capture_output=True,
            text=True,
            timeout=600,
            cwd=str(ROOT),
        )
    except subprocess.TimeoutExpired:
        print(f"  WARNING: Batch evaluation timed out for {run_id}")
        return {tid: 0.0 for tid in task_ids}

    # Parse results
    rewards = {}
    results_dir = ROOT / "benchmark" / "results" / run_id

    for tid in task_ids:
        result_file = results_dir / f"{tid}.edn"
        if result_file.exists():
            try:
                with open(result_file) as f:
                    r = loads(f.read())
                outcome = str(r[Keyword("outcome")])
                rewards[tid] = 1.0 if outcome == ":pass" else 0.0
            except Exception:
                rewards[tid] = 0.0
        else:
            rewards[tid] = 0.0

    return rewards


def _create_manifest(task_ids: List[str], run_id: str) -> Path:
    """Create a benchmark run manifest EDN file."""
    manifest = (
        '{:task-ids [' +
        ' '.join(f'"{tid}"' for tid in sorted(task_ids)) + ']\n'
        ' :policy {:kind :direct}\n'
        f' :tasks-file "benchmark/tasks-v0.edn"\n'
        ' :prompting {:template :rlvr :temperature 0.7 :samples 1}\n'
        ' :executor {:kind :container :image "clj-bench/eval:dev" :network :none}\n'
        f' :created-at "{time.strftime("%Y-%m-%dT%H:%M:%S.000000Z")}"\n'
        f' :run-id "{run_id}"\n'
        ' :benchmark-version :clj-bench/v0\n'
        ' :model {:provider :tinker :id "rlvr-qwen3-8b"}}}'
    )
    manifest_path = ROOT / "benchmark" / "runs" / f"{run_id}.edn"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(manifest)
    return manifest_path


def load_training_tasks(
    train_task_ids_path: str = "data/sft/train_task_ids.json",
    tasks_edn_path: str = "benchmark/tasks-v0.edn",
) -> List[Dict]:
    """Load training tasks (non-held-out) with prompt and test paths.

    Args:
        train_task_ids_path: Path to JSON file with training task ID list.
        tasks_edn_path: Path to benchmark tasks EDN file.

    Returns:
        List of task dicts with keys: task_id, prompt_path, tests_path, entrypoint.
    """
    from edn_format import Keyword, loads

    with open(ROOT / train_task_ids_path) as f:
        train_ids = set(json.load(f))

    with open(ROOT / tasks_edn_path) as f:
        all_tasks = loads(f.read())

    tasks = []
    for t in all_tasks:
        tid = str(t[Keyword("id")])
        if tid in train_ids:
            tasks.append({
                "task_id": tid,
                "prompt_path": str(t[Keyword("prompt-ref")][Keyword("path")]),
                "tests_path": str(t[Keyword("tests-ref")][Keyword("path")]),
                "entrypoint": str(t[Keyword("entrypoint")]),
            })
    return tasks
