"""
ClojurePiEnv - Tinker Environment for RLVR with Clojure code verification.

This module implements a custom Tinker Env subclass that uses Pi's
clj-verifier tools to provide reward signals for RL training.
"""

import json
from dataclasses import dataclass
from typing import Any, Dict, List, Optional

from tinker import Env, StepResult

from .pi_client import PiRPCClient
from .rewards import compute_shaped_reward


@dataclass
class ClojureTask:
    """A Clojure programming task for RLVR."""

    task_id: str
    prompt: str
    entrypoint: str
    tests: Optional[str] = None
    source: str = "benchmark"  # benchmark, 4clojure, etc.


class ClojurePiEnv(Env):
    """
    Tinker Environment for Clojure code generation with Pi as verifier.

    This environment connects to Pi (the AI agent) via RPC to evaluate
    generated Clojure code using the clj-verifier tools.

    Reward components:
    - eval_success: Code evaluates without throwing (0.1 weight)
    - kondo_clean: Passes clj-kondo linting (0.2 weight)
    - namespace_loads: Namespace can be required (0.1 weight)
    - tests_pass: All tests pass (0.6 weight)

    Episode terminates when all tests pass or max_steps is reached.
    """

    def __init__(
        self,
        pi_client: PiRPCClient,
        task: ClojureTask,
        max_steps: int = 10,
        reward_weights: Optional[Dict[str, float]] = None,
    ):
        """
        Initialize the Clojure environment.

        Args:
            pi_client: Pi RPC client for code evaluation
            task: The Clojure programming task
            max_steps: Maximum number of code generation attempts
            reward_weights: Custom weights for reward components
        """
        self.pi = pi_client
        self.task = task
        self.max_steps = max_steps
        self.step_count = 0

        # Default reward weights (from config)
        self.reward_weights = reward_weights or {
            "eval": 0.1,
            "kondo": 0.2,
            "namespace": 0.1,
            "tests": 0.6,
        }

        # Tracking state
        self.last_result = None
        self.last_kondo = None
        self.last_tests = None

    def reset(self) -> str:
        """
        Reset the environment for a new episode.

        Returns:
            Initial task prompt
        """
        self.step_count = 0
        self.last_result = None
        self.last_kondo = None
        self.last_tests = None
        return self.task.prompt

    def step(self, action: str) -> StepResult:
        """
        Execute one step: evaluate generated code.

        Args:
            action: Generated Clojure code

        Returns:
            StepResult with reward, done flag, and info
        """
        self.step_count += 1

        # Evaluate the code using Pi's clj-verifier tools
        try:
            # 1. Check if code evaluates (form evaluation)
            eval_result = self.pi.call_tool(
                "clj_eval_form",
                {"code": action}
            )
            eval_ok = eval_result.get("ok", False)
            namespace_loads = eval_result.get("namespace_loads", False)

            # 2. Run clj-kondo linting
            kondo_result = self.pi.call_tool(
                "clj_kondo_check",
                {"code": action}
            )
            kondo_clean = kondo_result.get("clean", False)

            # 3. Run tests
            test_result = self.pi.call_tool(
                "clj_run_tests",
                {
                    "entrypoint": self.task.entrypoint,
                    "code": action,
                }
            )
            tests_pass = test_result.get("all_pass", False)
            test_failures = test_result.get("failures", [])

            # Store for logging
            self.last_result = eval_result
            self.last_kondo = kondo_result
            self.last_tests = test_result

            # Compute shaped reward
            reward = compute_shaped_reward(
                eval_ok=eval_ok,
                kondo_clean=kondo_clean,
                namespace_loads=namespace_loads,
                tests_pass=tests_pass,
                weights=self.reward_weights,
            )

            # Episode ends when tests pass or max steps reached
            done = tests_pass or self.step_count >= self.max_steps

            # Build info dict
            info = {
                "step": self.step_count,
                "eval_ok": eval_ok,
                "kondo_clean": kondo_clean,
                "namespace_loads": namespace_loads,
                "tests_pass": tests_pass,
                "test_failures": test_failures,
                "reward_components": {
                    "eval": 0.1 if eval_ok else 0.0,
                    "kondo": 0.2 if kondo_clean else 0.0,
                    "namespace": 0.1 if namespace_loads else 0.0,
                    "tests": 0.6 if tests_pass else 0.0,
                },
            }

            return StepResult(
                reward=reward,
                done=done,
                info=info,
            )

        except TimeoutError:
            # Pi tool call timed out
            return StepResult(
                reward=self.reward_weights.get("timeout_penalty", -0.5),
                done=True,
                info={"error": "timeout", "step": self.step_count},
            )

        except Exception as e:
            # Error during evaluation
            return StepResult(
                reward=self.reward_weights.get("syntax_error_penalty", -1.0),
                done=True,
                info={"error": str(e), "step": self.step_count},
            )


class ClojurePiEnvBuilder:
    """
    Builder for creating batches of ClojurePiEnv instances.

    Used with Tinker's EnvGroupBuilder for parallel rollouts.
    """

    def __init__(
        self,
        pi_client: PiRPCClient,
        max_steps: int = 10,
        reward_weights: Optional[Dict[str, float]] = None,
    ):
        self.pi_client = pi_client
        self.max_steps = max_steps
        self.reward_weights = reward_weights

    def build(self, task: ClojureTask) -> ClojurePiEnv:
        """Build a single environment for the given task."""
        return ClojurePiEnv(
            pi_client=self.pi_client,
            task=task,
            max_steps=self.max_steps,
            reward_weights=self.reward_weights,
        )

    def build_batch(self, tasks: List[ClojureTask]) -> List[ClojurePiEnv]:
        """Build a batch of environments for parallel rollout."""
        return [self.build(task) for task in tasks]


def create_task_from_dict(task_dict: Dict[str, Any]) -> ClojureTask:
    """
    Create a ClojureTask from a benchmark task dictionary.

    Args:
        task_dict: Task dictionary from benchmark/tasks-v0.edn

    Returns:
        ClojureTask instance
    """
    # Extract prompt - could be inline or reference
    prompt = task_dict.get("prompt", "")
    if "prompt_ref" in task_dict:
        # Would load from referenced file
        pass

    # Extract tests
    tests = task_dict.get("tests", "")
    if "tests_ref" in task_dict:
        # Would load from referenced file
        pass

    return ClojureTask(
        task_id=task_dict.get("id", "unknown"),
        prompt=prompt,
        entrypoint=task_dict.get("entrypoint", "solution"),
        tests=tests,
        source=task_dict.get("source", "benchmark"),
    )
