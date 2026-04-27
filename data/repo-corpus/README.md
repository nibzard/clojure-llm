# Repo Corpus

This directory holds repo-derived Clojure training data.

The goal is to add a second corpus track alongside MultiPL-E and 4clojure:

- real library code
- real tests
- richer idioms (routing, specs, middleware, macros, interop)
- explicit provenance and license tracking

## Layout

```text
data/repo-corpus/
  repos-v0.json               # allowlist of repositories to mine
  raw/
    <owner>__<repo>/
      repo.json               # metadata for pinned repo snapshot
      vars.jsonl              # extracted vars
      tests.jsonl             # extracted test namespaces/files
      task_candidates.jsonl   # direct SFT candidates
      repair_pairs.jsonl      # repair-style SFT pairs
  derived/
    repo_direct_train.jsonl
    repo_direct_val.jsonl
    repo_repair_train.jsonl
    repo_repair_val.jsonl
```

## Workflow

1. Maintain the allowlist in `repos-v0.json`.
2. Materialize repo metadata and directory scaffolding:

   ```bash
   python3 scripts/scout_repo_corpus.py
   ```

3. Clone repositories under `data/repo-corpus/checkouts/<owner>__<repo>/`:

   ```bash
   python3 scripts/fetch_repo_corpus.py
   ```

   Or copy pre-existing local checkouts there if you do not want the script to
   fetch from GitHub.

4. Extract direct task candidates from local checkouts:

   ```bash
   python3 scripts/extract_repo_tasks.py
   ```

5. Export repo-derived SFT rows:

   ```bash
   python3 scripts/export_repo_sft.py
   ```

6. Rebuild the final SFT train/val files:

   ```bash
   python3 scripts/rebuild_training_data.py
   ```

## Notes

- `track` controls whether a repo is meant for the internal corpus or a
  redistribution-safe release corpus.
- The extractor is intentionally conservative. It only emits simple
  function-level candidates, and only when a var has real contract signal:
  docstring and/or matched tests.
- Raw task candidates include `contract_type` metadata so you can distinguish
  `docstring_contract`, `test_contract`, and `docstring_and_test` rows.
- `test_contract` rows require assertion-level evidence. Bare `deftest` or
  `testing` fragments alone are not enough.
- Final training still reads `data/sft/sft_train.jsonl` and
  `data/sft/sft_val.jsonl`. The repo corpus is an input to that build step,
  not a separate trainer path.
