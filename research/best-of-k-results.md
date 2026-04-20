# Best-of-K Evaluation Results

Date: 2026-04-19 (8B), 2026-04-20 (30B)

## Summary

Best-of-K evaluation measures a model's *ceiling*: generate K candidates per task at temperature 0.7, evaluate each via Clojure subprocess, and report whether *any* candidate passes. This estimates what pass@1 would look like if the model had a perfect verifier to pick the right answer.

### Phase 4: Qwen3-30B-A3B raises the ceiling to 83.8%

The 30B MoE model (30B total / 3B active) trained on the **same 2,459 SFT pairs** as the 8B model achieves **83.8% best-of-16** (93/111) — up from 72.1% with the 8B model. Best-of-2 already matches GPT-5.4's pass@1.

| K | 30B Pass | 30B Rate | 8B RLVR | 8B SFT |
|---|----------|----------|---------|--------|
| 1 | 59/111 | 53.2% | 49/111 = 44.1% | 47/111 = 42.3% |
| 2 | 72/111 | 64.9% | 62/111 = 55.9% | 53/111 = 47.7% |
| 4 | 78/111 | 70.3% | 69/111 = 62.2% | 63/111 = 56.8% |
| 8 | 84/111 | 75.7% | 75/111 = 67.6% | 72/111 = 64.9% |
| **16** | **93/111** | **83.8%** | 80/111 = 72.1% | 80/111 = 72.1% |

### Key comparisons

| Model | pass@1 | best-of-8 | best-of-16 |
|-------|--------|-----------|------------|
| **30B SFT** | **52.3%** | **75.7%** | **83.8%** |
| GPT-5.4 | 64.0% | — | — |
| GPT-5.4-mini | 59.5% | — | — |
| 8B RLVR | 41.4% | 67.6% | 72.1% |
| 8B SFT | 37.8% | 64.9% | 72.1% |
| Opus 4.7 | 45.0% | — | — |

## Motivation

The RLVR model's pass@1 (41.4%) fell short of Opus 4.7 (45.0%) and GPT-5.4 (64.0%). But pass@1 is a single-sample measurement. Two explanations for failure on any given task:

1. **Stochastic failure**: the model *can* solve the task, but not on every attempt. A verifier could pick the correct solution from multiple tries.
2. **Knowledge gap**: the model *cannot* solve the task regardless of retries. No amount of sampling helps.

Best-of-K separates these cases. If best-of-K >> pass@1, the bottleneck is consistency. If best-of-K ~ pass@1, the bottleneck is knowledge.

## Setup

| Parameter | Value |
|-----------|-------|
| Model | RLVR Qwen3-8B (SFT + GRPO, 10 iterations) |
| Checkpoint | `tinker://cf3778fc-5553-5e8d-be25-84859b2de080:train:0/weights/checkpoint-iter-10` |
| Tasks | 111 held-out MultiPL-E Clojure tasks |
| K | 16 candidates per task |
| Temperature | 0.7 |
| Max tokens | 512 |
| Evaluation | Clojure subprocess (load-file + clojure.test) |
| Platform | Tinker cloud (sampling), local (evaluation) |
| Cost | ~$0.36 (1,776 samples x ~512 tokens x $0.40/MT) |

## Results

### Best-of-K curve

| K | Pass | Rate | Lift over pass@1 |
|---|------|------|-------------------|
| 1 | 49 | 44.1% | — |
| 2 | 62 | 55.9% | +11.8pp |
| 4 | 69 | 62.2% | +18.0pp |
| 8 | 75 | 67.6% | +23.4pp |
| 16 | 80 | 72.1% | +27.9pp |

The curve is still rising at K=16, though diminishing returns set in after K=8. Each doubling of K adds fewer new tasks:

| Step | New tasks gained |
|------|-----------------|
| 1→2 | +13 |
| 2→4 | +7 |
| 4→8 | +6 |
| 8→16 | +5 |

### Source breakdown

| Source | Tasks | pass@1 | best-of-16 | Lift |
|--------|-------|--------|------------|------|
| humaneval-clj | 32 | 14/32 (43.8%) | 25/32 (78.1%) | +34.4pp |
| mbpp-clj | 79 | 35/79 (44.3%) | 55/79 (69.6%) | +25.3pp |

HumanEval tasks benefit more from retries. This makes sense: HumanEval tasks tend to be algorithmically focused with deterministic test cases, so the model often has the right idea but makes small errors. MBPP tasks are more diverse and some require domain-specific knowledge that retries cannot compensate for.

### Comparison with baselines (pass@1, single sample)

| Model | pass@1 (111 held-out) | Best-of-K equivalent |
|-------|----------------------|---------------------|
| GPT-5.4 | 71/111 = 64.0% | ~RLVR best-of-4 |
| GPT-5.4-mini | 66/111 = 59.5% | ~SFT best-of-8 |
| **RLVR best-of-16** | **80/111 = 72.1%** | — |
| **RLVR best-of-8** | **75/111 = 67.6%** | — |
| **SFT best-of-8** | **72/111 = 64.9%** | — |
| Opus 4.7 | 50/111 = 45.0% | ~RLVR best-of-1 |
| RLVR pass@1 | 49/111 = 44.1% | — |
| SFT pass@1 | 47/111 = 42.3% | — |

Both SFT and RLVR best-of-8 exceed GPT-5.4 pass@1 (64.0%). This is with an 8B model costing ~$0.36 for the full evaluation, versus GPT-5.4 at $1.35 for single-pass on 558 tasks.

## SFT vs RLVR Comparison

We ran the same best-of-16 evaluation on the SFT checkpoint (before RLVR training) to isolate RLVR's contribution.

| K | SFT | RLVR | Delta |
|---|-----|------|-------|
| 1 | 47/111 = 42.3% | 49/111 = 44.1% | +1.8pp |
| 2 | 53/111 = 47.7% | 62/111 = 55.9% | +8.2pp |
| 4 | 63/111 = 56.8% | 69/111 = 62.2% | +5.4pp |
| 8 | 72/111 = 64.9% | 75/111 = 67.6% | +2.7pp |
| 16 | 80/111 = 72.1% | 80/111 = 72.1% | 0pp |

**Same ceiling (72.1%), but RLVR converges faster.**

### What this means

1. **RLVR did not expand the model's knowledge.** Both SFT and RLVR solve the same 80 tasks at K=16. RLVR did not discover new solution strategies that SFT didn't already teach.

2. **RLVR improved consistency, especially at low K.** The gap is largest at K=2 (+8.2pp) and shrinks as K grows. RLVR's policy learned to produce correct solutions more reliably on the first few attempts — exactly what GRPO optimizes for.

3. **SFT alone is already strong with a verifier.** SFT best-of-8 (64.9%) beats GPT-5.4 pass@1 (64.0%). The SFT training on 2,459 Clojure pairs was sufficient to teach the model most of what it knows. RLVR's +3.6% pass@1 improvement is a real but modest consistency gain on top.

4. **The ceiling is set by SFT data quality, not RL.** To raise the 72.1% ceiling, the model needs either better SFT data (more diverse problems, more correct solutions) or a stronger base model. More RLVR iterations would only close the gap between pass@1 and best-of-16 faster, not raise the ceiling.

### Implication for the training pipeline

The RL step is optional if you have a verifier at inference time. SFT + best-of-16 already reaches 72.1%. RLVR's value is in reducing the number of samples needed (best-of-2: 55.9% vs 47.7%), which translates to lower inference cost in a production agent.

### Pass count distribution

Of 80 tasks that pass at least once:

| Pass count (out of 16) | Tasks | Interpretation |
|------------------------|-------|----------------|
| 16/16 | 17 | Model is highly reliable |
| 10-15/16 | 24 | Consistently solves |
| 5-9/16 | 18 | Often solves |
| 2-4/16 | 15 | Occasionally solves |
| 1/16 | 6 | Barely solves (fragile) |

17 tasks pass all 16/16 samples — the model has mastered these. 24 more pass 10-15/16. Together, 41 tasks (37% of total) are near-reliably solved.

6 tasks are "fragile wins" — they pass only 1 out of 16 attempts:

| Task | Source |
|------|--------|
| humaneval-clj-039 | HumanEval |
| humaneval-clj-062 | HumanEval |
| mbpp-clj-148 | MBPP |
| mbpp-clj-179 | MBPP |
| mbpp-clj-181 | MBPP |
| mbpp-clj-214 | MBPP |

These are right at the edge of the model's capability. A smarter verifier (not just pass/fail but partial credit) might help here.

### Tasks gained and lost

| Category | Count |
|----------|-------|
| Pass pass@1, pass best-of-16 | 49 |
| Fail pass@1, pass best-of-16 (gained) | 31 |
| Pass pass@1, fail best-of-16 (lost) | 0 |
| Fail both (genuinely unsolvable) | 31 |

**0 tasks were lost** — no task that passes on the first attempt fails across all 16 attempts. This confirms the model is not "getting lucky" on pass@1; pass@1 successes are robust.

31 tasks were gained by best-of-16. 31 tasks remain genuinely unsolvable (0/16 pass). The original 60 unsolved tasks (from the pass@1 analysis) split almost evenly: 29 were stochastic failures that retries fix, 31 are knowledge gaps that require better training or different approaches.

## Why Best-of-K Works Here

### 1. The model generates diverse correct solutions

At temperature 0.7, the model explores different solution strategies. Some fail (wrong algorithm, syntax error, edge case bug) but others succeed. The diversity is genuine — different rollouts often use different approaches.

### 2. Clojure has fast executable verification

Each evaluation takes <10 seconds via Clojure subprocess. This makes best-of-K cheap and practical: 16 evaluations per task x 111 tasks = 1,776 evaluations, all local, zero API cost.

### 3. Binary pass/fail is sufficient as a verifier

The model doesn't need a nuanced reward signal for best-of-K to work. Just "do the tests pass?" is enough to select the correct solution from multiple candidates.

## Implications

### The thesis is vindicated — with a caveat

The original thesis: "fast feedback loops (REPL, clj-kondo, clojure.test) matter more than model scale." Best-of-K confirms this: an 8B model with a verifier loop beats a frontier model without one. But the caveat is that pass@1 alone did not beat the frontier. The thesis holds *when the verifier is actually used at inference time*.

### The gap is consistency, not knowledge

The model can solve 80/111 tasks (72.1%) — it just doesn't solve them reliably on the first try. The SFT comparison confirms this is a property of the SFT training itself, not RLVR: both models share the same 72.1% ceiling. The 29 tasks that go from "unsolved" to "solved" with retries are not learning problems; they are sampling problems. RLVR makes sampling more efficient but does not change what the model knows.

### Practical path: build the verifier agent

Best-of-K is an *upper bound* (oracle verifier that picks the first passing sample). A real agent loop would:
1. Generate a candidate
2. Run tests
3. If tests fail, use the error feedback to generate a *better* candidate
4. Repeat

This is more efficient than brute-force best-of-K because the agent can learn from failures. If an agent loop achieves even 60% of the best-of-K ceiling (72.1%), that's 43% — already competitive with Opus.

### The 31 unsolvable tasks

31 tasks pass 0/16. These are genuine knowledge gaps. Analysis of these tasks (see `research/baseline-analysis.md`) suggests they involve:
- Complex algorithms requiring mathematical insight
- Obscure Clojure APIs or Java interop
- Tricky edge cases in collection manipulation
- Tasks where even GPT-5.4 sometimes fails

These may require better base models, continued pretraining on more Clojure code, or multi-step reasoning.

## Artifacts

| Artifact | Location |
|----------|----------|
| Evaluation script | `scripts/best_of_k.py` |
| 30B results (JSON) | `research/best-of-k-30b-results.json` |
| RLVR results (JSON) | `research/best-of-k-results.json` |
| SFT results (JSON) | `research/best-of-k-sft-results.json` |
| 30B SFT checkpoint | `tinker://6d56c642-fed2-539d-853b-8311cc4939ed:train:0/weights/checkpoint-step-600` |
| RLVR checkpoint | `tinker://cf3778fc-5553-5e8d-be25-84859b2de080:train:0/weights/checkpoint-iter-10` |
| 8B SFT checkpoint | `tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600` |
| Training analysis | `research/rlvr-results.md` |
| Baseline comparison | `research/baseline-analysis.md` |

## Cost

| Item | Cost per run |
|------|-------------|
| Sampling (1,776 x ~512 tokens) | ~$0.36 |
| Evaluation (local Clojure subprocess) | $0 |
| **Total per run** | **~$0.36** |
| **Both runs (RLVR + SFT)** | **~$0.72** |

## Next Steps

1. **Expand SFT data for 30B.** The 30B model raised the ceiling from 72.1% to 83.8% with the same data. Adding ~3,000-4,000 more diverse SFT pairs (multi-solution, evol-instruct, error-correction) could push toward 90%+.
2. **RLVR on 30B.** RLVR improved 8B consistency by +3.6pp pass@1. On 30B, RLVR might push pass@1 from 52.3% toward 55-58%, making best-of-2 competitive with GPT-5.4.
3. **Build the verifier agent loop.** The 30B ceiling is 83.8%. A smart agent that generates, tests, and retries with error feedback should close most of the gap between pass@1 and best-of-K.
4. **Write up for publication.** The result — 30B + SFT + verifier beats GPT-5.4 pass@1 at best-of-2 (64.9% vs 64.0%) — is publishable.
