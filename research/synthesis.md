# What We Learned: Synthesis of Results Through Phase 4

Date: 2026-04-20

## The core result

A 30B MoE model (3B active params) with SFT + verifier loop **beats GPT-5.4** (the best frontier model) on Clojure code generation. At best-of-2, it already matches GPT-5.4 (64.9% vs 64.0%). At best-of-8, it leads by 11.7pp (75.7% vs 64.0%). The thesis holds decisively.

The 8B model was the first proof: best-of-8 (67.6%) beats GPT-5.4 pass@1 (64.0%). The 30B model, trained on the **exact same 2,459 SFT pairs**, raised the ceiling from 72.1% to 83.8% — proving that model scale compounds with the verifier approach.

RLVR on the 30B model improved pass@1 (+2.7pp, from 52.3% to 55.0%) but **lowered the best-of-16 ceiling** from 83.8% to 79.3%. Unlike the 8B model where SFT and RLVR shared the same ceiling, RLVR on the 30B model narrowed the solution distribution, losing 5 tasks that the SFT model could solve at K=16. This is a novel finding: RLVR's effect on the capability ceiling is scale-dependent.

## SFT did the heavy lifting — and model scale multiplied it

| Stage | pass@1 | best-of-16 | Delta |
|-------|--------|------------|-------|
| Base Qwen3-8B | ~0% | ~0% | — |
| + SFT (2,459 pairs, 8B) | 37.8% | 72.1% | +37.8pp |
| + RLVR (GRPO, 10 iters, 8B) | 41.4% | 72.1% | +3.6pp |
| + **SFT (2,459 pairs, 30B)** | **52.3%** | **83.8%** | **+14.5pp over 8B SFT** |
| + RLVR (GRPO, 10 iters, 30B) | 55.0% | 79.3% | +2.7pp pass@1, -4.5pp ceiling |

Same data, larger model: +14.5pp pass@1, +11.7pp ceiling. The 30B MoE model has more capacity to internalize Clojure patterns from the same training signal. The model scale hypothesis is confirmed.

## RLVR improved consistency, not knowledge — but lowered the 30B ceiling

### 8B model

The 8B SFT vs RLVR best-of-K comparison was clean:

- **Same ceiling**: both SFT and RLVR solve 80/111 tasks at K=16 (72.1%)
- **Faster convergence**: RLVR best-of-2 is 55.9% vs SFT's 47.7% (+8.2pp)
- **Gap shrinks with more samples**: at K=16 the gap is 0pp

RLVR's policy learned to produce correct solutions more reliably on the first few tries. It did not discover new solution strategies.

### 30B model

RLVR on the 30B model told a different story:

- **pass@1 improved**: 55.0% vs 52.3% (+2.7pp) — consistency gain
- **Ceiling dropped**: 79.3% vs 83.8% (-4.5pp) — 5 tasks lost
- **Same best-of-8**: 75.7% for both — the lost tasks are in the K=9-16 range
- **The lost tasks require rare diverse solutions** that RLVR's narrowed distribution no longer produces

**Implication**: RLVR's effect is scale-dependent. On smaller models, it regularizes without harming diversity. On larger models, it can over-constrain the policy. The shaped reward function (syntax + kondo + tests) may also be too constraining — encouraging solutions that maximize all reward components rather than just passing tests.

## The bottleneck is consistency, not knowledge

60 tasks were "unsolved" at pass@1. Best-of-K split these cleanly:
- **29 tasks**: stochastic failures — the model can solve them, just not every time. Retries + verifier fix these.
- **31 tasks**: genuine knowledge gaps — the model never produces a correct solution in 16 attempts.

So roughly half the failures are fixable with a verifier loop, half require better training.

## The thesis is vindicated

**Original thesis**: "fast feedback loops (REPL, clj-kondo, clojure.test) matter more than model scale"

- **pass@1**: 30B SFT (52.3%) beats Opus (45.0%) but still trails GPT-5.4 (64.0%)
- **best-of-K**: decisively vindicated.
  - 8B + verifier best-of-8 (67.6%) > GPT-5.4 pass@1 (64.0%)
  - 30B + verifier best-of-2 (64.9%) ≈ GPT-5.4 pass@1 (64.0%)
  - 30B + verifier best-of-8 (75.7%) >> GPT-5.4 pass@1 (64.0%), +11.7pp
  - 30B + verifier best-of-16 (83.8%) >> everything
- Model scale matters — but the verifier loop amplifies the model's capability at any scale

## What didn't work

1. **RLVR with REINFORCE fallback** — Tinker's `importance_sampling` loss had API issues. The REINFORCE fallback had higher variance and no KL penalty. Proper GRPO might have done better.
2. **Binary rewards** — all-or-nothing signal wastes gradient computation when all K rollouts pass or all fail. Shaped rewards (syntax + kondo + tests) would provide denser signal.
3. **10 iterations, 30 tasks/iter** — too few. Each task was seen ~0.67 times on average. Published RLVR results use 50-200 iterations.
4. **Pass@1 vs Opus** — the original target (8B > Opus at pass@1) was not met. The gap (3.6pp) is real.
5. **RLVR lowered the 30B ceiling** — on the 8B model, RLVR preserved the 72.1% ceiling. On the 30B model, it dropped from 83.8% to 79.3%. RLVR narrowed the solution distribution too aggressively at 30B scale. The shaped reward function may share blame.

## What we'd do differently

1. **Start with the larger model.** The 30B MoE costs the same to train as the 8B on Tinker but learns much more from the same data. If we had started with 30B, we'd have hit 52.3% pass@1 and 83.8% best-of-16 in Phase 2.
2. **Invest in SFT data diversity before RL.** The 30B model's ceiling (83.8%) is set by the 2,459 SFT pairs. Expanding to ~5,000-6,500 pairs (multi-solution, evol-instruct, error-correction) should push toward 90%+.
3. **RLVR needs caution at larger scales.** RLVR improved 8B consistency without harming diversity. On 30B, it narrowed the solution distribution and lowered the ceiling. If we rerun RLVR on 30B, we should use a higher KL penalty, more rollouts per group, and possibly binary (test-only) rewards instead of shaped rewards.
4. **The SFT 30B model is the production model.** Despite RLVR 30B's higher pass@1 (55.0%), the SFT model's 83.8% ceiling makes it the better choice for verifier-in-the-loop deployment. The ceiling advantage matters more than single-shot improvement.

## The meta-lesson

Best-of-K evaluation should be standard practice in code generation research. Pass@1 tells you about single-shot reliability; best-of-K tells you about the model's capability ceiling. The gap between them (53% → 84% for 30B) is the addressable market for a verifier-in-the-loop agent. If best-of-K >> pass@1, invest in the agent. If best-of-K ≈ pass@1, invest in better training.

For the 30B model, the gap is 31pp (53.2% → 83.8%). This means 34 out of 52 failures are stochastic — the model can solve them, just not every time. Only 18 tasks are genuinely beyond the model's capability. The verifier agent has enormous leverage.
