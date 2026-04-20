/**
 * clj-verifier.ts - Pi extension for Clojure code verification
 *
 * This extension provides structured tools for Clojure code verification:
 * - clj_kondo_check: Lint Clojure code via clj-kondo
 * - clj_eval_form: Evaluate a single Clojure form in persistent REPL
 * - clj_run_tests: Run clojure.test suite
 * - benchmark_get_task: Fetch a benchmark task prompt
 *
 * API ADAPTATION NOTES:
 * This extension follows a common pattern for tool definition:
 * - Each tool has an inputSchema (JSON Schema) for validation
 * - Each tool has a handler function that executes the logic
 * - Tools return structured JSON for easy parsing
 *
 * To adapt to the actual pi-mono SDK:
 * 1. Import from the correct @anthropic-ai/pi-sdk module
 * 2. Use the actual Tool/Extension types from the SDK
 * 3. Adjust the export format to match the extension registration API
 * 4. Update error handling to use pi-mono's error conventions
 *
 * The core logic (shell commands, EDN parsing) should remain stable.
 */

import { execSync } from "child_process";
import { readFileSync, existsSync } from "fs";
import { join } from "path";
import { type ExtensionAPI } from "@mariozechner/pi-coding-agent";
import { Type } from "@sinclair/typebox";

// ============================================
// Type Definitions
// ============================================

interface KondoFinding {
  level: "error" | "warning" | "info";
  message: string;
  line: number;
  column: number;
}

interface KondoResult {
  ok: boolean;
  clean: boolean;
  findings: KondoFinding[];
}

interface EvalResult {
  ok: boolean;
  result: string;
  namespace_loads: boolean;
  error?: string;
}

interface TestResult {
  name: string;
  pass: boolean;
  error?: string;
}

interface TestSummary {
  pass: number;
  fail: number;
  error: number;
}

interface RunTestsResult {
  ok: boolean;
  all_pass: boolean;
  tests: TestResult[];
  summary: TestSummary;
}

interface BenchmarkTask {
  task_id: string;
  prompt: string;
  entrypoint: string;
  test_code: string;
}

// ============================================
// Helper Functions
// ============================================

/**
 * Safely execute a shell command and return the result.
 * Throws on non-zero exit if allowNonZero is false.
 */
function execCommand(
  command: string,
  options: { timeout?: number; cwd?: string; allowNonZero?: boolean } = {}
): { stdout: string; stderr: string; exitCode: number | null } {
  const { timeout = 30000, cwd, allowNonZero = false } = options;

  try {
    const stdout = execSync(command, {
      encoding: "utf-8",
      timeout,
      cwd,
      stdio: ["ignore", "pipe", "pipe"],
    });
    return { stdout, stderr: "", exitCode: 0 };
  } catch (error: any) {
    if (allowNonZero) {
      return {
        stdout: error.stdout || "",
        stderr: error.stderr || "",
        exitCode: error.status || 1,
      };
    }
    throw error;
  }
}

/**
 * Find the project root by looking for benchmark directory or deps.edn
 */
function findProjectRoot(startPath: string = process.cwd()): string {
  let currentPath = startPath;
  for (let i = 0; i < 10; i++) {
    if (existsSync(join(currentPath, "benchmark")) ||
        existsSync(join(currentPath, "deps.edn"))) {
      return currentPath;
    }
    const parent = join(currentPath, "..");
    if (parent === currentPath) break;
    currentPath = parent;
  }
  return startPath;
}

// ============================================
// Tool Implementations
// ============================================

/**
 * clj_kondo_check - Lint Clojure code via clj-kondo
 *
 * Input can be either:
 * - code: string of Clojure code to lint (saved to temp file)
 * - file_path: path to existing Clojure file
 *
 * Returns structured findings with level, message, line, column.
 */
async function cljKondoCheck(input: {
  code?: string;
  file_path?: string;
}): Promise<KondoResult> {
  const { code, file_path } = input;

  if (!code && !file_path) {
    throw new Error("Either 'code' or 'file_path' must be provided");
  }

  let targetFile = file_path;
  let tempFileCreated = false;

  // If code is provided, write to a temp file
  if (code) {
    const tempDir = "/tmp/clj-verifier";
    execCommand(`mkdir -p ${tempDir}`);
    targetFile = `${tempDir}/temp_${Date.now()}.clj`;
    execCommand(`cat > ${targetFile} << 'EOF'\n${code}\nEOF`);
    tempFileCreated = true;
  }

  try {
    // Run clj-kondo with JSON output
    const result = execCommand(`clj-kondo --lint ${targetFile} --format json`, {
      timeout: 10000,
      allowNonZero: true,
    });

    const findings: KondoFinding[] = [];
    let ok = true;
    let clean = true;

    if (result.exitCode === 0 && result.stdout) {
      try {
        const kondoOutput = JSON.parse(result.stdout);

        for (const finding of (kondoOutput.findings || [])) {
          const level = finding.level || "info";
          if (level === "error") {
            ok = false;
            clean = false;
          } else if (level === "warning") {
            clean = false;
          }

          findings.push({
            level,
            message: finding.message || "",
            line: finding.row || finding.line || 0,
            column: finding.col || finding.column || 0,
          });
        }
      } catch (parseError) {
        // clj-kondo might have returned non-JSON output
        ok = false;
        clean = false;
        findings.push({
          level: "error",
          message: `Failed to parse clj-kondo output: ${result.stdout}`,
          line: 0,
          column: 0,
        });
      }
    } else if (result.exitCode !== 0) {
      ok = false;
      clean = false;
      if (result.stderr) {
        findings.push({
          level: "error",
          message: result.stderr,
          line: 0,
          column: 0,
        });
      }
    }

    return { ok, clean, findings };
  } finally {
    // Clean up temp file
    if (tempFileCreated && targetFile) {
      execCommand(`rm -f ${targetFile}`, { allowNonZero: true });
    }
  }
}

/**
 * clj_eval_form - Evaluate a single Clojure form in persistent REPL
 *
 * Input:
 * - code: Clojure form to evaluate
 * - namespace: optional namespace to load before evaluating
 *
 * Returns:
 * - result: string representation of the result
 * - namespace_loads: whether the namespace loaded successfully
 */
async function cljEvalForm(input: {
  code: string;
  namespace?: string;
}): Promise<EvalResult> {
  const { code, namespace } = input;

  if (!code) {
    throw new Error("'code' must be provided");
  }

  try {
    // Build the Clojure expression to evaluate
    let clojureExpr = "";

    if (namespace) {
      // Try to load the namespace first
      clojureExpr += `(try (require '${namespace}) (catch Exception e# ::ns-load-fail)) `;
    }

    // Evaluate the form and capture result
    clojureExpr += `(try (pr-str ${code}) (catch Exception e# (pr-str {:error (.getMessage e#) :class (class e#)})))`;

    // Execute via Clojure CLI with socket REPL for persistence would be better
    // For now, use simple -e execution (slower but works)
    const result = execCommand(`clojure -M -e "${clojureExpr.replace(/"/g, '\\"')}"`, {
      timeout: 15000,
    });

    const output = result.stdout.trim();

    // Check for namespace load failure
    if (output.includes("::ns-load-fail")) {
      return {
        ok: true,
        result: "",
        namespace_loads: false,
        error: `Namespace '${namespace}' failed to load`,
      };
    }

    // Check for error in result
    if (output.startsWith("{")) {
      try {
        const parsed = JSON.parse(output);
        if (parsed.error) {
          return {
            ok: true,
            result: "",
            namespace_loads: true,
            error: parsed.error,
          };
        }
      } catch {
        // Not JSON, continue
      }
    }

    return {
      ok: true,
      result: output,
      namespace_loads: true,
    };
  } catch (error: any) {
    return {
      ok: false,
      result: "",
      namespace_loads: false,
      error: error.message || String(error),
    };
  }
}

/**
 * clj_run_tests - Run clojure.test suite
 *
 * Input:
 * - entrypoint: the namespace or file containing tests
 * - test_file: optional specific test file to run
 *
 * Returns structured test results.
 */
async function cljRunTests(input: {
  entrypoint: string;
  test_file?: string;
}): Promise<RunTestsResult> {
  const { entrypoint, test_file } = input;

  if (!entrypoint) {
    throw new Error("'entrypoint' must be provided");
  }

  const projectRoot = findProjectRoot();

  try {
    // Build the test expression
    let testExpr = "";

    if (test_file) {
      // Load specific test file
      testExpr = `(load-file "${test_file}")`;
    } else {
      // Require the namespace
      testExpr = `(require '${entrypoint})`;
    }

    // Run tests and capture results
    testExpr += `
      (let [results (apply hash-map (interleave (range) (map #(hash-map :name % :status (:status (meta %))) (ns-publics '${entrypoint}))))]
        (binding [*out* (java.io.StringWriter.)]
          (clojure.test/run-tests '${entrypoint})
          (str *out*)))
    `;

    const result = execCommand(`clojure -M:test -e "${testExpr.replace(/"/g, '\\"')}"`, {
      cwd: projectRoot,
      timeout: 30000,
      allowNonZero: true,
    });

    const output = result.stdout + result.stderr;

    // Parse test output
    // Clojure test output format:
    // Testing user.humaneval-001
    //
    // FAIL in (has-close-elements) (humaneval_001_test.clj:12)
    // Expected: true
    //   Actual: false
    //
    // Ran 1 tests containing 3 assertions.
    // 1 failures, 0 errors.

    const tests: TestResult[] = [];
    const summary: TestSummary = { pass: 0, fail: 0, error: 0 };

    // Parse test results (simplified regex-based approach)
    const testMatches = output.matchAll(/Testing ([^\n]+)/g);
    for (const match of testMatches) {
      tests.push({
        name: match[1],
        pass: !output.includes("FAIL") && !output.includes("ERROR"),
      });
    }

    // Parse summary
    const summaryMatch = output.match(/(\d+) failures, (\d+) errors/);
    if (summaryMatch) {
      summary.fail = parseInt(summaryMatch[1], 10);
      summary.error = parseInt(summaryMatch[2], 10);
      // Total tests - failures - errors = passes
      const totalMatch = output.match(/Ran (\d+) tests/);
      if (totalMatch) {
        const total = parseInt(totalMatch[1], 10);
        summary.pass = total - summary.fail - summary.error;
      }
    }

    // If no failures/errors mentioned, check for explicit success
    if (output.includes("0 failures, 0 errors")) {
      const totalMatch = output.match(/Ran (\d+) tests/);
      if (totalMatch) {
        summary.pass = parseInt(totalMatch[1], 10);
      }
    }

    const all_pass = summary.fail === 0 && summary.error === 0;

    return {
      ok: true,
      all_pass,
      tests,
      summary,
    };
  } catch (error: any) {
    return {
      ok: false,
      all_pass: false,
      tests: [],
      summary: { pass: 0, fail: 0, error: 1 },
    };
  }
}

/**
 * benchmark_get_task - Fetch a benchmark task prompt
 *
 * Input:
 * - task_id: the task identifier to fetch
 *
 * Returns the task definition with prompt, entrypoint, and test code.
 */
async function benchmarkGetTask(input: {
  task_id: string;
}): Promise<BenchmarkTask> {
  const { task_id } = input;

  if (!task_id) {
    throw new Error("'task_id' must be provided");
  }

  const projectRoot = findProjectRoot();
  const tasksFile = join(projectRoot, "benchmark", "tasks-v0.edn");

  if (!existsSync(tasksFile)) {
    throw new Error(`Benchmark tasks file not found: ${tasksFile}`);
  }

  try {
    // Use Clojure to read and query the EDN file
    const clojureExpr = `
      (require '[clojure.edn :as edn])
      (let [tasks (edn/read-string (slurp "${tasksFile}"))
            task (first (filter #(= (:id %) "${task_id}") tasks))]
        (if task
          (pr-str {:task_id (:id task)
                   :prompt (or (:prompt-ref task) "")
                   :entrypoint (str (:entrypoint task))
                   :test_code (or (:tests-ref task) "")})
          (pr-str {:error "Task not found"})))
    `;

    const result = execCommand(`clojure -M -e "${clojureExpr.replace(/"/g, '\\"')}"`, {
      timeout: 10000,
    });

    const output = JSON.parse(result.stdout);

    if (output.error) {
      throw new Error(output.error);
    }

    // If prompt-ref is a file, read the file contents
    let prompt = output.prompt;
    if (prompt && typeof prompt === "object" && prompt.kind === "file") {
      const promptPath = join(projectRoot, prompt.path);
      if (existsSync(promptPath)) {
        prompt = readFileSync(promptPath, "utf-8");
      }
    }

    // If tests-ref is a file, read the file contents
    let test_code = output.test_code;
    if (test_code && typeof test_code === "object" && test_code.kind === "file") {
      const testPath = join(projectRoot, test_code.path);
      if (existsSync(testPath)) {
        test_code = readFileSync(testPath, "utf-8");
      }
    }

    return {
      task_id: output.task_id,
      prompt: typeof prompt === "string" ? prompt : JSON.stringify(prompt),
      entrypoint: output.entrypoint,
      test_code: typeof test_code === "string" ? test_code : JSON.stringify(test_code),
    };
  } catch (error: any) {
    throw new Error(`Failed to fetch task '${task_id}': ${error.message}`);
  }
}

// ============================================
// Extension Definition (Pi factory function)
// ============================================

export default function cljVerifier(pi: ExtensionAPI) {
	// clj_kondo_check
	pi.registerTool({
		name: "clj_kondo_check",
		label: "Lint Clojure code",
		description: "Lint Clojure code using clj-kondo static analyzer. Returns structured findings with level, message, line, and column.",
		parameters: Type.Object({
			code: Type.Optional(Type.String({ description: "Clojure code to lint (as string)" })),
			file_path: Type.Optional(Type.String({ description: "Path to Clojure file to lint" })),
		}),
		async execute(_toolCallId, params, _signal, _onUpdate, _ctx) {
			const result = await cljKondoCheck(params);
			return {
				content: [{ type: "text" as const, text: JSON.stringify(result, null, 2) }],
			};
		},
	});

	// clj_eval_form
	pi.registerTool({
		name: "clj_eval_form",
		label: "Evaluate Clojure form",
		description: "Evaluate a single Clojure form. Returns the result string and whether the namespace loaded successfully.",
		parameters: Type.Object({
			code: Type.String({ description: "Clojure form to evaluate" }),
			namespace: Type.Optional(Type.String({ description: "Optional namespace to load before evaluating" })),
		}),
		async execute(_toolCallId, params, _signal, _onUpdate, _ctx) {
			const result = await cljEvalForm(params);
			return {
				content: [{ type: "text" as const, text: JSON.stringify(result, null, 2) }],
			};
		},
	});

	// clj_run_tests
	pi.registerTool({
		name: "clj_run_tests",
		label: "Run Clojure tests",
		description: "Run clojure.test suite and return structured results with pass/fail/error counts.",
		parameters: Type.Object({
			entrypoint: Type.String({ description: "Namespace or file containing tests" }),
			test_file: Type.Optional(Type.String({ description: "Optional specific test file to run" })),
		}),
		async execute(_toolCallId, params, _signal, _onUpdate, _ctx) {
			const result = await cljRunTests(params);
			return {
				content: [{ type: "text" as const, text: JSON.stringify(result, null, 2) }],
			};
		},
	});

	// benchmark_get_task
	pi.registerTool({
		name: "benchmark_get_task",
		label: "Get benchmark task",
		description: "Fetch a benchmark task by ID. Returns the task prompt, entrypoint, and test code.",
		parameters: Type.Object({
			task_id: Type.String({ description: "Task identifier to fetch" }),
		}),
		async execute(_toolCallId, params, _signal, _onUpdate, _ctx) {
			const result = await benchmarkGetTask(params);
			return {
				content: [{ type: "text" as const, text: JSON.stringify(result, null, 2) }],
			};
		},
	});
}
