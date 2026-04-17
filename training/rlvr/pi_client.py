"""
Pi RPC Client for communicating with the Pi AI agent.

This module provides a Python client for interacting with Pi's
clj-verifier tools via RPC, enabling RLVR training with code verification.
"""

import json
from dataclasses import dataclass, field
from typing import Any, Dict, List, Optional
from pathlib import Path

try:
    import httpx
    HAS_HTTPX = True
except ImportError:
    HAS_HTTPX = False


@dataclass
class ToolCall:
    """A tool call invocation to Pi."""

    name: str
    params: Dict[str, Any]
    timeout: float = 30.0


@dataclass
class ToolResult:
    """Result from a Pi tool call."""

    success: bool
    data: Dict[str, Any]
    error: Optional[str] = None


class PiRPCError(Exception):
    """Exception raised when Pi RPC call fails."""

    def __init__(self, message: str, code: Optional[int] = None):
        super().__init__(message)
        self.code = code


class PiRPCClient:
    """
    RPC client for communicating with Pi agent.

    Connects to Pi running in RPC mode and invokes clj-verifier tools:
    - clj_eval_form: Evaluate a Clojure form
    - clj_kondo_check: Run clj-kondo linting
    - clj_run_tests: Execute clojure.test tests
    """

    def __init__(
        self,
        rpc_url: str = "http://localhost:8080",
        timeout: float = 30.0,
        max_retries: int = 3,
        trace_path: Optional[str] = None,
    ):
        """
        Initialize the Pi RPC client.

        Args:
            rpc_url: URL of Pi's RPC server
            timeout: Default timeout for requests
            max_retries: Maximum number of retry attempts
            trace_path: Optional path to save JSONL trace logs
        """
        self.rpc_url = rpc_url.rstrip("/")
        self.timeout = timeout
        self.max_retries = max_retries
        self.trace_path = trace_path

        # Session for HTTP requests
        if HAS_HTTPX:
            self._client = httpx.Client(timeout=timeout)
        else:
            self._client = None

        # Trace log
        self._trace_entries: List[Dict] = []

    def call_tool(
        self,
        name: str,
        params: Dict[str, Any],
        timeout: Optional[float] = None,
    ) -> Dict[str, Any]:
        """
        Call a Pi tool by name.

        Args:
            name: Tool name (e.g., "clj_eval_form")
            params: Tool parameters
            timeout: Request timeout (uses default if None)

        Returns:
            Tool result as dictionary

        Raises:
            PiRPCError: If the call fails
        """
        if self._client is None:
            raise PiRPCError("httpx not available, install with: pip install httpx")

        timeout = timeout or self.timeout
        url = f"{self.rpc_url}/tools/{name}"

        for attempt in range(self.max_retries):
            try:
                response = self._client.post(
                    url,
                    json=params,
                    timeout=timeout,
                )
                response.raise_for_status()

                result = response.json()

                # Log trace entry
                self._log_entry(name, params, result)

                # Check for tool-level errors
                if result.get("error"):
                    raise PiRPCError(
                        result.get("error", "Unknown tool error"),
                        code=result.get("code"),
                    )

                return result

            except httpx.TimeoutException:
                if attempt == self.max_retries - 1:
                    raise PiRPCError(f"Timeout calling tool {name}")
            except httpx.HTTPStatusError as e:
                if attempt == self.max_retries - 1:
                    raise PiRPCError(
                        f"HTTP error calling tool {name}: {e.response.status_code}",
                        code=e.response.status_code,
                    )
            except json.JSONDecodeError as e:
                if attempt == self.max_retries - 1:
                    raise PiRPCError(f"Invalid JSON response from {name}: {e}")

        raise PiRPCError("Max retries exceeded")

    def submit_prompt(
        self,
        prompt: str,
        max_tokens: int = 512,
        temperature: float = 0.7,
    ) -> str:
        """
        Submit a prompt to Pi and get the generated response.

        This is used for agent-mode interaction where Pi generates
        code based on the task prompt.

        Args:
            prompt: Task prompt
            max_tokens: Maximum tokens to generate
            temperature: Sampling temperature

        Returns:
            Generated text
        """
        result = self.call_tool(
            "generate",
            {
                "prompt": prompt,
                "max_tokens": max_tokens,
                "temperature": temperature,
            },
        )
        return result.get("text", "")

    def eval_clojure_form(self, code: str) -> Dict[str, Any]:
        """
        Evaluate a Clojure form via Pi's clj-verifier.

        Args:
            code: Clojure code to evaluate

        Returns:
            Result dict with keys:
            - ok: bool, whether evaluation succeeded
            - result: evaluation result (if ok)
            - error: error message (if not ok)
            - namespace_loads: bool, whether ns loads
        """
        return self.call_tool("clj_eval_form", {"code": code})

    def kondo_check(self, code: str) -> Dict[str, Any]:
        """
        Run clj-kondo linting via Pi's clj-verifier.

        Args:
            code: Clojure code to check

        Returns:
            Result dict with keys:
            - clean: bool, no linter warnings
            - warnings: list of warning dicts
        """
        return self.call_tool("clj_kondo_check", {"code": code})

    def run_tests(
        self,
        entrypoint: str,
        code: str,
    ) -> Dict[str, Any]:
        """
        Run clojure.test tests via Pi's clj-verifier.

        Args:
            entrypoint: Function name to test
            code: Code containing the function

        Returns:
            Result dict with keys:
            - all_pass: bool, all tests passed
            - failures: list of failure details
            - test_count: int, number of tests run
        """
        return self.call_tool(
            "clj_run_tests",
            {
                "entrypoint": entrypoint,
                "code": code,
            },
        )

    def _log_entry(
        self,
        tool: str,
        params: Dict[str, Any],
        result: Dict[str, Any],
    ):
        """Log a trace entry for later analysis."""
        entry = {
            "tool": tool,
            "params": params,
            "result": result,
        }
        self._trace_entries.append(entry)

        # Write to file if trace_path is set
        if self.trace_path:
            self._write_trace()

    def _write_trace(self):
        """Write trace entries to JSONL file."""
        if not self.trace_path:
            return

        path = Path(self.trace_path)
        path.parent.mkdir(parents=True, exist_ok=True)

        with open(path, "a") as f:
            for entry in self._trace_entries:
                f.write(json.dumps(entry) + "\n")

        self._trace_entries.clear()

    def get_trace(self) -> List[Dict[str, Any]]:
        """Get all trace entries."""
        return self._trace_entries.copy()

    def close(self):
        """Close the RPC client and flush any pending traces."""
        if self._client:
            self._client.close()
        self._write_trace()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.close()


def create_pi_client(
    rpc_url: str = "http://localhost:8080",
    timeout: float = 30.0,
    max_retries: int = 3,
    trace_path: Optional[str] = None,
) -> PiRPCClient:
    """
    Factory function to create a Pi RPC client.

    Args:
        rpc_url: URL of Pi's RPC server
        timeout: Default timeout for requests
        max_retries: Maximum number of retry attempts
        trace_path: Optional path to save JSONL trace logs

    Returns:
        Configured PiRPCClient instance
    """
    return PiRPCClient(
        rpc_url=rpc_url,
        timeout=timeout,
        max_retries=max_retries,
        trace_path=trace_path,
    )
