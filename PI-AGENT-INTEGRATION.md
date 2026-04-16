# Pi Agent Integration Notes

This document summarizes how `pi-mono` can be used in this project across research, benchmarking, synth-data generation, optimization loops, project building, and later product consumption.

Repo explored:

- `https://github.com/badlogic/pi-mono`
- local clone: `/Users/nikola/dev/tmp/pi-mono`

## Short answer

`pi-mono` is worth using here, but mostly as:

- an agent runtime,
- a trajectory capture layer,
- a headless worker via JSON/RPC,
- a fast path to an early product shell.

It should **not** be the source of truth for:

- benchmark scoring,
- Clojure verification,
- sandbox isolation,
- training infrastructure.

The benchmark and verifier should remain owned by this repo. `pi` should be one of the systems evaluated by that benchmark, and later a shell around that benchmark/verifier stack.

## Relevant `pi-mono` capabilities

The most relevant capabilities in `pi-mono` are:

- coding-agent CLI
- SDK embedding
- JSON mode for event streaming
- RPC mode for process integration
- extensions for custom tools and event interception
- skills and prompt templates
- project-local customization via `.pi/`
- JSONL session persistence and export
- subagent workflows via subprocesses

Useful source references:

- `README.md`
- `packages/coding-agent/README.md`
- `packages/coding-agent/docs/extensions.md`
- `packages/coding-agent/docs/json.md`
- `packages/coding-agent/docs/rpc.md`
- `packages/coding-agent/examples/sdk/README.md`
- `packages/coding-agent/examples/extensions/subagent/README.md`
- `packages/coding-agent/examples/extensions/sandbox/index.ts`
- `packages/coding-agent/src/core/agent-session.ts`
- `packages/agent/README.md`

## What `pi` is good for in this project

## 1. `pi` as an agent baseline under test

This is the cleanest immediate use.

Your benchmark should compare:

- direct generation
- `pi` with stock tools
- `pi` with Clojure-specific verifier tools
- later, your own specialized product agent

That gives you a serious agentic baseline before you build a custom runtime.

Why this matters:

- you want to know whether verifier-backed agency helps before you spend on training;
- `pi` already has mature tool execution, sessions, prompts, and event streaming;
- you can treat it as a controlled policy/runtime combination in experiments.

## 2. `pi` as a trajectory capture layer

This is one of the highest-value uses.

`pi` stores sessions as JSONL and explicitly supports exporting session traces. That makes it useful for collecting:

- benchmark attempt traces,
- repo-repair trajectories,
- synth-data generation runs,
- verifier-loop interactions,
- tool-use failures and recoveries.

These traces can later be used for:

- SFT data,
- failure analysis,
- tool-use evaluation,
- prompt ablations,
- compaction and replanning studies.

This fits the benchmark-first plan well:

- benchmark results remain the evaluator truth;
- raw `pi` session traces become a secondary research asset.

## 3. `pi` as the early product shell

This is likely the fastest path to a usable product.

Instead of building a custom TUI or IDE integration immediately, this repo can ship a project-local `.pi/` configuration consisting of:

- extensions,
- skills,
- prompt templates,
- sandbox settings,
- maybe project-local agents.

That means the first user-facing Clojure agent can simply be:

- `pi`
- plus your Clojure-specific tools
- plus your benchmark-informed prompts
- plus your safety and verification hooks

That gets you to a usable product much faster than building UI/runtime from scratch.

## 4. `pi` as a workflow engine for research support

This is useful, but secondary to the roles above.

The subagent example shows that `pi` can spawn separate `pi` subprocesses with isolated context windows.

That makes it useful for:

- benchmark curation,
- repo scouting,
- corpus triage,
- task creation,
- review workflows,
- repair-and-review chains.

This is especially useful for open-ended support work around the benchmark, but it should not become the core benchmark runner.

## 5. `pi` as a build assistant for this repo

This is fine operationally but not uniquely important.

You can use `pi` to help:

- create manifests,
- inspect repos,
- summarize benchmark failures,
- propose repairs,
- drive internal workflows.

But that is a convenience benefit. The more important value is runtime reuse and trace capture.

## What `pi` should not own

## 1. Not the benchmark evaluator

The benchmark contract must remain in this repo.

This repo should own:

- task inventory,
- prompt contract,
- verifier,
- run manifest schema,
- result schema,
- aggregation and scoring.

`pi` should be an evaluated system, not the judge.

## 2. Not the Clojure sandbox

`pi` has sandbox examples and extension-level gating, but your Clojure evaluator needs a stronger execution boundary.

Keep the actual evaluator boundary separate and explicit:

- isolated containers,
- no network,
- readonly inputs,
- ephemeral writable workspace,
- wall-clock and memory caps,
- deterministic `clj-kondo` and `clojure.test` execution.

That boundary should not depend on `pi` internals.

## 3. Not the training backend

For:

- SFT,
- RLVR,
- continued pretraining,
- reward shaping,
- dataset materialization,

you should use your own training/data infrastructure.

`pi` can supply:

- trajectories,
- agent policies,
- task attempts,

but it should not become the center of the training stack.

## Where `pi` fits in the architecture

Recommended architecture:

1. `clj-bench` remains independent and repo-owned.
2. `pi` consumes benchmark manifests and emits traces/results.
3. the Clojure verifier and executor remain external and authoritative.
4. `pi` becomes the first real agent runtime benchmarked in this repo.
5. later, `pi` can also become the first product shell.

In practical terms:

- benchmark manifest in this repo,
- run launched against `pi`,
- tool calls routed into your verifier/executor,
- benchmark result written by your harness,
- session JSONL saved separately.

## Recommended integration points

## 1. Project-local `pi` package

Add a local `.pi/` setup later containing:

- `.pi/extensions/clj-verifier.ts`
- `.pi/extensions/benchmark-runner.ts`
- `.pi/extensions/synth-data.ts`
- `.pi/extensions/repo-repair.ts`
- `.pi/extensions/safety.ts`
- `.pi/skills/`
- `.pi/prompts/`

This makes `pi` immediately useful as a Clojure-aware shell without modifying upstream `pi-mono`.

## 2. Clojure-specific custom tools

The first tools should be narrow and structured, not chatty.

Recommended tools:

- `clj_kondo_check`
- `clj_eval_form`
- `clj_run_tests`
- `clj_repo_repair_check`
- `benchmark_get_task`
- `benchmark_record_result`

These should return structured outputs that are easy to score and easy to convert into training data.

## 3. Headless execution via JSON or RPC

Two useful modes:

- JSON mode for passive event capture
- RPC mode for active orchestration

RPC mode is the better fit for benchmark automation because it lets an outer program:

- submit prompts,
- steer/follow up,
- abort,
- inspect state,
- manage sessions.

This makes `pi` a controllable worker rather than just an interactive tool.

## 4. Store benchmark results and `pi` sessions separately

For each run you want two artifacts:

- benchmark result record,
- raw `pi` session JSONL trace.

This separation matters:

- benchmark record = evaluator truth
- session trace = auxiliary research/training data

Do not collapse them into one file format.

## Role-by-role analysis

## A. Building synth datasets

This is a strong fit.

Use `pi` as a teacher-agent runtime to generate:

- candidate function implementations,
- candidate tests,
- repair attempts,
- solution explanations,
- verifier-guided retries.

Then run everything through your verifier:

- parse/load
- `clj-kondo`
- unit tests
- maybe containerized repo checks

Only keep verifier-clean outputs.

Why `pi` helps here:

- easy tool integration,
- session capture,
- steer/follow-up support,
- custom prompts/skills/extensions,
- can run with different providers/models.

What not to do:

- do not trust raw `pi` outputs as dataset rows without verifier filtering.

## B. Running benchmarks

This is a good fit if done carefully.

Use `pi` as one benchmarked agent policy/runtime. The outer benchmark harness should:

- choose task,
- generate a run manifest,
- launch `pi` headlessly,
- feed the task prompt,
- collect events and session trace,
- run your verifier,
- write benchmark results.

This gives you:

- a reproducible agent baseline,
- trajectory capture,
- support for multiple prompt/tool policies,
- future compatibility with product settings.

But the benchmark harness must remain separate from `pi`.

## C. Optimization loops

This is useful mainly as an inner worker, not the outer loop controller.

Recommended shape:

- outer Python/Clojure loop owns sweeps and scoring;
- `pi` is launched in RPC mode per run;
- run parameters vary:
  - model,
  - prompt template,
  - tools,
  - retries,
  - constrained-decoding availability,
  - verifier strategy.

Then the outer loop records:

- pass/fail,
- syntax success,
- `clj-kondo` cleanliness,
- cost,
- tokens,
- wall time,
- trace file.

This gives you systematic policy sweeps while reusing `pi` as a robust agent runtime.

## D. Building the project itself

This is a moderate fit.

You can use `pi` to help construct:

- benchmark inventories,
- task curation,
- repo scouting,
- repair attempts,
- integration glue.

This is useful but not central. The more strategic benefit is standardizing how you run and capture agent behavior.

## E. Later use of the project as a consumer

This is a strong fit.

Once the benchmark and verifier stack exist, the first real product could be:

- `pi` as the shell,
- this repo’s verifier/executor tools underneath,
- benchmark-informed prompts and skills,
- optional project-local sandbox rules.

This gives you:

- fast path to usability,
- real user sessions,
- direct comparability between benchmarked agent and user-facing product.

That last point is important: the closer the product shell is to the benchmarked agent runtime, the easier it is to learn from real usage without rewriting everything.

## Best concrete roles for `pi`

Recommended uses:

- agent baseline
- headless benchmark worker
- trajectory/data capture layer
- synth-data generation worker
- repo-scouting and review subagent runtime
- early product shell

Avoid centering `pi` as:

- benchmark truth
- sandbox root of trust
- training backend

## Recommended first integration

The first integration should be thin and high-signal.

### Step 1: build one extension: `clj-verifier`

This extension should expose only a few structured tools:

- lint
- eval-form
- run-tests
- benchmark-get-task

No fancy UI needed at first.

### Step 2: add a headless runner

Use your benchmark manifests from this repo and run `pi` in RPC mode.

Per run:

- load run manifest,
- launch `pi --mode rpc`,
- send task prompt,
- collect event stream,
- save session JSONL,
- verify output,
- write benchmark result.

### Step 3: test on one slice only

Start with:

- `humaneval-clj`

Compare:

- direct generation
- `pi` stock tools
- `pi` + `clj-verifier`

That gives immediate signal without overbuilding.

## Concrete `.pi/` layout to aim for later

```text
.pi/
├── extensions/
│   ├── clj-verifier.ts
│   ├── benchmark-runner.ts
│   ├── synth-data.ts
│   ├── repo-repair.ts
│   └── safety.ts
├── skills/
│   ├── benchmark-curator/
│   │   └── SKILL.md
│   ├── synth-task-writer/
│   │   └── SKILL.md
│   └── repo-repair-review/
│       └── SKILL.md
├── prompts/
│   ├── benchmark-direct.md
│   ├── benchmark-agent.md
│   ├── synth-solution.md
│   └── repair-loop.md
└── sandbox.json
```

## Final recommendation

Use `pi-mono`, but use it in the right place.

The right stance is:

- benchmark and verifier remain authoritative and repo-owned;
- `pi` becomes the first serious agent runtime you evaluate;
- `pi` also becomes the easiest way to collect traces and ship an early product shell.

That gives you leverage in three directions at once:

- research velocity,
- reusable runtime,
- future product path.

It does not justify outsourcing benchmark logic or sandbox trust to `pi`.

## Recommended next steps

1. Design the `clj-verifier` extension and tool schemas.
2. Build a minimal outer runner that maps benchmark run manifests to `pi --mode rpc`.
3. Save both benchmark result records and raw `pi` session traces.
4. Benchmark `pi` on `humaneval-clj` before building more infrastructure.
