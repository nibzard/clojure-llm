# Benchmark Spec: `clj-bench` v0

## Purpose

`clj-bench` is the benchmark contract for the benchmark-first plan in [PLAN.md](./PLAN.md).

Version `v0` is intentionally narrow:

- function-level generation first;
- pinned task metadata;
- reproducible task inventory;
- explicit run/result schema;
- containerized execution contract;
- no model-specific implementation details baked into the task format.

The benchmark exists to support the autoresearch loop:

1. freeze the evaluator contract;
2. vary models, prompts, and agent policies against that contract;
3. keep or discard changes based on benchmark deltas.

## Scope

### Included in v0

- MultiPL-E `humaneval-clj`
- MultiPL-E `mbpp-clj`
- metadata for future repo-repair tasks
- task inventory validation
- run-plan generation

### Deferred

- automatic dataset ingestion
- model execution
- container orchestration
- repo repair execution
- constrained decoding integration
- public leaderboard generation

## Task model

Each benchmark task is a single EDN map.

Required keys:

- `:id`
- `:track`
- `:source`
- `:lang`
- `:prompt-ref`
- `:tests-ref`
- `:entrypoint`
- `:difficulty`
- `:tags`
- `:runner`
- `:license`
- `:status`

### Field definitions

` :id`
- globally unique task id
- format: short kebab-case string
- examples: `humaneval-clj-001`, `mbpp-clj-042`

`:track`
- one of `:function`, `:repair`, `:repl`

`:source`
- benchmark provenance
- examples: `:multipl-e/humaneval-clj`, `:multipl-e/mbpp-clj`, `:repo-curated`

`:lang`
- currently always `:clojure`

`:prompt-ref`
- pointer to the prompt source, not necessarily the inline prompt text
- examples:
  - `{:kind :multipl-e :config "humaneval-clj" :name "HumanEval_0_has_close_elements"}`
  - `{:kind :file :path "benchmark/prompts/foo.md"}`

`:tests-ref`
- pointer to executable tests
- examples:
  - `{:kind :multipl-e :config "humaneval-clj" :name "HumanEval_0_has_close_elements"}`
  - `{:kind :file :path "benchmark/tests/foo_test.clj"}`

`:entrypoint`
- symbol name the model must define
- examples: `has-close-elements`, `sort-numbers`

`:difficulty`
- one of `:easy`, `:medium`, `:hard`, `:unknown`

`:tags`
- set of topic tags
- examples: `#{:seqs :maps :strings :recursion :higher-order}`

`:runner`
- executor contract
- function track examples:
  - `{:kind :clojure-test :timeout-sec 10 :memory-mb 1024}`
- repair track examples:
  - `{:kind :repo-repair :timeout-sec 60 :memory-mb 2048}`

`:license`
- provenance and release disposition
- examples:
  - `{:kind :dataset :name "MultiPL-E" :spdx "MIT" :redistributable true}`
  - `{:kind :repo :spdx "EPL-2.0" :redistributable true}`

`:status`
- one of `:active`, `:draft`, `:internal-only`, `:excluded`

## Run model

A benchmark run is a single EDN map stored under `benchmark/runs/`.

Required keys:

- `:run-id`
- `:benchmark-version`
- `:created-at`
- `:model`
- `:prompting`
- `:policy`
- `:tasks-file`
- `:task-ids`
- `:executor`

### Run fields

`:run-id`
- unique id, e.g. `2026-04-16-qwen25coder7b-base-direct-v0`

`:benchmark-version`
- current benchmark contract version, starting at `:clj-bench/v0`

`:model`
- exact model identity
- examples:
  - `{:provider :hf :id "Qwen/Qwen2.5-Coder-7B"}`
  - `{:provider :openai :id "gpt-5.4"}`

`:prompting`
- prompt template and sampling settings
- examples:
  - `{:template :direct-v1 :temperature 0.2 :top-p 0.95 :samples 20}`

`:policy`
- high-level mode under test
- examples:
  - `{:kind :direct}`
  - `{:kind :agent :retries 3 :clj-kondo true :repl false}`
  - `{:kind :agent :retries 3 :clj-kondo true :repl true :grammar :subset-v1}`

`:executor`
- execution boundary
- example:
  - `{:kind :container :image "clj-bench/eval:dev" :network :none}`

## Result model

Each task result is a single EDN map. Aggregate runs can later be derived from these records.

Required keys:

- `:task-id`
- `:outcome`
- `:syntax-ok`
- `:kondo-ok`
- `:tests-ok`
- `:wall-ms`
- `:tokens`

Optional keys:

- `:error-kind`
- `:attempts`
- `:patch-bytes`
- `:notes`

### Outcome vocabulary

- `:pass`
- `:fail`
- `:timeout`
- `:crash`
- `:invalid-output`
- `:sandbox-error`

### Error vocabulary

- `:read-error`
- `:compile-error`
- `:runtime-error`
- `:test-failure`
- `:oom`
- `:unknown`

## Metrics derived from results

Primary:

- pass@1
- pass@k

Secondary:

- syntax success rate
- `clj-kondo` clean rate
- mean wall-clock to first pass
- mean tokens to first pass
- error distribution by category

## Execution contract

The runner interface is intentionally simple:

1. materialize candidate code;
2. run optional static checks;
3. run tests in an isolated environment;
4. emit one result map per task.

The execution boundary for v0 is container-first:

- no network;
- ephemeral writable workspace;
- readonly benchmark assets;
- cpu/memory/wall limits;
- no secrets.

## Repository layout

`benchmark/tasks-v0.edn`
- source-controlled task inventory

`benchmark/runs/`
- generated run manifests

`benchmark/results/`
- generated per-run results

`src/learn/benchmark.clj`
- validation, inventory, and run-plan CLI

## Initial workflow

1. keep `benchmark/tasks-v0.edn` small and explicit;
2. validate with `clojure -M:bench check`;
3. inspect inventory with `clojure -M:bench stats`;
4. generate a run manifest with `clojure -M:bench plan-run ...`;
5. build the real executor against the generated run manifest instead of inventing a second schema later.

## Immediate next work

1. ingest exact MultiPL-E task ids for the v0 function track
2. add executor code that consumes run manifests and writes result maps
3. add aggregation for pass@k and failure-category summaries
4. add repo-repair task schema once the function track is stable
