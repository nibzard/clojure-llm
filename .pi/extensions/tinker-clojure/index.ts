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

function startSidecar(): void {
	if (sidecar && !sidecar.killed) {
		return;
	}

	const scriptPath = join(EXT_DIR, "tinker_bridge.py");
	sidecar = spawn("python3", ["-u", scriptPath], {
		stdio: ["pipe", "pipe", "pipe"],
		env: { ...process.env },
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
	prompt: Type.String({ description: "Single incomplete (defn ...) form. The model extends it to a complete function. Example: '(defn has-close-elements [lst]'" }),
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
		Type.Number({ description: "Max tokens per sample", default: 512 }),
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
			"Generate Clojure code using a Tinker-hosted specialized model (Qwen3-8B/30B trained with SFT+RLVR). " +
			"Provide a single incomplete (defn ...) form — the model completes it. " +
			"The tool runs an internal verification loop: each sample is checked through syntax validation, " +
			"clj-kondo linting, and (if test_path provided) clojure.test execution. " +
			"Returns the first fully-verified sample, or the best partial candidate after all attempts. " +
			"No external verification tools are needed.",
		promptGuidelines: [
			"Pass exactly ONE incomplete `(defn ...)` form — no namespace, no imports, no helper functions.",
			"The model is single-defn trained: it extends one incomplete defn into a complete one.",
			"Format: `(defn function-name [args]` — the model fills in the body and closes the parens.",
			"The tool internally verifies each sample through syntax → kondo → tests. You do NOT need separate verification tools.",
			"If a test file exists for the task, pass its path via test_path for full test verification.",
			"Default num_samples=4 means the tool generates up to 4 candidates, picking the first one that passes all checks.",
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
				num_samples: numSamples,
				temperature: params.temperature ?? 0.7,
				max_tokens: params.max_tokens ?? 512,
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

			// Build summary of which checks passed
			const passedChecks = Object.entries(checks)
				.filter(([, v]) => v === true)
				.map(([k]) => k);
			let summary = verified
				? `Verified code (attempt ${attempts}/${maxAttempts}, passed: ${passedChecks.join(", ")})`
				: `Best candidate after ${attempts}/${maxAttempts} attempts (passed: ${passedChecks.join(", ") || "none"})`;

			if (wasTruncated) {
				summary += ` [truncated: ${extractedLength}/${rawLength} chars]`;
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
