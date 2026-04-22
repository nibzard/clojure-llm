/**
 * Tinker Clojure Code Generation Extension
 *
 * Routes Clojure code generation to Tinker-hosted specialized models
 * (Qwen3-8B/30B SFT+RLVR) via a persistent Python sidecar. The sidecar
 * runs an internal verification loop (syntax → kondo → tests) so the
 * host model never needs to orchestrate verification itself.
 *
 * Usage:
 *   Place in .pi/extensions/tinker-clojure/ — auto-discovered by pi.
 *   User: "Write a Clojure function that checks if a list has close elements"
 *   → Main model calls generate_clojure → sidecar → Tinker → verified result
 */

import { ChildProcess, spawn } from "node:child_process";
import { existsSync, readFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { type ExtensionAPI } from "@mariozechner/pi-coding-agent";
import { type Static, Type } from "@sinclair/typebox";

// ---------------------------------------------------------------------------
// Config
// ---------------------------------------------------------------------------

interface CheckpointConfig {
	checkpoints: Record<string, string>;
	default_checkpoint: string;
	base_tokenizer: string;
	use_chat_template?: boolean;
	verifier?: {
		default_num_samples?: number;
		test_timeout_sec?: number;
		temp_increase_per_retry?: number;
		max_temp?: number;
		kondo_feedback_max_chars?: number;
	};
}

const EXT_DIR = dirname(new URL(import.meta.url).pathname);

function loadConfig(): CheckpointConfig {
	const configPath = join(EXT_DIR, "config.json");
	if (!existsSync(configPath)) {
		throw new Error(`tinker-clojure: config.json not found at ${configPath}`);
	}
	return JSON.parse(readFileSync(configPath, "utf-8")) as CheckpointConfig;
}

// ---------------------------------------------------------------------------
// Sidecar lifecycle
// ---------------------------------------------------------------------------

let sidecar: ChildProcess | null = null;
let sidecarReady = false;
let requestSeq = 0;

interface PendingRequest {
	resolve: (value: unknown) => void;
	reject: (reason: unknown) => void;
}

const pendingRequests = new Map<string, PendingRequest>();
let sidecarBuffer = "";

function resolveCheckpoint(alias: string): string {
	const config = loadConfig();
	if (alias in config.checkpoints) {
		return config.checkpoints[alias];
	}
	// Pass through as-is (could be a full tinker:// path)
	return alias;
}

function resolvePythonExecutable(): string {
	const configured = process.env.TINKER_CLOJURE_PYTHON;
	if (configured) {
		return configured;
	}

	const candidates = [
		join(process.cwd(), ".venv", "bin", "python"),
		join(process.cwd(), "venv", "bin", "python"),
		join(EXT_DIR, ".venv", "bin", "python"),
		join(EXT_DIR, "venv", "bin", "python"),
	];

	for (const candidate of candidates) {
		if (existsSync(candidate)) {
			return candidate;
		}
	}

	return "python3";
}

function startSidecar(): void {
	if (sidecar && !sidecar.killed) {
		return;
	}

	const scriptPath = join(EXT_DIR, "tinker_bridge.py");
	const pythonExe = resolvePythonExecutable();
	sidecar = spawn(pythonExe, ["-u", scriptPath], {
		stdio: ["pipe", "pipe", "pipe"],
		env: {
			...process.env,
			TINKER_CLOJURE_PYTHON: pythonExe,
			TRANSFORMERS_NO_ADVISORY_WARNINGS: "1",
		},
	});

	sidecar.on("error", (err) => {
		console.error(`tinker-clojure sidecar error: ${err.message}`);
		sidecar = null;
		sidecarReady = false;
	});

	sidecar.on("exit", (code) => {
		console.error(`tinker-clojure sidecar exited with code ${code}`);
		sidecar = null;
		sidecarReady = false;
		// Reject all pending requests
		for (const [id, pending] of pendingRequests) {
			pending.reject(new Error("Sidecar exited"));
			pendingRequests.delete(id);
		}
	});

	sidecar.stdout!.on("data", (chunk: Buffer) => {
		sidecarBuffer += chunk.toString("utf-8");
		const lines = sidecarBuffer.split("\n");
		sidecarBuffer = lines.pop() || "";

		for (const line of lines) {
			if (!line.trim()) continue;
			try {
				const resp = JSON.parse(line) as { id: string; result?: unknown; error?: string };
				const pending = pendingRequests.get(resp.id);
				if (pending) {
					pendingRequests.delete(resp.id);
					if (resp.error) {
						pending.reject(new Error(resp.error));
					} else {
						pending.resolve(resp.result);
					}
				}
			} catch {
				// Ignore malformed lines
			}
		}
	});

	sidecar.stderr!.on("data", (chunk: Buffer) => {
		console.error(`tinker-clojure sidecar stderr: ${chunk.toString("utf-8")}`);
	});
}

function stopSidecar(): void {
	if (sidecar && !sidecar.killed) {
		sendToSidecar("shutdown", {}).then(
			() => {
				sidecar?.kill();
				sidecar = null;
				sidecarReady = false;
			},
			() => {
				sidecar?.kill();
				sidecar = null;
				sidecarReady = false;
			},
		);
	}
}

function sendToSidecar(method: string, params: Record<string, unknown>): Promise<unknown> {
	return new Promise((resolve, reject) => {
		if (!sidecar || sidecar.killed) {
			reject(new Error("Sidecar not running"));
			return;
		}

		const id = String(++requestSeq);
		const cmd = JSON.stringify({ id, method, params }) + "\n";

		pendingRequests.set(id, { resolve, reject });

		sidecar.stdin!.write(cmd, (err) => {
			if (err) {
				pendingRequests.delete(id);
				reject(err);
			}
		});
	});
}

async function ensureInitialized(checkpoint: string): Promise<void> {
	if (sidecarReady) return;

	const config = loadConfig();
	const resolvedCheckpoint = resolveCheckpoint(checkpoint);

	await sendToSidecar("init", {
		checkpoint: resolvedCheckpoint,
		base_tokenizer: config.base_tokenizer,
		use_chat_template: config.use_chat_template ?? false,
	});

	sidecarReady = true;
}

// ---------------------------------------------------------------------------
// Tool parameters
// ---------------------------------------------------------------------------

const TOOL_PARAMS = Type.Object({
	prompt: Type.String({ description: "Single incomplete (defn ...) form, OR a natural language description of the function. Example: '(defn has-close-elements [lst]' or 'Write a function that checks if a list has close elements'" }),
	function_name: Type.Optional(
		Type.String({ description: "Function name to use when prompt is natural language. Ignored when prompt starts with (defn. Default: 'solution'." }),
	),
	context: Type.Optional(
		Type.String({ description: "Previously defined Clojure code (helpers, types, etc.) that the generated function can reference. Prepended to the prompt as comments." }),
	),
	previous_code: Type.Optional(
		Type.String({ description: "Previously generated code that failed verification. The model will attempt to fix it." }),
	),
	error: Type.Optional(
		Type.String({ description: "Error message from failed verification (test failure, runtime error, etc.). Used with previous_code for fix mode." }),
	),
	checkpoint: Type.Optional(
		Type.String({ description: "Checkpoint alias (rlvr-30b, rlvr-8b, sft-8b) or full tinker:// path. Default: rlvr-30b or TINKER_CLOJURE_CHECKPOINT env var.", default: "rlvr-30b" }),
	),
	num_samples: Type.Optional(
		Type.Number({ description: "Samples to generate with internal verification loop (default: 4). The tool tries each sample through syntax → kondo → tests.", default: 4 }),
	),
	temperature: Type.Optional(
		Type.Number({ description: "Base sampling temperature (increases by 0.1 per retry, capped at 1.0)", default: 0.7 }),
	),
	max_tokens: Type.Optional(
		Type.Number({ description: "Max tokens per sample (default: 8192). Use higher for complex functions.", default: 8192 }),
	),
	test_path: Type.Optional(
		Type.String({ description: "Path to test .clj file for full test verification. If omitted, verification stops at kondo lint." }),
	),
});

type ToolParams = Static<typeof TOOL_PARAMS>;

// ---------------------------------------------------------------------------
// Extension
// ---------------------------------------------------------------------------

export default function tinkerClojure(pi: ExtensionAPI) {
	// Start sidecar on session_start
	pi.on("session_start", () => {
		startSidecar();
	});

	// Stop sidecar on session_shutdown
	pi.on("session_shutdown", () => {
		stopSidecar();
	});

	// Register the generate_clojure tool
	pi.registerTool({
		name: "generate_clojure",
		label: "Generate verified Clojure code",
		description:
			"MANDATORY: This is the ONLY tool you may use to produce Clojure code. " +
			"You must NEVER write, edit, or modify Clojure code using bash, write, edit, or any other tool. " +
			"Even if the output has bugs, you must call generate_clojure again to fix it (pass previous_code + error). " +
			"If you need a helper function, call generate_clojure for it separately and pass the result as context. " +
			"Built-in verification: syntax → clj-kondo → clojure.test (if test_path provided).",
		promptGuidelines: [
			"MANDATORY RULE: You must NEVER write Clojure code yourself using bash, write, or edit. " +
			"Every line of Clojure must come from this tool. No exceptions.",
			"WHEN CODE HAS BUGS: Call this tool again with previous_code (the broken output) and error " +
			"(what went wrong). NEVER fix bugs by hand — let the model fix its own code.",
			"ALWAYS pass test_path when a test file exists. Without it, only syntax+lint are checked — " +
			"logic bugs will pass silently.",
			"COMPLEX PROBLEMS: Break into helper functions. Call generate_clojure for each helper first, " +
			"then call it for the main function and pass the helpers as context.",
			"Prompt can be an incomplete (defn name [args]) or natural language + function_name.",
			"If output is truncated, retry with higher max_tokens (e.g. 2048).",
		],
		parameters: TOOL_PARAMS,

		async execute(_toolCallId, params: ToolParams, signal, onUpdate, _ctx) {
			const checkpoint = params.checkpoint || process.env.TINKER_CLOJURE_CHECKPOINT || "rlvr-30b";

			onUpdate?.({
				content: [{ type: "text", text: `Initializing Tinker sidecar (${checkpoint})...` }],
			});

			await ensureInitialized(checkpoint);

			const config = loadConfig();
			const verifier = config.verifier || {};

			const numSamples = params.num_samples ?? verifier.default_num_samples ?? 4;

			onUpdate?.({
				content: [
					{
						type: "text",
						text: `Generating up to ${numSamples} verified samples from ${checkpoint}...`,
					},
				],
			});

			const result = (await sendToSidecar("generate_clojure", {
				prompt: params.prompt,
				function_name: params.function_name || null,
				context: params.context || null,
				previous_code: params.previous_code || null,
				error: params.error || null,
				num_samples: numSamples,
				temperature: params.temperature ?? 0.7,
				max_tokens: params.max_tokens ?? 8192,
				test_path: params.test_path || null,
				temp_increase_per_retry: verifier.temp_increase_per_retry ?? 0.1,
				max_temp: verifier.max_temp ?? 1.0,
				test_timeout_sec: verifier.test_timeout_sec ?? 10,
				kondo_feedback_max_chars: verifier.kondo_feedback_max_chars ?? 200,
			})) as Record<string, unknown>;

			// Handle abort
			if (signal?.aborted) {
				throw new Error("Generation was aborted");
			}

			// Format response
			const code = (result.code as string) || ";; Generation failed";
			const verified = result.verified as boolean;
			const attempts = result.attempts as number;
			const maxAttempts = result.max_attempts as number;
			const checks = result.checks as Record<string, boolean | null>;
			const rawLength = result.raw_length as number | null;
			const extractedLength = result.extracted_length as number | null;
			const wasTruncated = result.was_truncated as boolean;
			const verificationOutput = (result.verification_output as string) || "";

			// Build summary of which checks passed
			const passedChecks = Object.entries(checks)
				.filter(([, v]) => v === true)
				.map(([k]) => k);

			// Build verification proof from raw test output
			let proof = "";
			if (verificationOutput) {
				const summaryMatch = verificationOutput.match(/Ran \d+ tests? containing \d+ assertions?.*/);
				if (summaryMatch) {
					proof = "\n" + summaryMatch[0];
				}
				const relevantLines = verificationOutput.split("\n")
					.filter((line: string) => line.trim() && !line.includes("WARNING"))
					.slice(-5);
				if (!proof && relevantLines.length > 0) {
					proof = "\n" + relevantLines.join("\n");
				}
			}

			let summary: string;
			const testResult = checks["tests"];
			const testsProvided = params.test_path && params.test_path.trim() !== "";

			if (verified) {
				summary = `Verified code (attempt ${attempts}/${maxAttempts}, passed: ${passedChecks.join(", ")})`;
				if (proof) {
					summary += proof;
				}
			} else if (testsProvided && testResult === false) {
				summary = `VERIFICATION FAILED (attempt ${attempts}/${maxAttempts}, passed: ${passedChecks.join(", ") || "none"}). ` +
					`You MUST call generate_clojure again with previous_code set to the code below and error set to the test failure reason. ` +
					`Do NOT fix the code yourself.`;
				if (proof) {
					summary += proof;
				}
			} else if (!testsProvided && testResult === null) {
				summary = `Partial verification (attempt ${attempts}/${maxAttempts}, passed: ${passedChecks.join(", ")}). ` +
					`Note: only syntax+lint were checked (no test examples found). ` +
					`If the code has logic bugs, call again with previous_code + error.`;
			} else {
				summary = `Best candidate after ${attempts}/${maxAttempts} attempts (passed: ${passedChecks.join(", ") || "none"}). ` +
					`Call again with previous_code + error to let the model fix it.`;
				if (proof) {
					summary += proof;
				}
			}

			if (wasTruncated) {
				summary += ` [truncated: ${extractedLength}/${rawLength} chars — retry with higher max_tokens]`;
			}

			return {
				content: [
					{ type: "text", text: summary },
					{ type: "text", text: code },
				],
				details: {
					checkpoint,
					verified,
					attempts,
					max_attempts: maxAttempts,
					checks,
					kondo_findings: result.kondo_findings || "",
					raw_length: rawLength,
					extracted_length: extractedLength,
					was_truncated: wasTruncated,
				},
			};
		},
	});
}
