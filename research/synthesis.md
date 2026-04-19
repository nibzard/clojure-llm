# What We Learned: Synthesis of Results Through Phase 3b

Date: 2026-04-19

## The core result

An 8B model with SFT + verifier loop **beats GPT-5.4** (the best frontier model) on Clojure code generation. Not at pass@1 — but at best-of-8 (67.6% vs 64.0%). The thesis holds when the verifier is actually used at inference time.

## SFT did the heavy lifting

| Stage | pass@1 | Delta |
|-------|--------|-------|
| Base Qwen3-8B | ~0% | — |
| + SFT (2,459 pairs) | 37.8% | +37.8pp |
| + RLVR (GRPO, 10 iters) | 41.4% | +3.6pp |

SFT taught the model Clojure. RLVR made it slightly more consistent. The 37.8pp gain from SFT dwarfs the 3.6pp from RLVR. If you have limited budget, spend it on high-quality SFT data, not RL iterations.

## RLVR improved consistency, not knowledge

The SFT best-of-K run was the decisive experiment:

- **Same ceiling**: both SFT and RLVR solve 80/111 tasks at K=16 (72.1%)
- **Faster convergence**: RLVR best-of-2 is 55.9% vs SFT's 47.7% (+8.2pp)
- **Gap shrinks with more samples**: at K=16 the gap is 0pp

RLVR's policy learned to produce correct solutions more reliably on the first few tries. It did not discover new solution strategies. The ceiling is set by what SFT taught the model.

## The bottleneck is consistency, not knowledge

60 tasks were "unsolved" at pass@1. Best-of-K split these cleanly:
- **29 tasks**: stochastic failures — the model can solve them, just not every time. Retries + verifier fix these.
- **31 tasks**: genuine knowledge gaps — the model never produces a correct solution in 16 attempts.

So roughly half the failures are fixable with a verifier loop, half require better training.

## The thesis is partially vindicated

**Original thesis**: "fast feedback loops (REPL, clj-kondo, clojure.test) matter more than model scale"

- **pass@1**: not vindicated. RLVR (41.4%) < Opus (45.0%) < GPT-5.4 (64.0%)
- **best-of-K**: vindicated. 8B + verifier (67.6%) > GPT-5.4 single-pass (64.0%)
- The caveat: you need the verifier at inference time, not just training time

## What didn't work

1. **RLVR with REINFORCE fallback** — Tinker's `importance_sampling` loss had API issues. The REINFORCE fallback had higher variance and no KL penalty. Proper GRPO might have done better.
2. **Binary rewards** — all-or-nothing signal wastes gradient computation when all K rollouts pass or all fail. Shaped rewards (syntax + kondo + tests) would provide denser signal.
3. **10 iterations, 30 tasks/iter** — too few. Each task was seen ~0.67 times on average. Published RLVR results use 50-200 iterations.
4. **Pass@1 vs Opus** — the original target (8B > Opus at pass@1) was not met. The gap (3.6pp) is real.

## What we'd do differently

1. **Skip RLVR, invest in the verifier agent loop** — since the ceiling is the same with or without RLVR, the practical path is SFT + agent that generates, tests, and retries with error feedback. This is cheaper and more general than more RL training.
2. **Raise the ceiling with better SFT data** — 2,459 pairs got us to 72.1%. More diverse, harder training problems (repo-level, multi-step) would push the ceiling higher.
3. **Try a larger base model** — Qwen3-30B-A3B MoE costs the same to train on Tinker but has 4x the capacity. Worth testing whether the ceiling moves.

## The meta-lesson

Best-of-K evaluation should be standard practice in code generation research. Pass@1 tells you about single-shot reliability; best-of-K tells you about the model's capability ceiling. The gap between them (44% → 72%) is the addressable market for a verifier-in-the-loop agent. If best-of-K >> pass@1, invest in the agent. If best-of-K ≈ pass@1, invest in better training.
