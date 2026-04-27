#!/usr/bin/env python3
"""Create repo-corpus scaffolding from the allowlist manifest.

This script does not clone repositories. It validates the allowlist,
creates directory scaffolding, and writes per-repo metadata placeholders
under `data/repo-corpus/raw/`.
"""

import json
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
CORPUS_DIR = ROOT / "data" / "repo-corpus"
RAW_DIR = CORPUS_DIR / "raw"
DERIVED_DIR = CORPUS_DIR / "derived"
CHECKOUTS_DIR = CORPUS_DIR / "checkouts"
MANIFEST_PATH = CORPUS_DIR / "repos-v0.json"


def load_manifest():
    with open(MANIFEST_PATH) as f:
        repos = json.load(f)
    if not isinstance(repos, list):
        raise ValueError("repos-v0.json must contain a JSON list")
    return repos


def repo_slug(repo_full_name):
    owner, name = repo_full_name.split("/", 1)
    return f"{owner}__{name}"


def main():
    repos = load_manifest()
    RAW_DIR.mkdir(parents=True, exist_ok=True)
    DERIVED_DIR.mkdir(parents=True, exist_ok=True)
    CHECKOUTS_DIR.mkdir(parents=True, exist_ok=True)

    print("=" * 60)
    print("Repo corpus scout")
    print("=" * 60)
    print(f"Manifest: {MANIFEST_PATH}")
    print(f"Repos:    {len(repos)}")

    for repo in repos:
        repo_name = repo["repo"]
        slug = repo_slug(repo_name)
        repo_dir = RAW_DIR / slug
        repo_dir.mkdir(parents=True, exist_ok=True)

        metadata = {
            "repo": repo_name,
            "track": repo.get("track", "internal"),
            "license": repo.get("license", "unknown"),
            "default_branch": repo.get("default_branch", "master"),
            "include": repo.get("include", []),
            "exclude": repo.get("exclude", []),
            "checkout_path": str(CHECKOUTS_DIR / slug),
            "status": "scaffolded",
        }

        (repo_dir / "repo.json").write_text(json.dumps(metadata, indent=2) + "\n")

        for filename in [
            "vars.jsonl",
            "tests.jsonl",
            "task_candidates.jsonl",
            "repair_pairs.jsonl",
        ]:
            path = repo_dir / filename
            if not path.exists():
                path.write_text("")

        print(f"  {repo_name:32s} -> {repo_dir.relative_to(ROOT)}")

    for filename in [
        "repo_direct_train.jsonl",
        "repo_direct_val.jsonl",
        "repo_repair_train.jsonl",
        "repo_repair_val.jsonl",
    ]:
        path = DERIVED_DIR / filename
        if not path.exists():
            path.write_text("")

    print("\nCreated:")
    print(f"  raw/       {RAW_DIR}")
    print(f"  derived/   {DERIVED_DIR}")
    print(f"  checkouts/ {CHECKOUTS_DIR}")
    print("\nNext: clone or copy repos into data/repo-corpus/checkouts/<owner>__<repo>/")


if __name__ == "__main__":
    main()
