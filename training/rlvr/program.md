# Autonomous RLVR Experiment Protocol — Qwen3-8B

This file programs an AI agent to run autonomous RLVR hyperparameter experiments on
Qwen3-8B-Base. Inspired by karpathy/autoresearch: the agent modifies a config, trains,
evaluates, and keeps or discards the change.

## Context

We train Qwen3-8B with SFT + RLVR (GRPO with Clojure verifier rewards) on 558
MultiPL-E Clojure tasks (447 train, 111 held-out). The goal is to maximize pass@1
on the 111 held-out tasks.

**Our 8B results so far:**

| Condition | pass@1 (111 held-out) | best-of-16 | Config |
|-----------|----------------------|------------|--------|
| GPT-5.4 | 64.0% (71/111) | — | — |
| RLVR v1 (shaped) | **48.6%** (54/111) | 64.0% (71/111) | syntax=0.1, kondo=0.2, load=0.1, tests=0.6, LR=5e-6, K=8, 10 iters |
| RLVR v0 (binary) | 41.4% (46/111) | 72.1% (80/111) | binary reward, LR=5e-6, K=8, 10 iters |
| SFT | 37.8% (42/111) | 72.1% (80/111) | LoRA r=32, LR=2e-5, 3 epochs |
| Opus 4.7 | 45.0% (50/111) | — | — |

**Key observations from prior runs:**
- Shaped reward (v1) improved pass@1 by +10.8pp over SFT but **lowered the ceiling**
  from 72.1% to 64.0%. The 8B model lost the ability to solve 9 tasks that SFT could.
- Binary reward (v0) matched the SFT ceiling at 72.1% but underperformed on pass@1.
- The tension: shaped reward improves single-pass reliability but narrows the
  solution distribution. We need a reward configuration that gets pass@1 up **without**
  sacrificing the ceiling.

**Target: 50%+ pass@1 (beating v1's 48.6%) while keeping best-of-16 at 72%+.**

## Setup

To set up a new experiment session:

1. **Agree on a run tag**: propose a tag like `apr25-8b` (date + model).
   The branch `autotrain/<tag>` must not already exist.
2. **Create the branch**: `git checkout -b autotrain/<tag>` from current master.
3. **Read the in-scope files**:
   - `CLAUDE.md` — project context, current status, conventions
   - `training/rlvr/config.yaml` — the config you modify (8B config)
   - `training/rlvr/train.py` — training loop (read-only, understand the mechanics)
   - `training/rlvr/evaluate.py` — reward computation and Clojure eval (read-only)
   - `training/rlvr/rewards.py` — GRPO advantage math (read-only)
   - `research/rlvr-results.md` — detailed v0/v1 analysis (read-only)
4. **Verify environment**: Check that `.env` has `TINKER_API_KEY` and `WANDB_API_KEY`.
   Check that `clojure` and `clj-kondo` are on PATH.
5. **Initialize results.tsv**: Create `training/rlvr/results.tsv` with the header row
   (leave it untracked by git — do NOT commit it):
   ```
   commit	pass_at_1	pass_count	total	hours	status	description
   ```
6. **Confirm and go**: Confirm setup looks good, then start experimenting.

## Fixed starting point

Every experiment starts from the same SFT checkpoint:

```
model: Qwen3-8B-Base
sft_checkpoint: tinker://b5c7e66e-618a-5f71-919e-da1db6844679:train:0/weights/checkpoint-step-600
```

Do NOT change the model or SFT checkpoint. Changing it mid-session makes results
incomparable.

## Experimentation

Each experiment runs RLVR training on Tinker cloud infrastructure. With the 8B model
(default 10 iterations, K=8, 30 tasks/iter), training takes **~1 hour**. With more
iterations or larger K, expect 1.5-2 hours.

**What you CAN do:**
- Modify `training/rlvr/config.yaml` — this is the primary file you edit.
  Everything in it is fair game: reward weights, learning rate, temperature,
  rollouts per task, number of iterations, KL penalty, clip range, etc.

**What you CANNOT do:**
- Modify `training/rlvr/train.py`, `training/rlvr/evaluate.py`, or `training/rlvr/rewards.py`.
  These are read-only. They contain the fixed training loop, evaluation harness,
  and reward computation.
- Modify the Clojure evaluation harness (`src/learn/evaluate.clj`).
- Install new packages or change `requirements.txt`.
- Change the SFT starting checkpoint within a session (each session starts from
  a fixed SFT checkpoint — changing it mid-session makes results incomparable).

**The goal: maximize pass@1 on 111 held-out tasks.**

With ~1h per experiment, you can run ~12 experiments in a 12-hour overnight session.

**Cost awareness**: 8B is the cheapest model in our lineup (~1 GPU-hour per experiment).
A 20-experiment session costs ~20 GPU-hours. Make bold, hypothesis-driven changes.
If an idea is clearly bad after thinking about it for 30 seconds, skip it.

**Simplicity criterion**: All else being equal, simpler reward configurations are
better. A reward function with 4 components that matches a 7-component one is
preferable. Removing a reward component and getting equal results is a win. Weight
0.001 improvements from adding complexity are probably not worth it.

## The config file

`training/rlvr/config.yaml` is the single file you modify. Key tunable parameters:

```yaml
# Rollout generation
rollouts:
  rollouts_per_task: 8     # GRPO group size K. 4-16 is reasonable.
  num_iterations: 10       # Total training iterations. 5-20 reasonable.
  tasks_per_batch: 30      # Tasks sampled per iteration. 20-50 reasonable.
  temperature: 0.7         # Sampling temperature. 0.5-1.0 reasonable.
  max_tokens: 512          # Max completion length. 256-1024 reasonable.

# Training
training:
  learning_rate: 5.0e-6    # 1e-6 to 1e-4 reasonable. Higher = faster, less stable.
  weight_decay: 0.01       # 0.0-0.1 reasonable.
  grad_clip_norm: 1.0      # 0.5-5.0 reasonable.

  # Reward shaping — this is the main search space
  reward_mode: shaped      # "shaped" or "binary"
  reward_weights:           # Only used when reward_mode=shaped. Must sum to ~1.0
    syntax: 0.1            # clojure.core/read succeeds
    kondo: 0.2             # clj-kondo lint passes
    load: 0.1              # (load-file) succeeds
    tests: 0.6             # clojure.test passes

  # GRPO-specific
  kl_penalty_coeff: 0.1    # 0.0-1.0. Prevents divergence from SFT policy.
  clip_range: 0.2          # 0.1-0.5. PPO-style clipping.
```

### Search space priorities

Not all parameters are equally impactful. Prioritize in this order:

1. **Reward weights** (highest impact) — The shaped reward is our main innovation.
   Try emphasizing different signals: tests-heavy, kondo-heavy, equal weights,
   binary (tests-only), syntax+tests only, dropping load, etc.
2. **Learning rate** — Too high destabilizes, too low undertrains. Try 1e-6, 5e-6,
   1e-5, 2e-5.
3. **Temperature** — Affects rollout diversity. Low temp = less exploration,
   high temp = more diverse but noisier advantages.
4. **Rollouts per task (K)** — Larger K = better advantage estimates but more
   compute per iteration. K=4, 8, 16.
5. **KL penalty** — Higher = stays closer to SFT policy. Lower = more freedom.
6. **Number of iterations** — More iterations = more training but diminishing
   returns. Watch for overfitting.
7. **Clip range** — PPO-style. Usually stable at 0.2, try 0.1 or 0.3.

### Hypotheses to test

Based on prior 8B results (v0 binary=41.4%, v1 shaped=48.6%, ceiling 72.1%→64.0%):

- **H1: Raise LR to 1e-5.** 5e-6 was conservative. The model has room to move
  faster — SFT used 2e-5 (4x higher). Try 1e-5 with same shaped reward.
- **H2: Tests-heavy reward (tests=0.8, kondo=0.1, syntax=0.1, load=0.0).** The v1
  weights split credit across 4 signals. Tests are what matter most — concentrate
  reward there and see if pass@1 rises while the ceiling holds.
- **H3: Drop load signal entirely (syntax=0.15, kondo=0.25, tests=0.60).** The load
  signal (code loads without error) is nearly redundant with syntax+kondo. Removing
  it may sharpen the reward gradient. Simpler is better.
- **H4: K=4 with 20 iterations instead of K=8 with 10.** Same total samples (2400)
  but more gradient updates on smaller groups. May learn faster from noisier advantages.
- **H5: Temperature 0.5.** Lower temp produces tighter, more conservative rollouts.
  May help the model reinforce its existing correct solutions rather than exploring
  and getting confused.
- **H6: KL penalty 0.0.** The v1 ceiling drop suggests KL regularization may be
  over-constraining. Removing it lets the policy move further from SFT — risky but
  may recover the lost ceiling.
- **H7: Binary reward with LR=1e-5.** v0 used binary at LR=5e-6 and got 41.4%.
  The binary reward preserved the 72.1% ceiling. If a higher LR can get binary
  closer to 48.6% while keeping the ceiling intact, that's the ideal outcome.

## Running an experiment

```bash
# 1. Edit the config
vim training/rlvr/config.yaml  # or edit directly

# 2. Commit the config change (so we can track what changed)
git add training/rlvr/config.yaml
git commit -m "experiment: <short description of what you changed>"

# 3. Run training (redirect output to log file)
python3 training/rlvr/train.py --config training/rlvr/config.yaml > training/rlvr/run.log 2>&1

# 4. Check if training completed successfully
tail -20 training/rlvr/run.log
# Look for "Training complete!" or "FAIL" or stack traces
```

Training takes ~1 hour (8B model, 10 iterations). Do NOT poll the log — let it finish.

## Evaluation (held-out pass@1)

After training completes, evaluate the final checkpoint on 111 held-out tasks:

```bash
# 1. Get the final checkpoint URI from the log
grep "Sampling client ready\|final" training/rlvr/run.log

# 2. Generate candidates and evaluate on held-out tasks
# Option A: Using the best_of_k.py script with K=1 (single-pass evaluation)
python3 scripts/best_of_k.py \
  --K 1 \
  --temperature 0.2 \
  --checkpoint <final-checkpoint-uri> \
  --output research/autotrain/<experiment-tag>.json

# 3. Read the result
grep "pass@1\|pass_count\|total" research/autotrain/<experiment-tag>.json
```

The pass@1 on 111 held-out tasks is the single metric. Higher is better.

For a quick sanity check during training, you can monitor the wandb dashboard at
`https://wandb.ai/nibzard-org/clojure-llm` or check the training summary:

```bash
# After training finishes
cat checkpoints/rlvr/training_summary.json | python3 -m json.tool
```

## Logging results

When an experiment finishes, log it to `training/rlvr/results.tsv`:

```
commit	pass_at_1	pass_count	total	hours	status	description
a1b2c3d	0.486	54	111	4.2	keep	shaped reward v1 baseline
b2c3d4e	0.523	58	111	4.0	keep	increase tests weight to 0.8
c3d4e5f	0.450	50	111	3.8	discard	switch to binary reward
d4e5f6f	0.000	0	111	0.0	crash	LR=1e-4 diverged (NaN loss)
```

Columns:
1. `commit` — short git hash (7 chars)
2. `pass_at_1` — fraction (0.0-1.0), use 0.000 for crashes
3. `pass_count` — integer count of passing tasks (0-111)
4. `total` — always 111
5. `hours` — training wall-clock time in hours, round to 1 decimal
6. `status` — `keep`, `discard`, or `crash`
7. `description` — short text describing what was changed

**Do NOT commit results.tsv.** Leave it untracked. It's a local experiment log.

## The experiment loop

The experiment runs on a dedicated branch (e.g. `autotrain/apr25-8b`).

LOOP FOREVER:

1. **Check git state**: which branch/commit are we on
2. **Form a hypothesis**: based on prior results, decide what to try next. Read
   `training/rlvr/results.tsv` to see what's been tried and what worked/failed.
3. **Edit the config**: modify `training/rlvr/config.yaml` with your experimental idea
4. **Git commit**: save the config change
5. **Run training**: `python3 training/rlvr/train.py --config training/rlvr/config.yaml > training/rlvr/run.log 2>&1`
6. **Check for crashes**: `tail -50 training/rlvr/run.log` — if it crashed, log it,
   `git reset --soft HEAD~1` to undo the commit, fix, and retry (max 3 retries)
7. **Evaluate**: run held-out eval with the final checkpoint
8. **Record results**: append to `training/rlvr/results.tsv`
9. **Decide**:
   - If pass@1 **improved** (higher than previous best): keep the commit, advance the branch
   - If pass@1 **equal or worse**: `git reset --soft HEAD~1` to undo, revert config
     to previous best (`git checkout HEAD -- training/rlvr/config.yaml`)
   - If **crashed**: log as crash, `git reset --soft HEAD~1`, move on

## Budget and safety

**Training budget**: Each session has a budget of ~20 experiments (~20 GPU-hours
total with 8B). After 20 experiments, pause and present the findings to the human.

**Crash budget**: If 3 consecutive experiments crash, stop and investigate. Don't
blindly retry — understand the failure mode first.

**Irreversibility guard**: Never `git push --force`, never `git reset --hard`
(unlike autoresearch, we use `--soft` to preserve working tree changes), never
delete checkpoint directories, never modify files marked read-only above.

## What to do when stuck

If you've run 5+ experiments with no improvement:

1. **Re-read results.tsv**: Look for patterns. Are discards clustering around
   a certain type of change?
2. **Try orthogonal directions**: If you've been tuning reward weights, switch to
   learning rate or temperature. If you've been tuning LR, try reward weights.
3. **Review the literature**: Read `research/literature-review.md` and
   `research/rlvr-results.md` for insights from prior runs.
4. **Try radical changes**: Instead of incremental tweaks, try something very
   different: binary reward, K=4, temperature 0.5, LR=2e-5, zero KL penalty.
5. **Present findings**: Summarize what worked and what didn't. The human may
   have ideas based on the pattern of results.

## NEVER STOP prematurely

Once the experiment loop has begun, do NOT pause to ask "should I continue?" after
each experiment. Run the full budget of experiments (up to 20), then present all
results at once. The human may be asleep or away.

Exceptions where you SHOULD stop and notify:
- 3 consecutive crashes
- Training costs are unexpectedly high (check Tinker dashboard)
- You discover a bug in the evaluation harness or training code
- The human explicitly asks you to stop
