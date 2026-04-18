# AGENTS.md — clojure-llm

## What this project is

We are proving that a small (8B) domain-specialized model, trained with verifier-in-the-loop RLVR, can outperform frontier models (Opus 4.7, GPT-5.4) on Clojure code generation.

The thesis: iteration speed in the generate-evaluate-revise loop matters more than raw model size. Clojure's REPL, `clj-kondo`, and `clojure.test` give us fast, granular verification that an agent can exploit. The precedent is QED-Nano — a 4B model beating 120B on proofs via verifier-in-the-loop RL.

## Current status

**Gate 1 (baselines) is in progress.** Three of four baseline conditions are complete:

| Condition | Model | pass@1 | Status |
|-----------|-------|--------|--------|
| A | Opus 4.7 | 48.0% | Done |
| B | GPT-5.4 | 65.4% | Done |
| B | GPT-5.4-mini | 60.0% | Done |
| C | Qwen3.5-plus | 54.7%* | Partial (75/558) |
| D | Qwen3-8B + SFT + RLVR | — | Phase 2-3 |

**Success criterion: Condition D pass@1 > Condition A pass@1 (48.0%).**

## Project layout

```
clojure-llm/
├── AGENTS.md              ← you are here
├── THESIS.md              ← core argument: iteration speed > model size
├── PLAN.md                ← original staged autoresearch plan
├── BENCHMARK.md           ← benchmark contract (task/run/result schemas)
├── PI-AGENT-INTEGRATION.md ← Pi agent extension specs (clj-verifier tools)
│
├── benchmark/
│   ├── tasks-v0.edn       ← 558 MultiPL-E task definitions
│   ├── prompts/multipl-e/ ← 558 .clj prompt files (task inputs)
│   ├── tests/             ← 558 .clj test files (clojure.test)
│   ├── candidates/        ← generated code per run (candidates/{run-id}/*.clj)
│   ├── results/           ← evaluation results per run (results/{run-id}/*.edn)
│   └── runs/              ← run manifests (*.edn)
│
├── scripts/
│   └── run_baseline.py    ← baseline runner (OpenAI + Anthropic APIs)
│
├── src/learn/
│   ├── benchmark.clj      ← CLI: plan-run, evaluate, check, summarize
│   ├── evaluate.clj       ← evaluation runner (syntax → kondo → tests → result)
│   └── aggregate.clj      ← result aggregation/summarization
│
├── training/
│   ├── sft/               ← SFT training scripts (Tinker platform)
│   └── rlvr/              ← RLVR training scripts (Tinker + Pi rollout)
│
├── research/
│   ├── baseline-analysis.md  ← baseline results and analysis
│   ├── literature-review.md  ← 30+ papers on RLVR, QED-Nano, code RL
│   ├── tinker-platform.md    ← Tinker training platform docs
│   └── clojure-ecosystem.md  ← MultiPL-E, 4clojure, clj-kondo details
│
├── data/
│   ├── multipl-e/         ← raw MultiPL-E dataset
│   └── 4clojure/          ← raw 4clojure problems
│
├── demo/
│   └── run-demo.sh        ← side-by-side demo script
│
└── deps.edn               ← Clojure deps (tools-deps)
```

## Key workflows

### Running baselines

```bash
# Dry run to check config
python3 scripts/run_baseline.py --condition B --dry-run

# Run with OpenAI-compatible API
python3 scripts/run_baseline.py --condition B --run-id <run-id>

# Run with Anthropic API (Opus)
python3 scripts/run_baseline.py --provider anthropic --condition A --run-id <run-id>

# Run with Qwen (via mulerouter)
python3 scripts/run_baseline.py --condition C --run-id <run-id>
```

Conditions map to env vars in `.env`: B→OPENAI_*, C→QWEN_*. Provider `anthropic` uses ANTHROPIC_*.

### Evaluation pipeline

```bash
# Create run manifest
clojure -M:bench plan-run <run-id> <model-id> direct

# Evaluate (syntax → clj-kondo → clojure.test → result EDN)
clojure -M:bench evaluate benchmark/runs/<run-id>.edn

# Results go to benchmark/results/<run-id>/<task-id>.edn
```

Each result EDN contains: `{:task-id, :outcome, :syntax-ok, :kondo-ok, :tests-ok, :wall-ms, :error-kind, :notes}`

Outcomes: `:pass`, `:fail`, `:timeout`, `:crash`, `:invalid-output`

### Analyzing results

```bash
# Count passes
grep -cl ":outcome :pass" benchmark/results/<run-id>/*.edn | wc -l

# Outcome distribution
for o in pass fail timeout crash invalid-output; do
  echo "$o: $(grep -cl ":outcome :$o" benchmark/results/<run-id>/*.edn | wc -l)"
done
```

## Architecture

### Evaluation pipeline

```
Prompt (.clj) → Model API → Candidate (.clj) → Evaluator → Result (.edn)
                                              ↓
                                          syntax check (edn/read)
                                              ↓
                                          clj-kondo lint
                                              ↓
                                          clojure.test (with timeout)
                                              ↓
                                          outcome classification
```

### Planned training pipeline (Phase 2-3)

```
Tinker (model weights)
    ↕ sample() / forward_backward()
Pi in RPC mode (agent runtime)
    ↕ tool calls
clj-verifier (REPL, kondo, tests)
    ↕
Docker sandbox (isolated execution)
```

## Phases

1. **Infrastructure + Baselines** (current) — eval pipeline, Pi extension, 3 baselines
2. **Data Curation** — extract verified Clojure solution pairs (3,500+)
3. **SFT** — LoRA fine-tune Qwen3-8B-Base on Tinker
4. **RLVR** — code RL with verifier-in-the-loop, Pi as rollout environment
5. **Demo** — side-by-side comparison: 8B + agent loop vs Opus

## Key documents

| Document | What it covers |
|----------|---------------|
| `THESIS.md` | Core argument and framing |
| `PLAN.md` | Full staged research plan |
| `BENCHMARK.md` | Task/run/result schemas, evaluation contract |
| `PI-AGENT-INTEGRATION.md` | Pi extension specs, RPC, clj-verifier tools |
| `research/baseline-analysis.md` | Baseline numbers and analysis |
| `research/literature-review.md` | 30+ papers (RLVR, QED-Nano, code RL) |
| `research/tinker-platform.md` | Tinker training platform capabilities |
| `research/clojure-ecosystem.md` | MultiPL-E, 4clojure, tooling details |

## Conventions

- Clojure source in `src/learn/`, run via `clojure -M:bench <command>`
- Python scripts in `scripts/`, used for API-based generation
- EDN for all config/data (tasks, manifests, results)
- Candidates and results are per-run, keyed by run-id
- Evaluation is resumable (skips tasks with existing result files)
- Test timeout protection via Clojure futures with `deref` timeout
- All API credentials in `.env` (never committed)
