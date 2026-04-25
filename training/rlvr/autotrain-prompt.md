# RLVR Experiment — One Iteration

You are running ONE iteration of an autonomous RLVR hyperparameter search on Qwen3-8B.
After this iteration completes, the loop will re-feed this same prompt. You will see
your previous work in the files and git history.

## What to do (in order)

### 1. Check state

```bash
cat training/rlvr/results.tsv 2>/dev/null || echo "NO RESULTS YET"
git log --oneline -5
git branch --show-current
```

- If `results.tsv` doesn't exist, this is the first iteration — create it with the header:
  ```
  commit	pass_at_1	pass_count	total	hours	status	description
  ```
- If it exists, read it to understand what's been tried and what worked/failed.
- Count completed experiments (non-header lines). If **>= 20**, output
  `<promise>BUDGET REACHED</promise>` and stop.

### 2. Pick the next experiment

Based on results so far, form a hypothesis and decide what to change in the config.
Read `training/rlvr/program.md` for the search space, priorities, and hypotheses.

Rules:
- If this is the **first experiment**, run the current config as-is to establish a baseline.
  Do NOT change anything. Record the result.
- If the **last experiment was a crash**, investigate the crash log (`tail -100 training/rlvr/run.log`),
  fix if trivial (wrong YAML syntax, etc.), otherwise move to a different hypothesis.
- If the **last experiment was discarded**, the config should already be reverted.
  Pick a different direction.
- If the **last experiment was kept**, build on it — try a related variation or move to
  the next priority parameter.

### 3. Edit the config

Edit `training/rlvr/config.yaml`. Only modify the tunable parameters (rollouts, training,
reward_weights, etc.). NEVER change `model`, `sft_checkpoint`, `training_tasks`, `heldout_tasks`,
or `benchmark_tasks`.

### 4. Commit

```bash
git add training/rlvr/config.yaml
git commit -m "experiment: <short description>"
```

### 5. Run training

```bash
python3 training/rlvr/train.py --config training/rlvr/config.yaml > training/rlvr/run.log 2>&1
```

This takes ~1 hour. Run it in the background and wait for completion.

If training crashes (NaN loss, OOM, API error), check the log:
```bash
tail -50 training/rlvr/run.log
```

### 6. Evaluate on held-out tasks

Extract the final checkpoint URI from the training log:
```bash
grep "Sampling client ready\|save_weights" training/rlvr/run.log | tail -3
```

Then evaluate:
```bash
python3 scripts/best_of_k.py \
  --K 1 \
  --temperature 0.2 \
  --checkpoint <checkpoint-uri> \
  --output research/autotrain/results-$(git rev-parse --short HEAD).json
```

Read the pass@1 result from the output file.

### 7. Record results

Append one line to `training/rlvr/results.tsv`:
```
<short-hash>\t<pass_at_1_fraction>\t<pass_count>\t111\t<hours>\t<status>\t<description>
```

- `pass_at_1`: fraction like 0.486
- `pass_count`: integer like 54
- `hours`: training wall-clock time (from log), e.g. 1.2
- `status`: `keep`, `discard`, or `crash`
- `description`: what you changed

### 8. Decide: keep or discard

- If pass@1 **improved** over previous best: **keep**. Leave the commit.
- If pass@1 is **equal or worse**: **discard**. Revert:
  ```bash
  git reset --soft HEAD~1
  git checkout HEAD -- training/rlvr/config.yaml
  ```
- If it **crashed**: log as crash, revert the commit, move on.

### 9. Exit

You're done with this iteration. The loop will re-feed this prompt.
State persists in results.tsv, config.yaml, and git history.
