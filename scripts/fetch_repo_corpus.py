#!/usr/bin/env python3
"""Clone allowlisted repositories for repo-corpus extraction.

This script performs shallow clones of the repositories listed in
`data/repo-corpus/repos-v0.json` into `data/repo-corpus/checkouts/`.
Existing checkouts are left in place unless `--refresh` is passed.
"""

import argparse
import json
import shutil
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
CORPUS_DIR = ROOT / "data" / "repo-corpus"
MANIFEST_PATH = CORPUS_DIR / "repos-v0.json"
CHECKOUTS_DIR = CORPUS_DIR / "checkouts"


def load_manifest():
    with open(MANIFEST_PATH) as f:
        return json.load(f)


def repo_slug(repo_full_name):
    owner, name = repo_full_name.split("/", 1)
    return f"{owner}__{name}"


def clone_repo(repo, refresh=False):
    repo_name = repo["repo"]
    default_branch = repo.get("default_branch", "master")
    slug = repo_slug(repo_name)
    target = CHECKOUTS_DIR / slug

    if target.exists():
        if not refresh:
            return "skip", target
        shutil.rmtree(target)

    url = f"https://github.com/{repo_name}.git"
    cmd = [
        "git",
        "clone",
        "--depth",
        "1",
        "--branch",
        default_branch,
        url,
        str(target),
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode != 0:
        raise RuntimeError(result.stderr.strip() or f"git clone failed for {repo_name}")
    return "cloned", target


def main():
    parser = argparse.ArgumentParser(description="Fetch allowlisted repo corpus checkouts")
    parser.add_argument(
        "--refresh",
        action="store_true",
        help="Delete and re-clone existing checkouts",
    )
    args = parser.parse_args()

    repos = load_manifest()
    CHECKOUTS_DIR.mkdir(parents=True, exist_ok=True)

    print("=" * 60)
    print("Fetch repo corpus")
    print("=" * 60)

    cloned = 0
    skipped = 0

    for repo in repos:
        repo_name = repo["repo"]
        try:
            status, target = clone_repo(repo, refresh=args.refresh)
            if status == "cloned":
                cloned += 1
            else:
                skipped += 1
            print(f"  {status:6s} {repo_name:32s} -> {target.relative_to(ROOT)}")
        except Exception as e:
            print(f"  error  {repo_name:32s} -> {e}", file=sys.stderr)

    print("\nSummary:")
    print(f"  cloned:  {cloned}")
    print(f"  skipped: {skipped}")


if __name__ == "__main__":
    main()
