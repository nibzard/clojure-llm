# clojure-llm

Can an 8B model beat frontier models at Clojure code generation?

We train Qwen3-8B with SFT + verifier-in-the-loop RLVR and compare against
Opus 4.7 (48% pass@1) and GPT-5.4 (65.4%). The thesis: fast feedback loops
(REPL, clj-kondo, clojure.test) matter more than model scale.
Precedent: QED-Nano (4B beats 120B on proofs). → `THESIS.md`

## Status

| # | Condition | pass@1 (111 held-out) | Status |
|---|-----------|----------------------|--------|
| B | GPT-5.4 | 64.0% | Done |
| B | GPT-5.4-mini | 59.5% | Done |
| **E** | **RLVR Qwen3-30B** | **55.0%** | **Phase 4b** |
| **E** | **SFT Qwen3-30B** | **52.3%** | **Phase 4a** |
| C | Qwen3.5-plus | ~55% | Partial |
| A | Opus 4.7 | 45.0% | Done |
| **D** | **RLVR Qwen3-8B** | **41.4%** | **Done** |
| D | SFT Qwen3-8B | 37.8% | Done |

**Phase 4b: RLVR 30B pass@1 = 55.0% (+2.7pp over SFT 30B).** But best-of-16 ceiling *dropped* from 83.8% to 79.3% — RLVR narrowed the solution distribution, losing 5 tasks the SFT model could solve. RLVR 30B best-of-8 = 75.7% (same as SFT 30B).

**Phase 4a: SFT 30B pass@1 = 52.3% (beats Opus 4.7 by +7.3pp).**
Best-of-16 ceiling: **83.8% (93/111)** — up from 72.1% (8B). Best-of-2 (64.9%) matches GPT-5.4 single-pass.
Same 2,459 SFT pairs, MoE model (30B total / 3B active). Only 18 unsolvable tasks (vs 31 for 8B).

**8B Best-of-K: RLVR best-of-8 (67.6%) beats GPT-5.4 pass@1 (64.0%).** With a verifier loop, the 8B model surpasses the frontier.
Analysis → `research/baseline-analysis.md`

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

RLVR → GRPO with binary verifier rewards (Clojure subprocess eval):
- Binary reward: 1.0 tests-pass, 0.0 fail
- GRPO advantages normalized per task group (K=8 rollouts)
- REINFORCE with cross_entropy + advantage-scaled weights (importance_sampling unavailable)
- Tinker SDK: `forward_backward` + `optim_step` on cloud training infrastructure
- Checkpoint: `training/rlvr/config.yaml`

Best-of-K → `scripts/best_of_k.py` — generate K samples per task, pick first passing one:
- **30B SFT**: best-of-16 ceiling 83.8% (93/111), pass@1 52.3%, best-of-8 75.7%
- **30B RLVR**: best-of-16 ceiling 79.3% (88/111), pass@1 55.0%, best-of-8 75.7%
- **8B RLVR**: best-of-16 ceiling 72.1% (80/111), pass@1 41.4%, best-of-8 67.6%
- **8B SFT**: best-of-16 ceiling 72.1% (80/111), pass@1 37.8%, best-of-8 64.9%
- 30B SFT raised ceiling by +11.7pp with same SFT data; RLVR lowered it by 4.5pp
- Results: `research/best-of-k-30b-results.json`, `research/best-of-k-rlvr-30b-results.json`, `research/best-of-k-results.json`, `research/best-of-k-sft-results.json`

## Key docs (progressive disclosure)

| Want to… | Read |
|----------|------|
| Understand the argument | `THESIS.md` |
| See the full research plan | `PLAN.md` |
| Know the benchmark contract | `BENCHMARK.md` |
| Understand evaluation internals | `src/learn/evaluate.clj` |
| See baseline numbers + error analysis | `research/baseline-analysis.md` |
| See best-of-K results (8B beats GPT-5.4 with verifier) | `research/best-of-k-results.md` |
| See RLVR training analysis | `research/rlvr-results.md` |
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
- Experiment tracking via wandb (entity: `nibzard-org`, project: `clojure-llm`, key in `.env`)
