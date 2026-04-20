/**
 * Tinker Clojure Code Generation Extension
 *
 * Routes Clojure code generation to Tinker-hosted specialized models
 * (Qwen3-8B SFT+RLVR) via a persistent Python sidecar. The main model
 * (any provider) handles everything else — this extension only provides
 * the generate_clojure tool for when the main model decides to delegate.
 *
 * Usage:
 *   Place in .pi/extensions/tinker-clojure/ — auto-discovered by pi.
 *   User: "Write a Clojure function that checks if a list has close elements"
 *   → Main model calls generate_clojure → sidecar → Tinker → result
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
	});

	sidecarReady = true;
}

// ---------------------------------------------------------------------------
// Tool parameters
// ---------------------------------------------------------------------------

const TOOL_PARAMS = Type.Object({
	prompt: Type.String({ description: "Clojure function signature or description (e.g., '(defn has-close-elements [lst] ...)')" }),
	checkpoint: Type.Optional(
		Type.String({ description: "Checkpoint alias (rlvr-8b, sft-8b) or full tinker:// path. Default: rlvr-8b or TINKER_CLOJURE_CHECKPOINT env var.", default: "rlvr-8b" }),
	),
	num_samples: Type.Optional(
		Type.Number({ description: "Samples to generate (1 for speed, 4-8 for quality with verification)", default: 1 }),
	),
	temperature: Type.Optional(
		Type.Number({ description: "Sampling temperature (0.2 for deterministic, 0.7 for diverse)", default: 0.7 }),
	),
	max_tokens: Type.Optional(
		Type.Number({ description: "Max tokens per sample", default: 512 }),
	),
	test_path: Type.Optional(
		Type.String({ description: "Path to test .clj file for best-of-K verification. If provided, generates num_samples and returns first passing one." }),
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
		label: "Generate Clojure code",
		description:
			"Generate Clojure code using a Tinker-hosted specialized model (Qwen3-8B trained with SFT+RLVR). " +
			"The model is fine-tuned for Clojure code generation and produces better results than general-purpose models. " +
			"Supports best-of-K sampling with optional test verification.",
		promptGuidelines: [
			"When the user asks for Clojure code generation, use generate_clojure instead of writing Clojure yourself.",
			"Pass a Clojure function signature as the prompt (e.g., '(defn function-name [args] ...)').",
			"Use num_samples=4-8 for complex functions — the tool generates multiple candidates and verifies them.",
			"If a test file exists for the task, pass its path via test_path for verification.",
			"The model is specialized for Clojure and will produce better results than general-purpose models.",
		],
		parameters: TOOL_PARAMS,

		async execute(_toolCallId, params: ToolParams, signal, onUpdate, _ctx) {
			const checkpoint = params.checkpoint || process.env.TINKER_CLOJURE_CHECKPOINT || "rlvr-8b";

			onUpdate?.({
				content: [{ type: "text", text: `Initializing Tinker sidecar (${checkpoint})...` }],
			});

			await ensureInitialized(checkpoint);

			const baseParams: Record<string, unknown> = {
				prompt: params.prompt,
				num_samples: params.num_samples ?? 1,
				temperature: params.temperature ?? 0.7,
				max_tokens: params.max_tokens ?? 512,
			};

			let result: Record<string, unknown>;

			if (params.test_path) {
				onUpdate?.({
					content: [
						{
							type: "text",
							text: `Generating ${baseParams.num_samples} samples with test verification...`,
						},
					],
				});

				result = (await sendToSidecar("generate_with_verify", {
					...baseParams,
					test_path: params.test_path,
				})) as Record<string, unknown>;
			} else {
				onUpdate?.({
					content: [
						{
							type: "text",
							text: `Generating ${baseParams.num_samples} sample(s) from ${checkpoint}...`,
						},
					],
				});

				result = (await sendToSidecar("generate", baseParams)) as Record<string, unknown>;
			}

			// Handle abort
			if (signal?.aborted) {
				throw new Error("Generation was aborted");
			}

			// Format response
			if (params.test_path) {
				const bestSample = result.best_sample as string | null;
				const verified = result.verified as boolean;
				const bestK = result.best_k as number;
				const total = result.total as number;

				const summaryParts = [
					verified
						? `Verified sample (k=${bestK}/${total} passed tests)`
						: `No sample passed tests out of ${total} (returning best candidate)`,
				];

				return {
					content: [
						{ type: "text", text: summaryParts.join(" ") },
						{ type: "text", text: bestSample || ";; Generation failed" },
					],
					details: {
						checkpoint,
						verified,
						best_k: bestK,
						total,
					},
				};
			} else {
				const samples = result.samples as string[];
				const numGenerated = result.num_generated as number;

				const sampleText =
					numGenerated === 1
						? samples[0] || ";; Generation failed"
						: samples.map((s, i) => `;; --- Sample ${i + 1} ---\n${s}`).join("\n\n");

				return {
					content: [
						{ type: "text", text: `Generated ${numGenerated} sample(s) from ${checkpoint}` },
						{ type: "text", text: sampleText },
					],
					details: {
						checkpoint,
						num_generated: numGenerated,
					},
				};
			}
		},
	});
}
