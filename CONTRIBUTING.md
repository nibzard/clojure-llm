# Contributing to clojure-llm

Contributions are welcome. This document covers the basics.

## Reporting issues

Open a [GitHub issue](https://github.com/nibzard/clojure-llm/issues) with:

- A clear description of the problem or suggestion
- Steps to reproduce (for bugs)
- Expected vs. actual behavior
- Relevant environment details (Clojure version, Python version, OS)

## Submitting changes

1. Fork the repository.
2. Create a branch from `master`.
3. Make your changes with clear, focused commits.
4. Open a pull request against `master`.

PRs should:

- Address a single concern (one feature, one fix, one refactor).
- Include any necessary updates to existing documentation.
- Not modify evaluation results or benchmark data without discussion.

## Development setup

**Clojure** (evaluation tooling):

```bash
# Requires Clojure 1.12+ and the Clojure CLI tools
clojure -M:test    # run tests
```

**Python** (generation and training scripts):

```bash
pip install -r requirements.txt
```

API credentials go in a `.env` file at the project root (never committed).

## Code conventions

- **Clojure**: Standard Clojure style. `kebab-case` for functions and
  variables. EDN for all data/configuration files.
- **Python**: Follow PEP 8. Functions are `snake_case`.
- **Data**: All results and manifests are EDN. JSON is acceptable for
  research artifact export.
- **Commits**: Imperative mood, concise subject lines.

## Testing

Clojure tests live in `test/`. Run them with:

```bash
clojure -M:test
```

There is no Python test suite. Python scripts are runners and should be
tested manually or via dry-run flags where available.
