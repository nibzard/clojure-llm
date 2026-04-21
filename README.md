# clojure-llm

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

Can an 8B-parameter model beat frontier models at Clojure code generation?

This project trains Qwen3 models with SFT + verifier-in-the-loop RLVR and
compares against Opus 4.7, GPT-5.4, and Gemini 3.1 Pro on 558 Clojure tasks
from MultiPL-E. The thesis: fast feedback loops (REPL, clj-kondo,
clojure.test) matter more than model scale. A Qwen3-8B model with a
verifier loop beats GPT-5.4 single-pass.

## Results

### Pass@1 (111 held-out tasks)

| Model | pass@1 | full 558 |
|-------|--------|----------|
| GPT-5.4 | 64.0% | 65.4% |
| Gemini 3.1 Pro | -- | 64.5%* |
| GPT-5.4-mini | 59.5% | 60.0% |
| **Qwen3-30B RLVR** | **55.0%** | -- |
| **Qwen3-30B SFT** | **52.3%** | -- |
| Qwen3.5-plus | ~55% | partial |
| Opus 4.7 | 45.0% | 48.0% |
| Qwen3-8B RLVR v1 | 48.6% | -- |
| Qwen3-8B SFT | 37.8% | -- |

*65 tasks pending evaluation.

### Best-of-K (111 held-out, with verifier)

The key result: a verifier loop lets small models surpass frontier
single-pass performance.

| Model | pass@1 | best-of-2 | best-of-8 | best-of-16 |
|-------|--------|-----------|-----------|------------|
| Qwen3-30B SFT | 52.3% | 64.9% | 75.7% | 83.8% |
| Qwen3-30B RLVR | 55.0% | -- | 75.7% | 79.3% |
| Qwen3-8B RLVR v1 | 48.6% | -- | 61.3% | 64.0% |
| Qwen3-8B RLVR v0 | 42.3% | -- | 67.6% | 72.1% |
| Qwen3-8B SFT | 37.8% | -- | 64.9% | 72.1% |

Qwen3-8B RLVR v0 best-of-8 (67.6%) exceeds GPT-5.4 pass@1 (64.0%).
Qwen3-30B SFT best-of-2 (64.9%) matches GPT-5.4 single-pass.

## Quick start

Prerequisites: Clojure 1.12+, Python 3.10+, API credentials in `.env`.

```bash
# Install Python dependencies
pip install -r requirements.txt

# Generate candidates (e.g., GPT-5.4)
python3 scripts/run_baseline.py --condition B --run-id gpt54

# Set up an evaluation run
clojure -M:bench plan-run gpt54 gpt-5.4 direct

# Evaluate all candidates
clojure -M:bench evaluate benchmark/runs/gpt54.edn

# View aggregated results
clojure -M:bench aggregate gpt54
```

## How it works

```
Prompt --> Model API --> Candidate --> Evaluator --> Result (.edn)
                                    |
                              syntax (clojure.core/read)
                                    |
                              clj-kondo lint
                                    |
                              clojure.test (timeout-guarded)
                                    |
                              :pass | :fail | :timeout | :crash
```

Each candidate goes through three evaluation stages:

1. **Syntax** -- `clojure.core/read` confirms it parses as valid Clojure.
2. **Lint** -- clj-kondo checks for common errors and style issues.
3. **Tests** -- clojure.test runs the ground-truth test suite with a
   per-task timeout.

Results are EDN maps: `{:task-id :outcome :syntax-ok :kondo-ok :tests-ok :wall-ms}`.
Evaluation is resumable (skips existing results) and containerized per-task.

## Training pipeline

### Phase 2: SFT

LoRA fine-tuning on 2,459 verified Clojure prompt/solution pairs. Applied to
both Qwen3-8B and Qwen3-30B (MoE, 30B total / 3B active parameters).

### Phase 3: RLVR

GRPO with verifier-in-the-loop rewards:

- **v0**: Binary reward (pass/fail), 10 iterations, 42.3% on held-out.
- **v1**: Shaped reward (`syntax`/`kondo`/`load`/`tests`), sampler refresh,
  48.6% on held-out.
- GRPO advantages normalized per task group (K=8 rollouts).
- Training via Tinker SDK on cloud infrastructure.
- Config: `training/rlvr/config.yaml`

Note: shaped rewards improved pass@1 but narrowed the solution distribution,
lowering the best-of-K ceiling (8B: 72.1% to 64.0%, 30B: 83.8% to 79.3%).

### Phase 4: Scaling to 30B

Qwen3-30B SFT achieves 52.3% pass@1 (+14.5pp over 8B SFT, +7.3pp over Opus 4.7).
RLVR 30B further improves to 55.0% pass@1 but at the cost of a lower ceiling.

## Repository layout

```
benchmark/            Tasks, prompts, generated code, evaluation results
  prompts/multipl-e/    558 .clj prompt files
  tests/                558 .clj test files (ground truth)
  candidates/{run}/     Generated code per run
  results/{run}/        Evaluated outcomes per run
src/learn/            Clojure evaluation tooling (CLI, eval, aggregation)
scripts/              Python runners (baseline generation, training)
training/sft/         SFT on Tinker platform
training/rlvr/        RLVR with GRPO rewards
research/             Literature review, platform docs, analysis
```

## Documentation

| Want to... | Read |
|------------|------|
| Understand the argument | [`THESIS.md`](THESIS.md) |
| See the full research plan | [`PLAN.md`](PLAN.md) |
| Know the benchmark contract | [`BENCHMARK.md`](BENCHMARK.md) |
| Understand evaluation internals | [`src/learn/evaluate.clj`](src/learn/evaluate.clj) |
| See baseline numbers + error analysis | [`research/baseline-analysis.md`](research/baseline-analysis.md) |
| See best-of-K results | [`research/best-of-k-results.md`](research/best-of-k-results.md) |
| See RLVR training analysis | [`research/rlvr-results.md`](research/rlvr-results.md) |
| Review the code-RL literature | [`research/literature-review.md`](research/literature-review.md) |
| Learn the training platform | [`research/tinker-platform.md`](research/tinker-platform.md) |
| Understand Clojure tooling fit | [`research/clojure-ecosystem.md`](research/clojure-ecosystem.md) |

## Citation

If you use this work, please cite it. See [`CITATION.cff`](CITATION.cff)
for the full citation metadata.

```bibtex
@software{clojure_llm_2026,
  author = {nibzard},
  title = {clojure-llm: Can an 8B Model Beat Frontier Models at Clojure Code Generation?},
  year = {2026},
  url = {https://github.com/nibzard/clojure-llm}
}
```

## License

This project is licensed under the [Apache License 2.0](LICENSE).
