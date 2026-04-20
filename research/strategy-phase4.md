# Phase 4 Strategy: Raising the Ceiling

Date: 2026-04-19

## Current state

| Metric | SFT | RLVR |
|--------|-----|------|
| pass@1 (temp=0.2) | 37.8% | 41.4% |
| pass@1 (temp=0.7) | 42.3% | 44.1% |
| best-of-8 | 64.9% | 67.6% |
| best-of-16 | 72.1% | 72.1% |
| genuinely unsolvable | 31/111 | 31/111 |

**Ceiling: 72.1% (80/111).** SFT and RLVR share this ceiling. To raise it, we need either better training data or a larger model. RLVR alone cannot raise it — it only makes the model reach the ceiling faster.

## What the literature says

### Verifier-in-the-loop is well-established

- **Test-time compute scaling** (Berkeley, 2024): "Scaling LLM Test-Time Compute Optimally Can be More Effective than Scaling Model Parameters." The core argument of our thesis.
- **Self-repair / self-debugging** (Chen et al.): LLM generates, executes, reads errors, fixes in a loop. Our best-of-K is a special case (brute-force retry without error feedback).
- **CodeRL** (Le et al., 2022): RL with critic/verifier to rerank candidates.
- **AlphaCode** (Li et al., 2022): Massive K + clustering + test-based filtering.
- **DeepSeek-R1** (2024): GRPO with verifiable rewards at scale. Showed RLVR works best with many iterations and large batch sizes.
- **RLVR is robust to verifier noise** (Plesner et al., 2026): works even with 15% noisy rewards.

### Key insight from our work

Our SFT vs RLVR best-of-K comparison is the clearest ablation I'm aware of in the literature: same model family, same data, same evaluation, same K, with and without RL. The finding that RLVR doesn't raise the ceiling is publishable.

## Two axes of improvement

### Axis 1: Better SFT data (raises the ceiling)

The 72.1% ceiling is set by what the model learned during SFT. To raise it:

#### 1a. More training pairs from harder tasks

Current: 2,459 pairs from 447 training tasks (MultiPL-E). These are function-level problems.

Options:
- **Evol-instruct for Clojure**: Use a frontier model to evolve existing problems into harder variants. We already have `benchmark/tests/2026-04-18-evol-instruct/` data.
- **4clojure problems**: 150+ problems with idiomatic Clojure solutions. Need license check (EPL).
- **Clojure-specific algorithmic tasks**: Generate problems that exercise Clojure-specific features (transducers, core.async, destructuring, protocols, multimethods).

#### 1b. Multi-solution diversity

For each task, include multiple correct solutions with different approaches. This teaches the model that there are many valid implementations, improving pass@K diversity.

Current: ~1 solution per task. Target: 2-3 solutions per task where possible.

#### 1c. Error-correction pairs

Format: `(broken_code, error_message, fixed_code)`. These teach the model to recover from mistakes — directly useful for the verifier agent loop.

Source: take passing SFT candidates, introduce realistic errors (type mismatch, wrong collection op, off-by-one), pair with the fix.

#### 1d. Repo-level tasks

Move beyond function-level to multi-function, multi-namespace tasks. These exercise import management, namespace design, and cross-function composition — skills the model currently lacks.

Target: 50-100 repo-level tasks from permissively licensed Clojure repos.

### Axis 2: Larger model (raises the ceiling)

Qwen3-30B-A3B (MoE, 30B total / 3B active) costs the same to train on Tinker as Qwen3-8B ($0.36/MT for training, $0.30/MT for sampling). Same price, 4x total parameters.

Hypothesis: the 30B model has more capacity to internalize Clojure patterns from the same SFT data. The ceiling may move from 72.1% to ~80%+.

Risk: MoE models can underperform dense models on niche domains if the active parameters don't specialize well. Need to test.

### RLVR's role: production optimization

RLVR does not raise the ceiling. But it reduces inference cost:
- SFT needs best-of-4 (56.8%) to match RLVR best-of-2 (55.9%)
- That's 2x fewer inference calls for the same quality

RLVR is worth doing *after* SFT data is finalized and the model is selected. It's an inference-cost optimization, not a capability expansion.

## Recommended execution order

### Step 1: Try Qwen3-30B-A3B with current SFT data

Same 2,459 pairs, larger model. This is the cheapest test of whether model scale raises the ceiling.

- SFT training on Tinker: ~$2-3 (same data, same cost tier)
- Evaluate on 111 held-out: pass@1
- Best-of-K (K=16): measure new ceiling

**Expected outcome**: higher pass@1 and possibly higher best-of-K ceiling. The model has more capacity to learn from the same data.

### Step 2: Expand SFT data

If Qwen3-30B-A3B shows promise, invest in more training data:

| Source | Estimated pairs | Difficulty |
|--------|----------------|------------|
| Multi-solution for existing 447 tasks | ~500 new | Easy — generate with frontier model |
| Evol-instruct variants | ~500 | Easy — already have pipeline |
| 4clojure (if license OK) | ~150 | Medium — need adaptation |
| Error-correction pairs | ~300 | Easy — mutate existing solutions |
| Repo-level tasks | ~100 | Hard — need curation |
| **Total** | **~3,000-4,000** (on top of existing 2,459) | |

Target: ~5,000-6,500 total SFT pairs.

### Step 3: SFT on Qwen3-30B-A3B with expanded data

Full SFT training run on the larger model with the expanded dataset.

Estimated cost: ~$5-8 for SFT.

### Step 4: Best-of-K on new SFT model

Measure the new ceiling. If it's significantly above 72.1%, we have a clear result.

### Step 5: RLVR on best model (optional)

Only if we want to reduce inference cost. Use proper GRPO (fix `importance_sampling` loss) with:
- 50-100 iterations
- 60 tasks per iteration
- Shaped rewards (syntax + kondo + tests)

Estimated cost: ~$10-25.

### Step 6: Build verifier agent loop

The practical product. Uses the best model (likely Qwen3-30B-A3B + SFT) with:
1. Generate candidate
2. Run Clojure tests
3. If fail, feed error back to model for retry
4. Up to K attempts

This should close most of the gap between pass@1 and best-of-K, potentially reaching 65-70% pass@1 equivalent with only 2-4 inference passes.

## Cost estimate (full Phase 4)

| Step | Cost |
|------|------|
| Qwen3-30B-A3B SFT (existing data) | ~$3 |
| Evaluate + best-of-K | ~$0.72 |
| Expanded SFT data generation | ~$2 (frontier model API) |
| Qwen3-30B-A3B SFT (expanded data) | ~$5 |
| Best-of-K on expanded model | ~$0.72 |
| RLVR (optional) | ~$10-25 |
| **Total** | **~$12-35** |

## Success criteria

| Metric | 8B Result | 30B SFT | 30B RLVR | Target | Stretch |
|--------|-----------|---------|----------|--------|---------|
| pass@1 | 41.4% | **52.3%** | **55.0%** | > 50% ✓ | > 55% |
| best-of-16 | 72.1% | **83.8%** | 79.3% | > 80% ✓ | > 85% |
| best-of-8 vs GPT-5.4 | +3.6pp | **+11.7pp** | +11.7pp | +10pp ✓ | +15pp |

**All targets met (SFT 30B is the best model).** RLVR 30B improved pass@1 to 55.0% (hitting the stretch target) but lowered the best-of-16 ceiling to 79.3%. SFT 30B remains the better model for verifier-in-the-loop deployment due to its 83.8% ceiling.

If Qwen3-30B-A3B + expanded SFT + best-of-8 > 75%, that's a clear win over GPT-5.4 (64.0%) by 11+ points — a publishable result. **Achieved: 75.7% best-of-8 = +11.7pp over GPT-5.4.**
