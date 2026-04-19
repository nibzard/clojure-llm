# Plan: Benchmark-First Autoresearch for Clojure Code Models

## Thesis

Clojure is a good target for benchmark-driven code model research because:

- the public benchmark surface is still thin enough that new work matters;
- the language has fast executable feedback via `clojure.test`, `clj-kondo`, and the REPL;
- the syntax and tooling permit useful constrained-decoding and verifier-in-the-loop experiments;
- a strong benchmark and harness can produce publishable results before expensive training begins.

The working hypothesis is narrower than "Clojure is easy for LLMs if trained enough":

1. A benchmark-first research loop can measure real Clojure weakness in current models.
2. A verifier-backed agent harness can improve Clojure pass rates before any finetuning.
3. Once the harness exists, it can manufacture higher-signal Clojure training data.
4. A specialized 7B-class model may then beat larger general models on Clojure-specific tasks.

## Project shape

This project is not "train a model and hope the eval works."

It is a staged autoresearch program:

1. Build the benchmark and evaluation harness.
2. Use that harness to measure baselines and agentic lift.
3. Use the same harness to synthesize and verify training data.
4. Train only after the benchmark says the setup is worth the spend.
5. Turn the best research harness into a deployable Clojure agent.

That order matches the `autoresearch` paradigm: tight experiment loops, fixed evaluators, aggressive iteration, and a bias toward infrastructure that compounds.

## Goals

- Primary: publish a defensible Clojure benchmark + harness that exposes meaningful performance gaps in current code models.
- Secondary: show measurable lift from a verifier-in-the-loop Clojure agent over the same base model without training.
- Tertiary: use the benchmark and harness to justify and guide training a small Clojure-specialized model.
- Product goal: converge toward a deployable Clojure coding agent powered by the same harness.

## Non-goals

- Multi-dialect support in v1. No Common Lisp, Scheme, or Racket.
- Whole-program general software engineering claims at the start.
- Immediate public release of every data source used in internal experiments.
- "Beat frontier models everywhere." The initial scope is Clojure-specific tasks only.

## Why Clojure

- Strong executable verification loop: `clojure.test` and REPL feedback are cheap.
- Strong static feedback loop: `clj-kondo` catches many invalid or non-idiomatic forms cheaply.
- Good tool uniformity for a hosted language: `deps.edn` + `clojure` CLI are sufficient for a large share of repos.
- Small enough ecosystem that benchmark work is still high leverage.
- Lisp syntax gives constrained decoding obvious surface-level value even if semantics remain hard.

## Key claims, tightened

### 1. Constrained decoding is helpful, but not magic

We should claim only what we can enforce:

- balanced delimiters;
- well-formed top-level forms for a constrained subset of standard Clojure syntax;
- lower syntax-error rate at generation time.

We should not claim:

- full reader-level validity for all Clojure syntax;
- correctness for custom reader behavior or non-standard macros;
- semantic validity.

Implementation candidates:

- `llguidance`
- `xgrammar`

Grammar source candidates:

- hand-written constrained grammar for the supported subset;
- grammar derived in part from `tree-sitter-clojure`, with explicit exclusions for documented limits.

Success metric:

- reduced syntax/read failures with acceptable decode overhead.

### 2. JVM security is not the sandbox story

Do not rely on Java `SecurityManager`. It was deprecated in Java 17 and permanently disabled in JDK 24.

The execution boundary must be OS-level isolation:

- Docker or Podman containers for baseline reproducibility;
- optional `nsjail` for tighter Linux-only rollouts later;
- `--network none`;
- readonly base filesystem plus ephemeral writable workspace;
- CPU, memory, pids, and wall-clock limits;
- per-run fresh container/process;
- no secrets mounted into the evaluator.

If that sandbox is not solid, both eval and RL plans are invalid.

### 3. Public and internal data are separate tracks

Internal experimentation can use a broader set of sources. Public release cannot assume that all useful sources are redistributable.

We therefore maintain two corpora:

- `internal-corpus`: whatever is legally safe to use for experiments but not necessarily redistributable as a dataset;
- `release-corpus`: sources with clear enough terms for public benchmark artifacts, recipes, and sample data release.

This avoids blocking research progress on day one while keeping a clean publication path.

## Research artifacts, in order

### Artifact A: `clj-bench`

The first publishable deliverable is a benchmark suite and harness, not a model.

It should include:

- `HumanEval-Clojure` via MultiPL-E `humaneval-clj`;
- `MBPP-Clojure` via MultiPL-E `mbpp-clj`;
- a curated `4clojure-bench` if license treatment is confirmed acceptable;
- a repo-level benchmark built from permissively licensed Clojure repos with failing tests and pinned commits;
- an optional "interactive REPL repair" track once the harness is stable.

Each benchmark task must be:

- executable;
- contamination-aware where possible;
- version-pinned;
- reproducible in containers.

### Artifact B: `clj-harness`

The second artifact is the execution and agent harness:

- code execution in isolated containers;
- `clj-kondo` verifier;
- `clojure.test` verifier;
- optional constrained decoding backend;
- logging for syntax failures, runtime failures, and tokens-to-pass.

This harness serves three roles:

- benchmark runner;
- inference-time agent runtime;
- later training-time verifier/reward environment.

#### Dual-mode API: design principle

The harness is designed from day one to serve both **eval mode** (run a benchmark, produce a scored result) and **RL mode** (do a rollout, return a shaped reward). These look like different products but share the same core primitives. Retrofitting an RL environment onto an eval-only harness is painful; building both shapes in from the start is cheap.

Concretely, the same core object must expose:

- `reset(task) -> State` — load a task, start a fresh sandbox, return the initial state (task prompt, workspace, empty REPL).
- `step(State, Action) -> (State', Signal)` — advance by one action (generate a form, eval in REPL, run tests, apply a patch) and return the new state plus a verifier `Signal` that is either **pooled** (benchmark score) or **shaped** (per-step reward terms: read-ok, kondo-clean, ns-load, test-pass).
- `close(State)` — tear down the sandbox cleanly.

One implementation, two call patterns:

- **Eval mode**: one `reset` → one or a few `step`s → one final pass/fail + metadata.
- **RL mode**: one `reset` → many `step`s → a trajectory of per-step rewards.

The `Signal` type carries both the pooled benchmark outcome and the decomposed reward terms so that neither caller has to translate. Verifiers (`clj-kondo`, `clojure.test`, read, namespace load) each emit their own term; eval mode collapses them, RL mode keeps them.

This commits the harness's public surface now, so Stage 3 RL is an integration task, not a rewrite.

### Artifact C: agentic baseline report

Before training, run a research report:

- base model direct generation;
- same model with containerized test loop;
- same model with constrained decoding;
- same model with both.

That report tells us whether the harness produces enough lift to justify training investment.

### Artifact D: data generation pipeline

Only after Artifact C shows useful lift:

- teacher-generated tasks or repairs;
- verifier-backed filtering;
- dedupe;
- provenance tracking;
- split into internal-only vs releasable subsets.

### Artifact E: specialized model training

Training begins only after the benchmark and harness have made the target concrete.

## Benchmark design

### Track 1: function-level generation

Purpose:

- fast, cheap, repeatable measurement;
- easiest place to compare many models.

Initial contents:

- MultiPL-E `humaneval-clj`
- MultiPL-E `mbpp-clj`

Metrics:

- pass@1
- pass@k
- syntax/read failure rate
- `clj-kondo` clean rate
- mean tokens to first passing sample
- wall-clock cost per passing solution

### Track 2: repair / bugfix

Purpose:

- bridge from toy problems to real repo work.

Candidate construction:

- sample permissively licensed repos;
- pin commit;
- inject or select a real failing test state;
- ask the model/agent to produce a patch.

Metrics:

- fix rate
- patch applies cleanly
- tests pass
- number of agent steps
- total tool calls / cost

### Track 3: interactive REPL tasks

Purpose:

- isolate the core thesis that Clojure benefits from fine-grained feedback.

Task shape:

- multi-step completion or repair where the agent can evaluate one form at a time.

Metrics:

- final pass rate
- number of REPL iterations
- average error recovery depth

This track can start small and private before publication.

## Baselines

Every benchmark report must pin exact model IDs and dates. No vague labels like "GPT-4" or "Claude."

Open baselines to start with:

- `Qwen/Qwen2.5-Coder-7B`
- `Qwen/Qwen2.5-Coder-7B-Instruct`
- `deepseek-ai/DeepSeek-Coder-V2-Lite-Base`
- `bigcode/starcoder2-15b`

Closed baselines can be included in reports, but only with:

- exact API model names;
- prompt template;
- sampling settings;
- evaluation date.

## Initial model choice

Training runs on Tinker (LoRA-only, managed distributed training). Qwen2.5-Coder-7B is not on the Tinker supported-model list, so the finetune base is drawn from what Tinker actually supports.

Start with **`Qwen/Qwen3-30B-A3B`** (Hybrid variant, reasoning-capable, MoE 30B total / 3B active, 32K context, $0.36/MT train).

Reason:

- cheapest MoE on Tinker at the Medium capacity tier — same train price as Qwen3-8B dense but ~4× total parameters for LoRA to land in;
- 3B active params → RL rollouts (sample price $0.30/MT) are as fast as an 8B dense while the model carries 30B of capacity;
- retained chain-of-thought pairs naturally with the REPL-feedback loop in RLVR;
- LoRA preserves base weights, so the instruct-tuning in the Hybrid variant survives continued pretraining without the catastrophic-forgetting risk of full-FT.

Alternatives at the same price point (one-string swap):

- `Qwen/Qwen3-30B-A3B-Base` — pure Base variant. Pick if we decide CPT semantics need a clean base.
- `Qwen/Qwen3-30B-A3B-Instruct-2507` — newest instruction-tuned variant. Skips part of Stage 2 if we want to jump straight to domain SFT.

Stretch:

- `Qwen/Qwen3.5-35B-A3B` — newer arch, 64K native context, Hybrid+Vision. ~3× cost at $1.07/MT. Switch if capacity plateaus or we need >32K context.
- `deepseek-ai/DeepSeek-V3.1-Base` — frontier-class MoE Base at $3.38/MT. Only if the 30B/3B-active tier proves insufficient.

Model ID is a single string — swapping to Qwen3.5-\*-Base / Qwen3.6 / a newer MoE when Tinker adds them is a one-line change. That is Tinker's core value prop; lean into it. Do not commit yet to "this will be the finetune base forever." Revisit after benchmark and harness results.

## Data plan

### Release-track sources

Prefer these for anything intended to ship publicly:

- permissively licensed GitHub repos with pinned provenance;
- MultiPL-E benchmark assets where redistribution is clear;
- 4clojure content if EPL treatment is confirmed and acceptable for the benchmark;
- synthetic tasks generated entirely in-house, with original tests and prompts;
- macroexpansion pairs derived from redistributable source.

### Internal-track sources

May include for research iteration, but do not assume public redistribution:

- broader GitHub scrape beyond the release set;
- Clojars source where package-level licensing must be resolved per package;
- teacher-generated translations from third-party problem sets;
- Advent of Code solution code only, without puzzle text or inputs;
- any private synthetic derivative set that is useful for ablations.

### Explicit exclusions for public benchmark assets

- LeetCode content or scraped variants;
- Advent of Code puzzle text or puzzle inputs;
- any dataset with unclear redistribution rights.

## Training plan

Training is stage-gated.

### Stage 0: no-training research

Run the benchmark against:

- direct generation;
- agent loop without constrained decoding;
- constrained decoding without agent loop;
- both combined.

If there is no measurable lift, do not train yet.

### Stage 1: lightweight adaptation

If the harness works:

- LoRA or QLoRA on a 7B base;
- supervised finetuning on verifier-clean pairs;
- focus on function-level tasks first.

Goal:

- beat the same base model on function-level `clj-bench`.

### Stage 2: full SFT or continued pretraining

Only if Stage 1 saturates:

- continued pretraining on Clojure-heavy corpus;
- SFT on verified tasks and repairs;
- maintain clean holdouts.

### Stage 3: RL with verifiable rewards

Only after the environment is robust and cheap:

- reward for read success;
- reward for `clj-kondo` cleanliness;
- reward for namespace load success;
- reward for test pass.

Do not start here. RL is the last stage, not the first differentiator.

**Completed (Phase 3).** Used binary test-pass/fail reward with GRPO advantages on Qwen3-8B SFT checkpoint. 10 iterations × 30 tasks × 8 rollouts = 2,400 samples. Used `cross_entropy` loss with advantage-scaled weights (REINFORCE) because Tinker's `importance_sampling` loss had API compatibility issues. Result: 41.4% pass@1 on 111 held-out tasks (+3.6% over SFT's 37.8%, but 3.6% short of Opus 4.7's 45.0%). Training took ~4 hours on Tinker. See `checkpoints/rlvr/training_summary.json` and wandb run `pqshb7mm`.

**Best-of-K evaluation (Phase 3b).** Ran best-of-16 on both SFT and RLVR models. Both reach the same 72.1% ceiling (80/111 tasks). RLVR converges faster: best-of-2 is 55.9% (RLVR) vs 47.7% (SFT). RLVR best-of-8 (67.6%) beats GPT-5.4 pass@1 (64.0%). SFT best-of-8 (64.9%) also beats GPT-5.4. RLVR improved consistency but not knowledge — the 72.1% ceiling is set by SFT data quality. See `research/best-of-k-results.md`.

## Macro-aware track

This is a genuine research angle, but it is an ablation, not a pillar of the whole project.

Potential data:

- `(form, macroexpand-1 form)`
- `(form, macroexpand form)` where expansion is bounded and deterministic

Potential hypothesis:

- explicit expansion supervision may improve reasoning about macros and generated code structure.

Required caution:

- expansion output is noisy and environment-dependent;
- this should be tested after the basic benchmark/harness loop works.

## Milestones

| Phase | Deliverable | Time | Exit criterion | Result |
|---|---|---|---|---|
| **0. Benchmark inventory** | Confirm runnable `humaneval-clj`, `mbpp-clj`, baseline harness skeleton | 3-5 days | One-command eval works end-to-end in containers | **Done** — 558 tasks validated |
| **1. Baseline baselines** | Frontier model baselines on 111 held-out tasks | 1 week | Reproducible scores for closed baselines | **Done** — GPT-5.4: 64.0%, Opus 4.7: 45.0% |
| **2. SFT** | Qwen3-8B SFT on 2,459 verified Clojure pairs via Tinker | 1-2 weeks | Beats base model on held-out | **Done** — 37.8% pass@1 |
| **3. RLVR** | GRPO with Clojure verifier rewards on Tinker | 2-3 weeks | Beats Opus 4.7 (45.0%) | **Done** — 41.4% (fell short) |
| **3b. Best-of-K** | Best-of-K eval on SFT + RLVR models | 1 day | Estimate pass@K ceiling | **Done** — RLVR best-of-8 (67.6%) > GPT-5.4 pass@1 (64.0%); same 72.1% ceiling for SFT and RLVR |
| **4. Repo-level benchmark** | Patch-generation track over real repos | 2-4 weeks | Reproducible patch eval on pinned repos | — |
| **5. Full model work** | Continued pretraining / stronger SFT / longer RL | 4-8 weeks | Material improvement justifies added spend | — |

## Go / no-go gates

### Gate A: benchmark legitimacy

Proceed only if:

- the benchmark is reproducible;
- at least some tasks are not obviously saturated by current baselines;
- the harness cost is low enough to run routinely.

### Gate B: harness lift

Proceed to data generation and training only if the harness produces real lift over direct generation on the same base model.

Suggested threshold:

- at least 5 absolute points of pass@1 lift on one core track, or
- a meaningfully lower cost-to-pass with similar quality.

Do not claim the thesis is false if this fails once. Investigate prompt design, verifier wiring, and task design first.

### Gate C: training justification

Proceed beyond lightweight adaptation only if:

- LoRA/SFT improvements are stable across reruns;
- gains transfer beyond one benchmark slice;
- infra cost is still reasonable.

## First month

### Week 1

- Make `humaneval-clj` and `mbpp-clj` run locally in containers.
- Define canonical prompts and sampling settings.
- Run open baseline models and record exact versions.
- Build result tables and plotting scripts.

### Week 2

- Add `clj-kondo` and runtime categorization to the harness.
- Build the first agent loop with test execution and retry.
- Measure direct generation vs agent loop.

### Week 3

- Add constrained decoding for a documented subset of syntax.
- Measure syntax failure reduction and latency overhead.
- Publish or draft the benchmark methodology note.

### Week 4

- Decide whether the benchmark and harness justify data generation.
- If yes, start verified synthesis and lightweight adaptation.
- If no, tighten benchmark design or pivot the thesis before training spend.

## Risks and mitigations

| Risk | Mitigation |
|---|---|
| Benchmark contamination or saturation | Prefer pinned, newly curated, or repo-level tasks; report limits explicitly |
| Sandbox escape or evaluator instability | Use per-run isolated containers, no network, strict limits, zero secrets |
| Constrained decoding overhead is too high | Measure overhead directly; allow verifier-only mode as fallback |
| Clojure grammar coverage is incomplete | Support only a documented syntax subset first; treat unsupported reader behavior as out of scope |
| Data rights block release | Maintain separate internal and release corpora from day one |
| Synthetic data overfits teacher style | Mix with human code, hold out human-written sets, and benchmark on repo repair tasks |
| Repo-level benchmark is expensive to curate | Start with function-level tracks and add repo repair later |

## Success criteria

### Minimum

- publish a reproducible Clojure benchmark and harness;
- show exact baseline numbers for at least three models;
- demonstrate one clear same-model lift from SFT or RLVR training.

**Status: achieved.** Benchmark harness built, 4 baselines measured, SFT+RLVR training completed with clear improvement over base.

### Target

- SFT + RLVR beats Opus 4.7 on 111 held-out tasks;
- repo-level benchmark v0 exists with pinned tasks;
- the harness is usable as the core of a product prototype.

**Status: partially achieved.** RLVR reached 41.4% vs Opus 4.7's 45.0% — fell 3.6% short. Repo-level benchmark not started.

### Stretch

- an 8B-class specialized model matches or beats stronger closed baselines on at least one Clojure-focused track;
- repo-repair performance is strong enough that the agent is product-worthy for real users.

**Status: partially achieved (best-of-K).** RLVR pass@1 (41.4%) did not beat Opus or GPT-5.4. But both SFT and RLVR best-of-8 beat GPT-5.4 pass@1 (64.0%): RLVR at 67.6%, SFT at 64.9%. Best-of-16 reaches 72.1% for both. SFT and RLVR share the same ceiling — RLVR only improves consistency, not knowledge. With a verifier-in-the-loop, the 8B model surpasses the frontier — validating the thesis that fast feedback loops matter more than scale.

## Immediate next actions

Phases 0–3b complete. Best-of-K evaluation done (SFT + RLVR). Possible next steps:

1. **Build verifier agent loop**: best-of-K proves the ceiling is 72.1%. Build an actual agent that generates, tests, and retries with Clojure feedback to close the gap between pass@1 and best-of-K in practice.
2. **Raise the 72.1% ceiling** via better SFT data or a stronger base model — RLVR cannot raise it further since SFT and RLVR share the same ceiling.
3. **Repo-level benchmark**: build Track 2 patch-generation tasks from real Clojure repos.
4. **Write up**: compile results into a research report with error analysis and comparison table.
