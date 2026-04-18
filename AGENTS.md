# clojure-llm

Can an 8B model beat frontier models at Clojure code generation?

We train Qwen3-8B with SFT + verifier-in-the-loop RLVR and compare against
Opus 4.7 (48% pass@1) and GPT-5.4 (65.4%). The thesis: fast feedback loops
(REPL, clj-kondo, clojure.test) matter more than model scale.
Precedent: QED-Nano (4B beats 120B on proofs). → `THESIS.md`

## Status

| # | Condition | pass@1 | Status |
|---|-----------|--------|--------|
| A | Opus 4.7 | 48.0% | Done |
| B | GPT-5.4 / mini | 65.4 / 60.0% | Done |
| C | Qwen3.5-plus | ~55% | Partial |
| **D** | **Qwen3-8B + SFT + RLVR** | — | **Next** |

**Target: D > A (48%).** Analysis → `research/baseline-analysis.md`

## Layout (what matters)

```
benchmark/          Tasks, prompts, generated code, evaluation results
  prompts/multipl-e/  558 .clj prompt files (input)
  tests/              558 .clj test files (ground truth)
  candidates/{run}/   Generated code per run
  results/{run}/      Evaluated outcomes per run
src/learn/          Clojure evaluation tooling (CLI, eval, aggregation)
scripts/            Python runners (baseline generation, training)
training/sft/       SFT on Tinker platform
training/rlvr/      RLVR with GRPO rewards
research/           Literature review, platform docs, analysis
```

## How it works

```
Prompt → Model API → Candidate → Evaluator → Result (.edn)
                                    ↓
                              syntax (clojure.core/read)
                                    ↓
                              clj-kondo lint
                                    ↓
                              clojure.test (timeout-guarded)
                                    ↓
                              :pass | :fail | :timeout | :crash
```

- Eval is resumable (skips existing results), containerized, per-task
- Results are EDN maps: `{:task-id :outcome :syntax-ok :kondo-ok :tests-ok :wall-ms}`
- 558 tasks from MultiPL-E (humaneval-clj + mbpp-clj)

## Commands

```bash
# Generate candidates via API
python3 scripts/run_baseline.py --condition B --run-id <id>   # .env → OPENAI_*
python3 scripts/run_baseline.py --provider anthropic -c A ...  # .env → ANTHROPIC_*

# Evaluate
clojure -M:bench plan-run <run-id> <model> direct
clojure -M:bench evaluate benchmark/runs/<run-id>.edn

# Aggregate
clojure -M:bench aggregate <run-id> [<run-id2> ...]
```

## Training pipeline (Phases 2-3)

SFT → LoRA fine-tune on 2,459 verified Clojure pairs → `training/sft/`

RLVR → GRPO with shaped rewards:
- 0.1 syntax valid + 0.2 kondo-clean + 0.1 namespace-loads + 0.6 tests-pass
- Pi agent as rollout environment → `PI-AGENT-INTEGRATION.md`

Config: `training/rlvr/config.yaml`

## Key docs (progressive disclosure)

| Want to… | Read |
|----------|------|
| Understand the argument | `THESIS.md` |
| See the full research plan | `PLAN.md` |
| Know the benchmark contract | `BENCHMARK.md` |
| Understand evaluation internals | `src/learn/evaluate.clj` |
| See baseline numbers + error analysis | `research/baseline-analysis.md` |
| Review the RL/code-RL literature | `research/literature-review.md` |
| Learn the training platform | `research/tinker-platform.md` |
| Understand Clojure tooling fit | `research/clojure-ecosystem.md` |
| Build the Pi agent integration | `PI-AGENT-INTEGRATION.md` |

## Conventions

- Clojure source in `src/learn/`, invoked via `clojure -M:bench`
- Python in `scripts/` for API calls and training orchestration
- All config/data in EDN (tasks, manifests, results)
- API credentials in `.env` (never committed)
- Candidates/results keyed by run-id, never mutated after evaluation
