# autoresearch — Principles & Implementation Guide

A distilled reference for reproducing Andrej Karpathy's `autoresearch` pattern in any domain.
Source repo: https://github.com/karpathy/autoresearch (commit fetched 2026-04-16).

> "Give an AI agent a small but real LLM training setup and let it experiment autonomously
> overnight. It modifies the code, trains for 5 minutes, checks if the result improved,
> keeps or discards, and repeats."
> — `README.md` line 7

---

## 1. The core idea

`autoresearch` is **not** a library. It is a *pattern* for structuring a repo so that a
coding agent (Claude Code, Codex, etc.) can run an unsupervised loop of:

```
edit → commit → run → measure → keep-or-revert → repeat
```

The human's job shifts from *editing code* to *editing the agent's instructions*
(`program.md`). The agent's job is to explore the search space of code changes that
improve a single numeric metric under a fixed compute budget.

```
Human          program.md   ←── iterated on by human (the "org chart")
  │
  ▼
Agent          train.py     ←── iterated on by agent (the experiment)
  │
  ▼
Harness        prepare.py   ←── frozen (data, metric, time budget)
```

Reference: `README.md` lines 11–17.

---

## 2. The five invariants

These are the design choices that make the loop work. If you drop one, the agent's
behavior degrades (runs become incomparable, scope creeps, or the loop stalls).

### 2.1 One file to edit

The agent only modifies **one** file (`train.py`). Everything else — data prep,
tokenizer, evaluation harness, constants — is read-only.

> "The agent only touches `train.py`. This keeps the scope manageable and diffs reviewable."
> — `README.md` line 63

**Why it matters:** small diffs are reviewable by a human in the morning, and the agent
can't accidentally "fix" the scoring function to make its numbers look better.

**How to apply:** in your domain, pick the *one* artifact that encodes the hypothesis
(a model file, a prompt, a SQL query, a strategy config). Everything upstream
(inputs) and downstream (evaluation) is frozen.

### 2.2 Fixed time budget, not fixed work

Each experiment runs for exactly **5 minutes of wall-clock training time** regardless of
hardware, model size, or batch size.

> "Training always runs for exactly 5 minutes, regardless of your specific platform.
> This means you can expect approx 12 experiments/hour and approx 100 experiments
> while you sleep."
> — `README.md` line 64

Set in `prepare.py` line 31:
```python
TIME_BUDGET = 300        # training time budget in seconds (5 minutes)
```

**Why it matters:** the agent can change architecture, batch size, depth, optimizer —
all of which would normally change run length. Pinning wall-clock time makes *every*
experiment directly comparable and guarantees throughput (~100 experiments/night).

**Trade-off:** your numbers are not comparable to *other people's* runs on other
hardware. That's fine — you're optimizing for *your* platform.

### 2.3 One metric, vocab/config-independent

The score is `val_bpb` — validation **bits per byte**, not loss or perplexity.

```python
return total_nats / (math.log(2) * total_bytes)
```
— `prepare.py` line 365

**Why bits-per-byte and not cross-entropy loss?** Cross-entropy depends on vocabulary
size. If the agent shrinks `vocab_size` from 8192 to 4096, loss drops mechanically,
not from any real improvement. BPB normalizes by *byte count of the target text*, so
tokenizer changes don't move the number.

**How to apply:** pick a metric that is invariant to the things the agent is allowed
to change. If the agent can change model size, don't score on "loss per parameter."
If it can change batch size, don't score on "loss per step."

### 2.4 Keep-or-revert on a single branch

Each experiment lives on a dedicated branch (`autoresearch/<tag>`). After each run:
- If `val_bpb` improved → keep the commit, branch advances.
- If equal or worse → `git reset` back to the previous commit.

> "If val_bpb improved (lower), you 'advance' the branch, keeping the git commit.
> If val_bpb is equal or worse, you git reset back to where you started."
> — `program.md` lines 103–104

**Why it matters:** the branch itself becomes the record of *kept* progress. The
`results.tsv` (untracked) records *every* attempt including discards. Together they
give you a monotonic improvement chain + an exploration log.

### 2.5 Simplicity tie-breaker

When an experiment neither clearly wins nor loses, code simplicity breaks the tie.

> "A 0.001 val_bpb improvement that adds 20 lines of hacky code? Probably not worth it.
> A 0.001 val_bpb improvement from deleting code? Definitely keep. An improvement of
> ~0 but much simpler code? Keep."
> — `program.md` line 37

**Why it matters:** without this, the code monotonically accumulates hacks that each
bought 0.001 BPB and the codebase eventually becomes unreadable/unforkable. With it,
the codebase stays tractable over hundreds of experiments.

---

## 3. The loop protocol

Verbatim from `program.md` lines 94–105:

```
LOOP FOREVER:
  1. Look at the git state: current branch/commit
  2. Tune train.py with an experimental idea by directly hacking the code
  3. git commit
  4. Run: uv run train.py > run.log 2>&1
     (redirect everything — do NOT use tee, it floods agent context)
  5. grep "^val_bpb:\|^peak_vram_mb:" run.log
  6. If grep output is empty → run crashed. tail -n 50 run.log, attempt fix.
  7. Record results in results.tsv (untracked)
  8. If val_bpb improved → keep commit, advance branch
  9. Else → git reset to previous commit
```

Two operational rules worth highlighting:

**Redirect, don't tee** (step 4). The agent's context window is finite. A 5-minute
training run prints thousands of lines. `tee` floods the agent's context and the loop
grinds to a halt after a few iterations. Always redirect to a file, then `grep` only
the summary lines.

**Never stop** (`program.md` line 112):
> "Once the experiment loop has begun, do NOT pause to ask the human if you should
> continue. The human might be asleep. You are autonomous. If you run out of ideas,
> think harder — read papers referenced in the code, re-read the in-scope files for
> new angles, try combining previous near-misses, try more radical architectural
> changes. The loop runs until the human interrupts you, period."

Without this explicit override, most agents default to "should I continue?" after every
iteration. Overnight throughput collapses.

---

## 4. The result log format

`results.tsv` — tab-separated (comma breaks on free-text descriptions):

```
commit   val_bpb    memory_gb  status    description
a1b2c3d  0.997900   44.0       keep      baseline
b2c3d4e  0.993200   44.2       keep      increase LR to 0.04
c3d4e5f  1.005000   44.0       discard   switch to GeLU activation
d4e5f6g  0.000000   0.0        crash     double model width (OOM)
```

Three statuses: `keep`, `discard`, `crash`. Crashes get `val_bpb=0.000000, memory_gb=0.0`.
File is **untracked** — don't commit it, so branches stay clean and multiple parallel
agents don't collide on it.

Reference: `program.md` lines 66–88.

---

## 5. Structure your repo this way

Minimum viable autoresearch repo:

```
your-project/
├── pyproject.toml        # dependencies (frozen during loop)
├── prepare.py            # data, metric, constants (FROZEN)
├── train.py              # the thing under experimentation (agent edits)
├── program.md            # agent's instructions (human edits)
└── README.md             # human context
```

Sizes from the real repo (for calibration):
- `prepare.py` — 390 lines
- `train.py` — ~700 lines
- `program.md` — 115 lines
- `README.md` — 93 lines

Entire "research org" fits in under ~1500 lines. That's deliberate — the agent reads
the whole repo at the start of each session (`program.md` line 11: "The repo is small.
Read these files for full context").

---

## 6. Adapting the pattern to non-ML domains

The pattern generalizes. The invariants stay the same; the nouns change.

| autoresearch (pretraining)   | Prompt optimization           | Trading strategy             | SQL query tuning              |
| ---------------------------- | ----------------------------- | ---------------------------- | ----------------------------- |
| `train.py`                   | `prompt.md`                   | `strategy.py`                | `query.sql`                   |
| `prepare.py` (data, BPB)     | eval harness (benchmark set)  | backtest engine + data       | fixed dataset + EXPLAIN plan  |
| `val_bpb` (lower=better)     | benchmark accuracy            | Sharpe over backtest window  | wall-clock runtime            |
| 5-min training               | 5-min eval over N examples    | fixed backtest window        | fixed query + fixed dataset   |
| VRAM soft cap                | tokens-per-request cap        | max drawdown cap             | memory / row-scan cap         |

Checklist when adapting:
1. **Can you pick one file the agent edits?** If not, split the repo.
2. **Is your metric invariant to what the agent can change?** If the agent can change
   the eval set, it will.
3. **Can you cap cost per experiment?** Time, tokens, dollars — pick one and enforce it.
4. **Do you have a keep/discard criterion that's a single number?** Multi-objective
   defeats the autonomous loop (the agent has to ask you which dimension wins).
5. **Is there a simplicity tie-breaker written down?** Without it, code rots over N
   iterations.

---

## 7. Gotchas from the real repo

**VRAM is a soft constraint, not a hard one.** From `program.md` line 35:
> "Some increase is acceptable for meaningful val_bpb gains, but it should not blow
> up dramatically."

The agent needs qualitative guidance here because OOMs become the dominant failure
mode once the agent tries to scale the model.

**First run is always the baseline.** From `program.md` line 39. No edits, just
`uv run train.py` as-is. This gives the first row of `results.tsv` and confirms the
harness works before any hypothesis is tested.

**Timeout = 2× time budget.** From `program.md` line 108: "If a run exceeds 10 minutes,
kill it and treat it as a failure." Some bad changes silently slow training without
crashing — the timeout catches them.

**Multiple agents on multiple GPUs work** — just tag branches distinctly
(`autoresearch/mar5-gpu0`, `autoresearch/mar5-gpu1`). Each has its own `results.tsv`
because the file is untracked.

---

## 8. The minimum program.md

If you port this pattern, your `program.md` needs these sections (mirroring the
original, `program.md` lines 1–115):

1. **Setup** — branch naming, files to read, data preconditions, how to initialize
   the results log.
2. **Experimentation** — what the agent *can* change, what it *cannot* change, and
   the single metric.
3. **Output format** — the exact stdout line(s) the agent will grep for. This must
   be stable and machine-readable.
4. **Logging** — the TSV schema and status vocabulary.
5. **Loop** — the numbered steps, verbatim.
6. **Never-stop clause** — explicit override of the agent's "check in with user"
   default.
7. **Crash policy** — when to fix vs. when to log and move on.
8. **Timeout policy** — when a run is considered hung.

Keep it under ~200 lines. The agent re-reads this at the start of every session.

---

## 9. Further reading

- Original repo: https://github.com/karpathy/autoresearch
- Project backstory tweet: https://x.com/karpathy/status/2029701092347630069
- Follow-up tweet: https://x.com/karpathy/status/2031135152349524125
- Parent project (full platform support): https://github.com/karpathy/nanochat
- Dummy's guide to the neural-net side: https://x.com/hooeem/status/2030720614752039185

### Notable forks (non-H100 targets)

- MacOS: https://github.com/miolini/autoresearch-macos
- MacOS (MLX): https://github.com/trevin-creator/autoresearch-mlx
- Windows (RTX): https://github.com/jsegov/autoresearch-win-rtx
- AMD: https://github.com/andyluo7/autoresearch

---

## 10. One-paragraph summary for a skeptic

`autoresearch` is a minimal pattern — one file the agent edits, one file the human
edits, one frozen evaluation harness, one numeric metric, a fixed time budget per
experiment, and a git-based keep/revert loop. The throughput (~100 experiments per
night) only works because the agent never pauses to ask permission, never tees output
into its context window, and never touches the scoring function. If you want an AI
agent to do real research for you overnight, these are the load-bearing constraints —
copy them before you copy anything else.
