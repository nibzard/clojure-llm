# Baseline Analysis: Results Through Phase 4

Date: 2026-04-17
Updated: 2026-04-20 (added Gemini 3.1 Pro, 30B models, RLVR v1)

## Unified Comparison Table

All models on 111 held-out MultiPL-E Clojure tasks. Frontier baselines: single pass, temperature 0.2. Trained models: same eval protocol. Best-of-K: temperature 0.7, pick first passing candidate.

| Model | Type | pass@1 | best-of-2 | best-of-8 | best-of-16 | Full 558 |
|-------|------|--------|-----------|-----------|------------|----------|
| GPT-5.4 | Frontier API | **64.0%** | — | — | — | 65.4% |
| Gemini 3.1 Pro | Frontier API | — | — | — | — | 64.5% |
| GPT-5.4-mini | Frontier API | 59.5% | — | — | — | 60.0% |
| **RLVR Qwen3-30B** | Trained (30B MoE) | **55.0%** | 61.3% | 75.7% | 79.3% | — |
| **SFT Qwen3-30B** | Trained (30B MoE) | **52.3%** | **64.9%** | **75.7%** | **83.8%** | — |
| Qwen3.5-plus | Frontier API | ~55% | — | — | — | — |
| Opus 4.7 | Frontier API | 45.0% | — | — | — | 48.0% |
| **RLVR v1 Qwen3-8B** | Trained (8B dense) | **48.6%** | 54.1% | 61.3% | 64.0% | — |
| **RLVR v0 Qwen3-8B** | Trained (8B dense) | **42.3%** | 55.9% | 67.6% | 72.1% | — |
| **SFT Qwen3-8B** | Trained (8B dense) | **37.8%** | 47.7% | 64.9% | 72.1% | — |
| Qwen3-8B base | No training | ~0% | — | — | — | — |

Key comparisons:
- **30B SFT best-of-2 (64.9%) matches GPT-5.4 pass@1 (64.0%)** — same quality with 3B active params
- **30B SFT best-of-8 (75.7%) beats GPT-5.4 by +11.7pp** — verifier loop amplifies small model
- **RLVR consistently lowers the ceiling**: v0 8B (72.1% → not tested with shaped), v1 8B (64.0% vs SFT's 72.1%), 30B (79.3% vs 83.8%)
- **RLVR v1 8B best-of-16 (64.0%) matches GPT-5.4 pass@1 exactly** — but only at K=16
- **8B RLVR v0 best-of-8 (67.6%) beats GPT-5.4 pass@1 (64.0%)** — v0 with binary rewards preserved diversity
- **RLVR v1 8B (48.6%) > Opus 4.7 (45.0%)** — 8B model beats Opus at pass@1

## Evaluator Bug Fix

The initial evaluation used `edn/read` for syntax checking, which cannot parse Clojure reader macros (`#()` anonymous fn, `#""` regex, `@` deref). This caused ~160 false-negative "invalid-output" classifications per model. Fixed by switching to `clojure.core/read` (LispReader) which handles all reader macros. Results below are corrected.

## Corrected Results

Three frontier model baselines on 558 MultiPL-E Clojure tasks (161 humaneval-clj + 397 mbpp-clj). Direct generation (single pass, temperature 0.2, no tools, no retries).

| Condition | Model | pass@1 | HumanEval | MBPP | Cost |
|-----------|-------|--------|-----------|------|------|
| **B** | GPT-5.4 | **65.4%** (365/558) | 70.8% (114/161) | 63.2% (251/397) | $1.35 |
| **E** | Gemini 3.1 Pro (low thinking) | **64.5%** (360/558) | — | — | $0.55 |
| **B** | GPT-5.4-mini | **60.0%** (335/558) | — | — | $0.33 |
| **A** | Opus 4.7 | **48.0%** (268/558) | 50.9% (82/161) | 46.8% (186/397) | $5.33 |
| **C** | Qwen3.5-plus | **54.7%*** (41/75) | — | — | — |

\* Qwen3.5-plus: partial run (75/558 valid candidates, balance exhausted on mulerouter). 54.7% is biased — only first 75 tasks.
\** Gemini 3.1 Pro: 493/558 valid candidates (65 hit daily API quota). Native Google GenAI SDK, thinking_level="low". 100% syntax-ok rate. Cost estimated from 2-day run (~114K input + ~41K output tokens). Final number may shift slightly when remaining 65 tasks are generated.

## SFT + RLVR Results (111 held-out tasks)

After Phase 2 (SFT) and Phase 3 (RLVR), evaluating on the 111 held-out tasks not seen during training:

| Model | pass@1 (111 held-out) | Invalid output |
|-------|----------------------|----------------|
| GPT-5.4 | 71/111 = 64.0% | — |
| GPT-5.4-mini | 66/111 = 59.5% | — |
| Opus 4.7 | 50/111 = 45.0% | — |
| **RLVR v1 Qwen3-30B** | **~61/111 = 55.0%** | — |
| **SFT Qwen3-30B** | **~58/111 = 52.3%** | — |
| **RLVR v1 Qwen3-8B** | **~54/111 = 48.6%** | — |
| RLVR v0 Qwen3-8B | 46/111 = 41.4% | 7 (6.3%) |
| SFT Qwen3-8B | 42/111 = 37.8% | 5 (4.5%) |

### Training progression

| Stage | Model | pass@1 (111 held-out) | Delta |
|-------|-------|----------------------|-------|
| Base | Qwen3-8B (no training) | ~0% (est.) | — |
| SFT | Qwen3-8B + 2,459 Clojure pairs | 37.8% | +37.8% |
| RLVR v0 | SFT + GRPO (binary reward) | 41.4% | +3.6% |
| RLVR v1 | SFT + GRPO (shaped reward) | 48.6% | +10.8% over SFT |
| SFT 30B | Qwen3-30B-A3B + 2,459 pairs | 52.3% | +14.5pp over 8B SFT |
| RLVR v1 30B | 30B SFT + GRPO (shaped reward) | 55.0% | +2.7pp over 30B SFT |

### Per-task analysis (RLVR vs SFT)

| Category | Count |
|----------|-------|
| Both pass | 37 |
| RLVR only (SFT fails) | 9 |
| SFT only (RLVR fails) | 5 |
| Neither | 60 |

RLVR gained 9 tasks and lost 5, net +4. Two tasks (`humaneval-clj-141`, `mbpp-clj-084`) were solved by RLVR but by none of the frontier baselines.

**Result: the thesis target (8B > Opus 4.7) was met with RLVR v1 (48.6% > 45.0%).** The v0 run (41.4%) fell 3.6pp short, but shaped rewards and sampler refresh closed the gap. Best-of-K evaluation shows the model's ceiling far exceeds pass@1. See `research/rlvr-results.md` for the full training analysis.

## Best-of-K Evaluation (111 held-out tasks)

Generate K candidates per task at temperature 0.7, evaluate each, report whether *any* candidate passes:

### 30B models

| K | 30B SFT | 30B RLVR | Delta |
|---|---------|----------|-------|
| 1 | 59/111 = 53.2% | 60/111 = 54.1% | +0.9pp |
| 2 | 72/111 = 64.9% | 68/111 = 61.3% | -3.6pp |
| 4 | 78/111 = 70.3% | 79/111 = 71.2% | +0.9pp |
| **8** | **84/111 = 75.7%** | **84/111 = 75.7%** | 0pp |
| **16** | **93/111 = 83.8%** | **88/111 = 79.3%** | **-4.5pp** |

**Key finding: RLVR 30B lowered the ceiling by 4.5pp.** SFT 30B is the better model for verifier-in-the-loop deployment.

### 8B models

| K | 8B RLVR v0 | 8B SFT | Delta |
|---|------|-----|-------|
| 1 | 49/111 = 44.1% | 47/111 = 42.3% | +1.8pp |
| 2 | 62/111 = 55.9% | 53/111 = 47.7% | +8.2pp |
| 4 | 69/111 = 62.2% | 63/111 = 56.8% | +5.4pp |
| **8** | **75/111 = 67.6%** | **72/111 = 64.9%** | +2.7pp |
| 16 | 80/111 = 72.1% | 80/111 = 72.1% | 0pp |

**Key finding: both 8B SFT and RLVR best-of-8 beat GPT-5.4 pass@1 (64.0%).** With a verifier loop, the 8B model surpasses the frontier model's single-pass performance.

- Same 72.1% ceiling for both 8B SFT and RLVR — RLVR did not expand knowledge
- RLVR converges faster: best-of-2 is 55.9% (RLVR) vs 47.7% (SFT), a +8.2pp gap

### Implications

The bottleneck is **consistency, not knowledge**. The 30B SFT model solves 93/111 tasks at K=16 (83.8%) — only 18 tasks are genuinely unsolvable. The gap between pass@1 (52.3%) and best-of-16 (83.8%) is the addressable market for a verifier agent.

RLVR's effect is scale-dependent: it preserved the 8B ceiling (72.1%) but lowered the 30B ceiling (83.8% → 79.3%). The shaped reward function may be too constraining at larger scales.

Results: `research/best-of-k-results.json` (8B RLVR), `research/best-of-k-sft-results.json` (8B SFT), `research/best-of-k-30b-results.json` (30B SFT), `research/best-of-k-rlvr-30b-results.json` (30B RLVR), script: `scripts/best_of_k.py`

## Outcome Distribution

| Outcome | Opus 4.7 | GPT-5.4 | GPT-5.4-mini |
|---------|----------|---------|--------------|
| pass | 268 (48.0%) | 365 (65.4%) | 335 (60.0%) |
| fail | 284 (50.9%) | 173 (31.0%) | 213 (38.2%) |
| invalid-output | 5 (0.9%) | 19 (3.4%) | 9 (1.6%) |
| timeout | 1 (0.2%) | 1 (0.2%) | 1 (0.2%) |

## Key Findings

### 1. The evaluator bug inflated apparent difficulty

All models' pass rates jumped ~12-16 points after fixing the syntax checker. The ~30% "invalid-output" rate was an artifact of using `edn/read` instead of Clojure's own reader. Real syntax error rates are 1-3%.

### 2. Opus still trails GPT-5.4 significantly

48.0% vs 65.4% — a 17.4 point gap. Opus has far more test failures (284 vs 173). Opus generates valid Clojure that is *wrong* much more often. This likely reflects less Clojure in Opus's training data.

### 3. Task-level comparison (Opus vs GPT-5.4)

| Category | Count | Pct |
|----------|-------|-----|
| Both pass | 166 | 29.7% |
| GPT-5.4 only | 108 | 19.4% |
| Opus only | 38 | 6.8% |
| Both fail | 246 | 44.1% |

GPT-5.4 wins 108 tasks that Opus fails; Opus only wins 38 the other way. GPT-5.4 leads on both HumanEval and MBPP by similar margins.

### 4. GPT-5.4-mini is close to GPT-5.4

60.0% vs 65.4% — only 5.4 points apart. Clojure difficulty appears more about domain knowledge than raw model scale.

### 5. Error patterns in Opus failures (284 fail + 5 invalid)

| Category | Count | Pct |
|----------|-------|-----|
| Kondo lint failure (namespace/requires) | ~41 | 14% |
| Function name mismatch (snake_case vs kebab) | ~29 | 10% |
| Runtime error (type mismatch, crash) | ~18 | 6% |
| All tests wrong (wrong algorithm) | ~65 | 23% |
| Partial failure (1-2 tests wrong, edge cases) | ~29 | 10% |
| Both fail (multiple issues) | ~102 | 36% |

### 6. Where Opus wins vs GPT-5.4

Opus sometimes writes cleaner, more idiomatic Clojure. Its 38 unique wins include cases where:
- GPT has a subtle algorithmic bug (e.g., off-by-one in `next_smallest`)
- GPT generates a syntax error in edge cases
- Opus uses a more direct approach that happens to be correct

### 7. Where GPT-5.4 wins vs Opus

GPT-5.4's 108 unique wins show patterns:
- Better integer handling (no overflow with `bigint`)
- Correct collection type handling (vector vs set)
- Fewer edge-case bugs (empty input, nil handling)
- Better mathematical formula implementation

## Implications for the Thesis

### The target proved harder than initially estimated (v0), then achieved (v1)

The original thesis was that an 8B model with SFT + RLVR could beat Opus 4.7 (48.0% on full benchmark, 45.0% on held-out). The v0 RLVR run achieved 41.4% — 3.6% short. The v1 run with shaped rewards achieved 48.6%, surpassing Opus by +3.6pp. The 30B model extended the lead further (SFT: 52.3%, RLVR: 55.0%).

### SFT was the bigger win, RLVR was incremental

SFT moved the model from ~0% to 37.8% — a 37.8 point gain from supervised learning on 2,459 Clojure pairs. RLVR added only 3.6 points on top. This suggests the bottleneck is domain knowledge (which SFT addresses well) rather than exploration (which RLVR addresses). More RLVR iterations or better GRPO implementation may help, but the returns appear diminishing.

### The verifier loop is still the key mechanism

Even with the corrected numbers, the gap between single-pass and what's achievable with retries is significant. If an agent loop could fix even 30% of the 284 failures, that's +85 tasks → 63%. **Confirmed by best-of-K: best-of-16 reaches 72.1% on held-out tasks — the model has the knowledge, it just needs a verifier to pick the right attempt.**

### 60 tasks unsolved by any model → 31 after best-of-K

Of 111 held-out tasks, 60 are solved by neither SFT nor RLVR at pass@1. Best-of-K evaluation reduces this to 31 genuinely unsolvable tasks (0/16 pass). The other 29 *can* be solved by the model — they just need multiple attempts with a verifier.

## Next Steps

### Completed

- [x] Fix evaluator syntax checker (edn/read → clojure.core/read)
- [x] Re-evaluate all baselines with fixed evaluator
- [x] Error analysis on failures
- [x] Task-level comparison (Opus vs GPT-5.4)
- [x] SFT on 2,459 verified Clojure pairs (Phase 2) — 37.8% pass@1
- [x] RLVR v0 with GRPO (Phase 3) — 41.4% pass@1
- [x] RLVR v1 with shaped rewards — 48.6% pass@1 (beats Opus 4.7)
- [x] SFT on Qwen3-30B-A3B (Phase 4a) — 52.3% pass@1
- [x] Best-of-K on 30B SFT — 83.8% ceiling
- [x] RLVR on Qwen3-30B-A3B (Phase 4b) — 55.0% pass@1, 79.3% ceiling
- [x] Gemini 3.1 Pro baseline — 64.5% on full 558

### Possible follow-up

- [ ] Build verifier agent loop (best-of-K proves 83.8% ceiling; build actual agent to close gap)
- [ ] Expand SFT data (multi-solution, evol-instruct, error-correction) to raise ceiling
- [ ] Complete Gemini 3.1 Pro run (65 tasks remaining)
- [ ] Repo-level benchmark (Track 2)

## Run Artifacts

| Run ID | Model | Notes |
|--------|-------|-------|
| `2026-04-17-claude-opus-4-7-direct` | Opus 4.7 | Full 558 tasks |
| `2026-04-17-gpt-5-4-direct` | GPT-5.4 | Full 558 tasks |
| `2026-04-17-gpt-5-4-mini-2026-03-17-direct` | GPT-5.4-mini | Full 558 tasks |
| `2026-04-17-qwen35-plus-direct` | Qwen3.5-plus | Partial (75/558) |
| `2026-04-18-sft-qwen3-8b-heldout` | SFT Qwen3-8B | 111 held-out tasks |
| `2026-04-18-rlvr-qwen3-8b-heldout` | RLVR Qwen3-8B | 111 held-out tasks |
