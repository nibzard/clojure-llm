# Phase 3 Results: RLVR Training with GRPO

Date: 2026-04-19

## Summary

Qwen3-8B trained with SFT (2,459 pairs) followed by RLVR (GRPO, 10 iterations) achieved **41.4% pass@1** on 111 held-out Clojure tasks — an improvement of **+3.6%** over SFT alone (37.8%), but **3.6% short** of the Opus 4.7 target (45.0%).

| Model | pass@1 (111 held-out) | Invalid output |
|-------|----------------------|----------------|
| GPT-5.4 | 71/111 = 64.0% | — |
| GPT-5.4-mini | 66/111 = 59.5% | — |
| Opus 4.7 | 50/111 = 45.0% | — |
| **RLVR Qwen3-8B** | **46/111 = 41.4%** | 7 (6.3%) |
| SFT Qwen3-8B | 42/111 = 37.8% | 5 (4.5%) |

**Thesis outcome: the 8B model did not beat Opus 4.7.** RLVR closed the gap from 7.2% (SFT vs Opus) to 3.6% (RLVR vs Opus) but did not surpass it.

## Training Setup

### Starting checkpoint

SFT Qwen3-8B-Base fine-tuned on 2,459 verified Clojure (prompt, solution) pairs.
Tinker checkpoint: `tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600`

SFT held-out result: 42/111 = 37.8% pass@1.

### RLVR configuration

| Parameter | Value |
|-----------|-------|
| Algorithm | GRPO (Group Relative Policy Optimization) |
| Rollouts per task | K = 8 |
| Tasks per iteration | 30 (sampled from 447 training tasks) |
| Iterations | 10 |
| Temperature | 0.7 (rollout), 0.2 (evaluation) |
| Max tokens | 512 |
| Learning rate | 5e-6 |
| Reward | Binary: 1.0 test-pass, 0.0 fail |
| Loss function | `cross_entropy` with advantage-scaled weights |
| Optimizer | Adam (weight_decay=0.01, grad_clip=1.0) |
| Total samples | 10 × 30 × 8 = 2,400 |
| Training time | 14,873s (~4h 8m) |
| Platform | Tinker cloud training |

### Why cross_entropy instead of importance_sampling

Tinker SDK v0.18.1 supports `importance_sampling` loss for proper GRPO, but the API rejected our Datum format with tensor shape errors (`BadRequestError: 'Could not convert loss function inputs to array record'`). This is likely a version mismatch between our Datum construction and the backend's expected tensor layout.

Fallback: REINFORCE with advantage-scaled weights. For each rollout:
- Compute GRPO advantage: `adv_i = (r_i - mean(r_group)) / std(r_group)`
- Scale the loss mask by `max(advantage, 0.0)` — only positive-advantage samples contribute
- This is valid reward-weighted regression, but lacks the importance-sampling correction that makes GRPO more sample-efficient

This is a genuine methodological limitation of this run. The REINFORCE fallback has higher variance than proper GRPO with old-policy log-prob ratios.

## Training Trajectory

| Iter | Pass rate | Mean reward | Loss | Time |
|------|-----------|-------------|------|------|
| 1 | 161/240 = 67.1% | 0.67 | 154.5 | 22m |
| 2 | 137/240 = 57.1% | 0.57 | 503.3 | 21m |
| 3 | 155/240 = 64.6% | 0.65 | 171.5 | 18m |
| 4 | 105/240 = 43.8% | 0.44 | 536.7 | 23m |
| 5 | 112/240 = 46.7% | 0.47 | 436.9 | 20m |
| 6 | 168/240 = 70.0% | 0.70 | 307.5 | 20m |
| 7 | 132/240 = 55.0% | 0.55 | 178.3 | 22m |
| 8 | 143/240 = 59.6% | 0.60 | 373.0 | 27m |
| 9 | 116/240 = 48.3% | 0.48 | 352.4 | 40m |
| 10 | 123/240 = 51.2% | 0.51 | 271.2 | 35m |

**Mean pass rate across training: 56.3%** (training tasks, temperature=0.7, K=8 rollouts).

### Observations

1. **No clear improvement trend.** Pass rates oscillate between 43% and 70% with no monotonic gain. This is expected with small batches (30 tasks) and high variance in GRPO with binary rewards, but also suggests the learning signal is weak.

2. **Loss is noisy.** Ranges from 154 to 537 without converging. The REINFORCE loss scale depends on the magnitude of positive advantages, which varies with the reward distribution in each batch.

3. **Pass rates on training tasks are higher than held-out evaluation.** Training-time 56.3% mean vs evaluation 41.4%. This gap reflects: (a) temperature 0.7 during training vs 0.2 at eval, (b) K=8 rollouts vs single-shot eval, and (c) different task sets.

4. **Iteration 9 spike in time.** 40 minutes vs typical 20-25 minutes. Unclear cause — possibly Tinker queue congestion.

## Per-Task Analysis

### RLVR vs SFT

| Category | Count |
|----------|-------|
| Both pass | 37 |
| RLVR only | 9 |
| SFT only | 5 |
| Neither | 60 |

RLVR gained 9 tasks that SFT missed, but lost 5 that SFT solved. Net gain: +4 tasks.

### Tasks gained by RLVR (not solved by SFT)

| Task ID | Source |
|---------|--------|
| humaneval-clj-012 | HumanEval |
| humaneval-clj-097 | HumanEval |
| humaneval-clj-105 | HumanEval |
| humaneval-clj-141 | HumanEval |
| humaneval-clj-144 | HumanEval |
| mbpp-clj-084 | MBPP |
| mbpp-clj-132 | MBPP |
| mbpp-clj-268 | MBPP |
| mbpp-clj-308 | MBPP |

### Tasks lost by RLVR (SFT solved, RLVR did not)

| Task ID | Source |
|---------|--------|
| humaneval-clj-067 | HumanEval |
| mbpp-clj-060 | MBPP |
| mbpp-clj-087 | MBPP |
| mbpp-clj-191 | MBPP |
| mbpp-clj-371 | MBPP |

### RLVR unique wins vs all baselines

2 tasks were solved by RLVR that neither Opus 4.7 nor GPT-5.4 solved:
- `humaneval-clj-141`
- `mbpp-clj-084`

### Outcome distribution

| Outcome | SFT | RLVR | Delta |
|---------|-----|------|-------|
| pass | 42 (37.8%) | 46 (41.4%) | +4 |
| fail | 64 (57.7%) | 58 (52.3%) | -6 |
| invalid-output | 5 (4.5%) | 7 (6.3%) | +2 |

RLVR reduced test failures by 6 but increased syntax/parse errors by 2. The increase in invalid-output suggests RLVR may have pushed the model toward generating code that is harder to parse (possibly more complex or creative solutions that sacrifice syntactic correctness).

## Why RLVR Fell Short

### 1. REINFORCE instead of proper GRPO

The fallback to `cross_entropy` with advantage-weighted masks lacks the importance-sampling correction. This means:
- Higher gradient variance
- No explicit KL penalty to prevent deviation from the SFT policy
- Less sample-efficient than intended

### 2. Binary rewards have low signal-to-noise

With binary (pass/fail) rewards and K=8 rollouts per task:
- If all 8 rollouts pass or all fail, GRPO advantages are zero → no learning signal
- Many task groups had all-same outcomes, wasting gradient computation
- A shaped reward (syntax, kondo, namespace-load, tests) would provide more granular signal

### 3. Small batch size (30 tasks per iteration)

With 447 training tasks and only 30 sampled per iteration:
- High variance between iterations
- Each task seen ~0.67 times on average across 10 iterations
- Many tasks never seen during training

### 4. Only 10 iterations

10 iterations × 240 samples = 2,400 total training samples is modest for RL. Published RLVR results typically use 50-200 iterations with larger batches.

### 5. Model capacity ceiling

Qwen3-8B is a general-purpose base model. Even with SFT + RLVR, its Clojure knowledge is limited compared to frontier models that saw orders of magnitude more Clojure during pretraining.

## Artifacts

| Artifact | Location |
|----------|----------|
| Training code | `training/rlvr/train.py` |
| Evaluation code | `training/rlvr/evaluate.py` |
| Smoke test | `training/rlvr/smoke_test.py` |
| Config | `training/rlvr/config.yaml` |
| Training summary | `checkpoints/rlvr/training_summary.json` |
| wandb run | `nibzard-org/clojure-llm/runs/pqshb7mm` |
| RLVR checkpoint (Tinker) | `tinker://cf3778fc-5553-5e8d-be25-84859b2de080:train:0/weights/checkpoint-iter-10` |
| Held-out candidates | `benchmark/candidates/2026-04-18-rlvr-qwen3-8b-heldout/` |
| Held-out results | `benchmark/results/2026-04-18-rlvr-qwen3-8b-heldout/` |
| Run manifest | `benchmark/runs/2026-04-18-rlvr-qwen3-8b-heldout.edn` |

## Cost

| Item | Cost |
|------|------|
| Rollout sampling (2,400 × ~512 tokens) | ~$0.50 |
| Training forward/backward (2,400 × ~2,048 tokens) | ~$2.00 |
| Evaluation (local Clojure subprocess) | $0 |
| Held-out generation (111 × 512 tokens) | ~$0.02 |
| **Total** | **~$2.52** |

## Possible Improvements

1. **Fix `importance_sampling` loss.** Debug the Datum tensor format to enable proper GRPO with old-policy log-prob ratios and KL penalty. This is the single highest-impact fix.

2. **More iterations and larger batches.** 50-100 iterations with 60+ tasks per iteration. Cost scales linearly (~$12-25 for 100 iterations).

3. **Shaped rewards.** Replace binary pass/fail with a multi-component reward: syntax valid (0.1), kondo clean (0.2), namespace loads (0.1), tests pass (0.6). This provides gradient signal even when all rollouts fail.

4. **Curriculum.** Start with easy tasks (high SFT pass rate), then progressively introduce harder tasks.

5. **Best-of-K at inference.** Generate K candidates per task and pick the first passing one. This estimates pass@K and may close the gap with Opus without further training.

6. **Larger base model.** Try Qwen3-30B-A3B (MoE, same training cost on Tinker) or a newer 14B dense model.

## Conclusion

The RLVR training produced a measurable improvement over SFT (+3.6% absolute, +9.5% relative) but did not achieve the target of beating Opus 4.7. The result is consistent with the literature: RLVR helps most when the base model is already competent and the reward signal is dense. For an 8B model on Clojure with binary rewards and a REINFORCE fallback, the improvement is real but modest.

The thesis that "fast feedback loops matter more than model scale" is partially supported: SFT + RLVR moved an 8B model from ~0% (base) to 41.4%, a dramatic improvement. But the remaining gap to frontier models (22.6% to GPT-5.4) likely requires either better RL methodology (proper GRPO, shaped rewards, more iterations) or more model capacity.
