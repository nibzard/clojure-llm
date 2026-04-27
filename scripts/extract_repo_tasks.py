#!/usr/bin/env python3
"""Extract conservative repo-derived task candidates from local checkouts.

This v1 extractor is still conservative:
- scans `.clj`, `.cljc`, `.cljs` source files under each checkout
- records top-level `defn` / `defn-` forms with optional docstrings
- records test files separately
- matches vars to tests using symbol and namespace heuristics
- emits direct task candidates only when a var has real contract signal:
  docstring and/or matched tests

The prompt format stays function-level so it fits the existing SFT pipeline.
"""

import json
import fnmatch
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
CORPUS_DIR = ROOT / "data" / "repo-corpus"
MANIFEST_PATH = CORPUS_DIR / "repos-v0.json"
CHECKOUTS_DIR = CORPUS_DIR / "checkouts"
RAW_DIR = CORPUS_DIR / "raw"

SRC_EXTS = {".clj", ".cljc", ".cljs"}
GENERIC_VAR_NAMES = {
    "read",
    "write",
    "normalize",
    "parse",
    "print",
    "debug",
    "run",
    "start",
    "stop",
    "main",
    "-main",
    "init",
    "load",
    "save",
    "update",
}
DEFN_RE = re.compile(
    r'^\((defn-?)\s+([^\s\[\(\)]+)(?:\s+"([^"]*)")?\s+\[([^\]]*)\]',
    re.MULTILINE,
)
NS_RE = re.compile(r'^\(ns\s+([^\s\)]+)', re.MULTILINE)


def load_manifest():
    with open(MANIFEST_PATH) as f:
        return json.load(f)


def repo_slug(repo_full_name):
    owner, name = repo_full_name.split("/", 1)
    return f"{owner}__{name}"


def matches_any(path_str, patterns):
    return any(fnmatch.fnmatch(path_str, pattern) for pattern in patterns)


def find_files(root, include_patterns=None, exclude_patterns=None):
    include_patterns = include_patterns or ["**"]
    exclude_patterns = exclude_patterns or []
    found = []
    for path in root.rglob("*"):
        if not path.is_file() or path.suffix not in SRC_EXTS:
            continue
        rel_str = path.relative_to(root).as_posix()
        if include_patterns and not matches_any(rel_str, include_patterns):
            continue
        if exclude_patterns and matches_any(rel_str, exclude_patterns):
            continue
        found.append(path)
    return found


def detect_tests(files):
    tests = []
    for path in files:
        lowered = path.as_posix().lower()
        if "/test/" in lowered or lowered.endswith("_test.clj") or lowered.endswith("_test.cljc"):
            tests.append(path)
    return tests


def read_text(path):
    return path.read_text(errors="ignore")


def extract_ns_name(text):
    match = NS_RE.search(text)
    return match.group(1) if match else None


def extract_balanced_form(text, start_idx):
    depth = 0
    in_string = False
    escape = False

    for idx in range(start_idx, len(text)):
        ch = text[idx]

        if in_string:
            if escape:
                escape = False
            elif ch == "\\":
                escape = True
            elif ch == '"':
                in_string = False
            continue

        if ch == '"':
            in_string = True
            continue
        if ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                return text[start_idx:idx + 1]

    return None


def extract_balanced_vector(text, start_idx):
    depth = 0
    in_string = False
    escape = False

    for idx in range(start_idx, len(text)):
        ch = text[idx]

        if in_string:
            if escape:
                escape = False
            elif ch == "\\":
                escape = True
            elif ch == '"':
                in_string = False
            continue

        if ch == '"':
            in_string = True
            continue
        if ch == "[":
            depth += 1
        elif ch == "]":
            depth -= 1
            if depth == 0:
                return text[start_idx:idx + 1]

    return None


def extract_vars(path):
    text = read_text(path)
    ns_name = extract_ns_name(text)
    vars_found = []
    for match in DEFN_RE.finditer(text):
        defn_kind, name, docstring, argvec = match.groups()
        form = extract_balanced_form(text, match.start())
        if form is None:
            continue
        argvec_start = form.find("[")
        argvec_text = None
        if argvec_start != -1:
            argvec_text = extract_balanced_vector(form, argvec_start)
        form_lines = [line for line in form.strip().splitlines() if line.strip()]
        vars_found.append(
            {
                "name": name,
                "private": defn_kind == "defn-",
                "docstring": docstring or "",
                "argvec": argvec_text or f"[{argvec}]",
                "source_path": str(path),
                "form": form.strip(),
                "form_line_count": len(form_lines),
                "form_char_count": len(form.strip()),
                "ns_name": ns_name or "",
            }
        )
    return vars_found


def path_namespace_hint(rel_path):
    parts = list(rel_path.parts)
    if not parts:
        return ""
    if parts[0] in {"src", "test"}:
        parts = parts[1:]
    if not parts:
        return ""
    suffixless = Path(*parts).with_suffix("")
    return suffixless.as_posix().replace("/", ".").replace("_", "-")


def normalize_symbol(name):
    return {name, name.replace("-", "_"), name.replace("_", "-")}


def build_test_index(checkout, tests):
    index = []
    for test_file in tests:
        rel_path = test_file.relative_to(checkout)
        text = read_text(test_file)
        index.append(
            {
                "path": str(rel_path),
                "text": text,
                "ns_name": extract_ns_name(text) or "",
                "path_hint": path_namespace_hint(rel_path),
            }
        )
    return index


def summarize_test_evidence(test_row, var_name):
    lines = []
    normalized_names = normalize_symbol(var_name)
    for raw_line in test_row["text"].splitlines():
        line = raw_line.strip()
        if not line:
            continue
        if not any(name in line for name in normalized_names):
            continue
        if "(is " in line or "(are " in line:
            lines.append(line)
        if len(lines) >= 2:
            break
    return lines


def matches_var(test_row, var_name, source_ns, source_path):
    normalized_names = normalize_symbol(var_name)
    text = test_row["text"]

    symbol_hit = any(
        re.search(rf"(?<![\w\-/]){re.escape(name)}(?![\w\-/])", text)
        for name in normalized_names
    )
    if symbol_hit:
        return True

    source_hint = path_namespace_hint(source_path)
    namespace_hits = {
        source_ns,
        source_ns + "-test" if source_ns else "",
        source_hint,
        source_hint + "-test" if source_hint else "",
    }
    namespace_hits.discard("")

    if test_row["ns_name"] in namespace_hits:
        return True
    if test_row["path_hint"] in namespace_hits:
        return True

    return False


def contract_type_for(var, matched_tests):
    has_doc = bool(var["docstring"].strip())
    has_tests = bool(matched_tests)
    if has_doc and has_tests:
        return "docstring_and_test"
    if has_doc:
        return "docstring_contract"
    if has_tests:
        return "test_contract"
    return None


def contract_score(contract_type):
    return {
        "docstring_and_test": 3,
        "docstring_contract": 2,
        "test_contract": 1,
        None: 0,
    }[contract_type]


def should_emit_candidate(var, contract_type):
    if contract_type is None:
        return False
    if var["private"]:
        return False
    if var["name"].startswith("-"):
        return False
    if var["name"] in GENERIC_VAR_NAMES and contract_type == "test_contract":
        return False
    # Drop trivial wrappers and tiny helpers from the first repo-derived corpus.
    if var["form_line_count"] < 3 and var["form_char_count"] < 80:
        return False
    # For test-only contracts, require stronger evidence than a bare namespace/name match.
    if contract_type == "test_contract" and var["form_line_count"] < 4:
        return False
    return True


def build_prompt(var, matched_tests, contract_type, test_evidence):
    lines = ["Implement the following Clojure function from a real library.", ""]
    lines.append("(defn " + var["name"])
    if var["docstring"]:
        lines.append(f'  "{var["docstring"]}"')
    lines.append(f"  {var['argvec']})")

    if test_evidence:
        lines.append("")
        lines.append("Observed behavior in repo tests:")
        for evidence in test_evidence[:3]:
            lines.append(f"- {evidence}")
    elif matched_tests:
        lines.append("")
        lines.append("Observed in repo tests:")
        for path in matched_tests[:3]:
            lines.append(f"- related test file: {path}")

    lines.append("")
    lines.append(f"Contract type: {contract_type}")
    lines.append("Provide only the function implementation.")
    return "\n".join(lines) + "\n"


def main():
    repos = load_manifest()

    print("=" * 60)
    print("Extract repo tasks")
    print("=" * 60)

    total_vars = 0
    total_candidates = 0

    for repo in repos:
        repo_name = repo["repo"]
        slug = repo_slug(repo_name)
        checkout = CHECKOUTS_DIR / slug
        raw_dir = RAW_DIR / slug

        if not checkout.exists():
            print(f"  SKIP {repo_name}: missing checkout at {checkout}")
            continue

        files = find_files(
            checkout,
            include_patterns=repo.get("include", ["**"]),
            exclude_patterns=repo.get("exclude", []),
        )
        tests = detect_tests(files)
        test_index = build_test_index(checkout, tests)

        vars_rows = []
        test_rows = []
        candidate_rows = []

        for test_file in tests:
            test_rows.append(
                {
                    "repo": repo_name,
                    "path": str(test_file.relative_to(checkout)),
                }
            )

        for path in files:
            lowered = path.as_posix().lower()
            if "/test/" in lowered:
                continue
            rel_path = path.relative_to(checkout)
            for var in extract_vars(path):
                matched_test_rows = [
                    test_row
                    for test_row in test_index
                    if matches_var(test_row, var["name"], var["ns_name"], rel_path)
                ]
                matched_tests = [test_row["path"] for test_row in matched_test_rows]
                test_evidence = []
                for test_row in matched_test_rows[:5]:
                    test_evidence.extend(summarize_test_evidence(test_row, var["name"]))
                    if len(test_evidence) >= 3:
                        break
                contract_type = contract_type_for(var, matched_tests)
                if contract_type == "test_contract" and not test_evidence:
                    contract_type = None
                row = {
                    "repo": repo_name,
                    "track": repo.get("track", "internal"),
                    "license": repo.get("license", "unknown"),
                    "source_path": str(rel_path),
                    "var_name": var["name"],
                    "ns_name": var["ns_name"],
                    "private": var["private"],
                    "docstring": var["docstring"],
                    "argvec": var["argvec"],
                    "form_line_count": var["form_line_count"],
                    "form_char_count": var["form_char_count"],
                    "matched_tests": matched_tests,
                    "test_evidence": test_evidence[:3],
                    "contract_type": contract_type,
                    "contract_score": contract_score(contract_type),
                }
                vars_rows.append(row)

                if should_emit_candidate(var, contract_type):
                    candidate_rows.append(
                        {
                            "repo": repo_name,
                            "track": repo.get("track", "internal"),
                            "license": repo.get("license", "unknown"),
                            "task_id": f"repo::{slug}::{rel_path.as_posix()}::{var['name']}",
                            "source_path": str(rel_path),
                            "var_name": var["name"],
                            "ns_name": var["ns_name"],
                            "private": var["private"],
                            "matched_tests": matched_tests,
                            "test_evidence": test_evidence[:3],
                            "contract_type": contract_type,
                            "contract_score": contract_score(contract_type),
                            "form_line_count": var["form_line_count"],
                            "form_char_count": var["form_char_count"],
                            "messages": [
                                {
                                    "role": "user",
                                    "content": build_prompt(
                                        var,
                                        matched_tests,
                                        contract_type,
                                        test_evidence[:3],
                                    ),
                                },
                                {"role": "assistant", "content": var["form"]},
                            ],
                        }
                    )

        vars_path = raw_dir / "vars.jsonl"
        tests_path = raw_dir / "tests.jsonl"
        candidates_path = raw_dir / "task_candidates.jsonl"

        with open(vars_path, "w") as f:
            for row in vars_rows:
                f.write(json.dumps(row) + "\n")

        with open(tests_path, "w") as f:
            for row in test_rows:
                f.write(json.dumps(row) + "\n")

        with open(candidates_path, "w") as f:
            for row in candidate_rows:
                f.write(json.dumps(row) + "\n")

        total_vars += len(vars_rows)
        total_candidates += len(candidate_rows)
        contract_counts = {}
        for row in candidate_rows:
            ct = row["contract_type"]
            contract_counts[ct] = contract_counts.get(ct, 0) + 1
        contract_summary = ", ".join(
            f"{name}={count}" for name, count in sorted(contract_counts.items())
        ) or "none"
        print(
            f"  {repo_name:32s} vars={len(vars_rows):4d} tests={len(test_rows):3d} candidates={len(candidate_rows):4d} [{contract_summary}]"
        )

    print("\nSummary:")
    print(f"  Vars:       {total_vars}")
    print(f"  Candidates: {total_candidates}")


if __name__ == "__main__":
    main()
