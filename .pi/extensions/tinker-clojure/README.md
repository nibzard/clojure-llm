# tinker-clojure

This Pi extension runs a Python sidecar for verified Clojure code generation.
The sidecar needs its own Python environment with `tinker` and `transformers`.

## Bootstrap

From this directory:

```bash
uv sync
```

That creates `.venv/` next to the extension. The Pi extension will prefer:

1. `TINKER_CLOJURE_PYTHON` if set
2. repo-local `.venv/bin/python`
3. extension-local `.venv/bin/python`
4. fallback `python3`

## Manual verification

```bash
.venv/bin/python -c "import tinker, transformers; print('ok')"
```

## Notes

- `.env` loading is best-effort. If `python-dotenv` is missing, the sidecar can
  still read the repo `.env` directly.
- Keep the sidecar environment separate from the rest of the repo unless you
  intentionally want to standardize all Python tooling.
