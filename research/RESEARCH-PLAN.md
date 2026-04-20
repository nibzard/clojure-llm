# Research Plan: Benchmark-First Clojure Code LLM

**Synthesized from:** Literature Review, Industry Landscape, and Clojure Ecosystem deep-dives
**Date:** April 16, 2026
**Status:** Complete

---

## Executive Summary

This research plan synthesizes findings from three parallel research tracks into an actionable roadmap for the clojure-llm project. The core thesis is validated: **Clojure is significantly underrepresented in code LLM benchmarks and tooling, creating a genuine research and market opportunity.**

### Key Conclusions

1. **The gap is real.** Only MultiPL-E includes Clojure (261 tasks, ~10-15% pass@1). No Clojure-specific benchmark, model, or agent tool exists.
2. **The tools are ready.** Constrained decoding (guidance, xgrammar), verification (clj-kondo, clojure.test), grammar support (tree-sitter-clojure), and agent runtimes (pi-mono, OpenHands) are all production-ready.
3. **The research precedent is strong.** Small specialized models can beat larger general ones (QED-Nano: 4B beats 120B on proofs). RLVR shows 10%+ gains. Self-distillation works without external teachers.
4. **The differentiation is clear.** REPL-driven generation, macro-aware training, and spec-based verification are all unexplored in the literature.

---

## Research Domain Map

### What exists today

| Domain | Status | Clojure Coverage |
|--------|--------|-----------------|
| Function-level benchmarks | Mature (HumanEval, MBPP, BigCodeBench) | Only via MultiPL-E translation (261 tasks) |
| Repo-level benchmarks | Growing (SWE-bench) | None |
| Constrained decoding | Production-ready (guidance 21k stars, xgrammar) | No Clojure grammar published |
| RLVR for code | Active research, strong results (10-14% gains) | Not applied to any Lisp |
| Agent-based repair | Multiple frameworks (SWE-Agent, OpenHands, pi-mono) | None target Clojure/REPL |
| Training platforms | Commoditized (Modal $30/mo free, Together, Fireworks) | N/A |
| Code LLMs | Qwen3-Coder-Next 80B leading open-source | Implicit support, no specialization |

### Research gaps (opportunities)

1. **No idiomatic Clojure benchmark** -- macros, protocols, multimethods, atoms/refs, spec
2. **No REPL-driven generation** studied in major work
3. **No S-expression constrained decoding** grammar published
4. **No macro-aware training** methodology
5. **No Clojure-specific fine-tuned model** on HuggingFace
6. **No Clojure agent runtime** with REPL-in-the-loop

---

## Prioritized Research Questions

### Tier 1: Must answer before building (Weeks 1-3)

**RQ1: How bad are current models at Clojure, exactly?**
- Run MultiPL-E humaneval-clj (164 tasks) and mbpp-clj (97 tasks) against pinned baselines
- Baselines: Qwen2.5-Coder-7B, Qwen3-30B-A3B, DeepSeek-Coder-V2-Lite, starcoder2-15b
- Record: pass@1, pass@k, syntax/read failure rate, clj-kondo clean rate, wall-clock cost
- *Source: PLAN.md baselines section, MultiPL-E repo (github.com/nuprl/MultiPL-E)*

**RQ2: Does a verifier-in-the-loop agent improve Clojure pass rates?**
- Build the clj-harness with containerized execution, clj-kondo, and clojure.test
- Compare: direct generation vs agent loop vs agent + clj-kondo vs agent + constrained decoding
- Threshold: 5+ absolute points of pass@1 lift (PLAN.md Gate B)
- *Source: literature -- FixAudit (35% improvement with 7B model), SD-Zero (10%+ gains)*

**RQ3: Can constrained decoding reduce Clojure syntax failures?**
- Build S-expression grammar from tree-sitter-clojure (CC0 license)
- Integrate with guidance or xgrammar
- Measure: syntax error reduction, decode overhead, latency impact
- *Source: literature -- constrained decoding is mature; Clojure S-expressions are grammatically simpler than C-like syntax*

### Tier 2: Must answer before training (Weeks 3-6)

**RQ4: How much Clojure training data is available and how do we curate it?**
- Inventory: MultiPL-E (261 tasks), 4clojure (500+ EPL-licensed), The Stack (Clojure subset), permissively licensed repos (metosin/muuntaja, metosin/reitit, weavejester/medley)
- Separate: internal-corpus vs release-corpus (PLAN.md data plan)
- Estimate total available tokens after dedup and cleaning
- *Source: ecosystem research -- no Clojure dataset on HuggingFace, opportunity to create first*

**RQ5: What training approach gives best ROI?**
- Compare: LoRA SFT only vs continued pretraining + SFT vs SFT + RLVR
- Use the dual-mode harness (eval mode / RL mode) from PLAN.md Artifact B
- RLVR reward shaping: read success, kondo-clean, ns-load, test-pass
- *Source: literature -- RLVR robust to 15% verifier noise (Plesner et al.); self-distillation works without teachers (Zhang et al.)*

**RQ6: Which base model and training platform?**
- PLAN.md recommends Qwen3-30B-A3B (3B active, $0.36/MT on Tinker)
- Evaluate: Modal ($30/mo free tier for prototyping), Together AI, Fireworks AI
- Test: Qwen2.5-Coder-7B vs Qwen3-30B-A3B vs alternatives
- *Source: industry research -- Modal best for prototyping, model swap is one-line change*

### Tier 3: Differentiating research (Weeks 6-12)

**RQ7: Does macro-aware training improve code generation?**
- Generate (form, macroexpand-1 form) pairs from permissively licensed code
- Test whether expansion supervision improves macro reasoning
- This is an ablation, not a pillar (PLAN.md macro-aware track)
- *Source: literature -- no prior work on macro-aware generation*

**RQ8: Does REPL-driven RLVR outperform batch RLVR?**
- Compare: single-shot generation + test vs incremental REPL-based generation with per-form feedback
- REPL track (PLAN.md Track 3): multi-step tasks where agent evaluates one form at a time
- *Source: literature -- no prior work on REPL-driven RLVR*

---

## Concrete Research Actions

### Phase 0: Baseline measurement (Week 1-2)

| Action | Deliverable | Priority |
|--------|-------------|----------|
| Set up MultiPL-E Clojure evaluation in Docker | Reproducible eval pipeline | P0 |
| Run humaneval-clj + mbpp-clj against 4 open baselines | Baseline result table | P0 |
| Set up clj-kondo with JSON output in Docker container | Verification pipeline | P0 |
| Extract 4clojure problems and convert to clojure.test | 4clojure-bench task inventory | P1 |
| Build first agent loop with test execution + retry | Agent baseline comparison | P0 |

**Baseline models to test (from PLAN.md):**
```
Qwen/Qwen2.5-Coder-7B
Qwen/Qwen2.5-Coder-7B-Instruct
deepseek-ai/DeepSeek-Coder-V2-Lite-Base
bigcode/starcoder2-15b
```

### Phase 1: Harness + ablations (Week 2-4)

| Action | Deliverable | Priority |
|--------|-------------|----------|
| Build dual-mode harness (reset/step/close API) | clj-harness v0 | P0 |
| Add constrained decoding with S-expression grammar | Grammar file + integration | P1 |
| Run 4 ablation configs on humaneval-clj | Ablation report (PLAN.md Artifact C) | P0 |
| Investigate pi-mono as first agent runtime | Integration assessment | P2 |
| Curate list of permissively licensed repos for repo-repair | Repo candidate list | P2 |

**Ablation matrix:**
| Config | Agent loop | Constrained decoding | clj-kondo |
|--------|-----------|---------------------|-----------|
| A | No | No | No |
| B | Yes | No | Yes |
| C | No | Yes | No |
| D | Yes | Yes | Yes |

### Phase 2: Go/No-go + data pipeline (Week 4-6)

**Go/No-go decision point** (PLAN.md Gates A-C):
- Gate A: Benchmark reproducible, not saturated, cost acceptable
- Gate B: 5+ absolute points pass@1 lift from harness
- Gate C: Training gains stable, transfer beyond one slice, cost reasonable

If GO:
| Action | Deliverable | Priority |
|--------|-------------|----------|
| Build synthetic data pipeline with verifier filtering | Data generator | P0 |
| Curate internal-corpus and release-corpus | Two-track dataset | P0 |
| Set up LoRA SFT on chosen platform | Training pipeline | P0 |
| Run first Qwen3-30B-A3B fine-tune on function-level tasks | First adapted model | P1 |

If NO-GO: tighten benchmark design, investigate prompt/verifier improvements before training spend.

### Phase 3: Specialized training (Week 6-12)

| Action | Deliverable | Priority |
|--------|-------------|----------|
| Full SFT or continued pretraining on Clojure corpus | Stronger adapted model | P1 |
| Implement RLVR with REPL-based reward shaping | RL training loop | P1 |
| Macro-aware ablation | Research finding | P2 |
| Repo-level benchmark from curated repos | clj-bench v1 | P2 |
| Publish benchmark + methodology note | Research report | P1 |

---

## Key Resources and Links

### Benchmarks & Data
- MultiPL-E: https://github.com/nuprl/MultiPL-E (261 Clojure tasks, Apache-2.0)
- 4clojure: https://github.com/4clojure/4clojure (~500 problems, EPL 1.0)
- BigCodeBench: https://github.com/bigcode-project/bigcodebench (Python, but methodology reference)
- The Stack (StarCoder data): includes Clojure subset

### Tools & Infrastructure
- clj-kondo: https://github.com/clj-kondo/clj-kondo (Rust-based, <100ms, JSON output)
- tree-sitter-clojure: https://github.com/sogaiu/tree-sitter-clojure (CC0, production-ready)
- guidance: https://github.com/guidance-ai/guidance (21k stars, CFG constraints)
- xgrammar: https://github.com/FasterDecoding/xgrammar (vLLM integration)
- babashka: https://babashka.org (native Clojure, instant startup, fast verification)
- pi-mono: https://github.com/badlogic/pi-mono (agent runtime, MIT license)
- OpenHands: https://github.com/OpenHands/openhands (66.8k stars, agent framework)

### Training Platforms
- Modal: https://modal.com ($30/mo free, best for prototyping)
- Together AI: https://together.ai (training + inference)
- Fireworks AI: https://fireworks.ai (fast inference, used by Cursor)
- RunPod: https://runpod.io (raw GPU rental)

### Key Papers
1. MultiPL-E (Cassano et al., ICLR 2023) -- cross-language benchmark
2. BigCodeBench (Zhuo et al., ICLR 2025) -- practical code tasks
3. SD-Zero (He et al., 2026) -- self-distillation with binary rewards
4. ZeroCoder (Fan et al., 2026) -- co-evolutionary coder/tester training
5. QED-Nano (LM-Provers, 2026) -- 4B beats 120B with specialization
6. FixAudit (Tang et al., 2026) -- iterative test-and-repair, 35% improvement
7. Self-distillation (Zhang et al., 2026) -- 42% -> 55% with no external teacher
8. Imperfect Verifier (Plesner et al., 2026) -- RLVR robust to 15% noise

### Repo Candidates for Repair Benchmarks
- metosin/muuntaja (MIT, 97% test coverage)
- metosin/reitit (MIT, excellent coverage, idiomatic)
- weavejester/medley (EPL, small utility lib, ideal for bug injection)
- clj-commons/pretty (EPL)
- clojure/tools.logging (EPL)

---

## Recommended Next Steps

1. **Immediately:** Set up MultiPL-E Docker eval, run baselines on humaneval-clj
2. **This week:** Build clj-kondo verification pipeline, extract 4clojure tasks
3. **Next week:** Build agent loop, run first ablations
4. **Week 3:** Constrained decoding with S-expression grammar
5. **Week 4:** Go/no-go decision on training investment

The research confirms the project's thesis is sound. The path is: benchmark -> measure -> harness -> ablate -> decide on training. Each step produces a publishable artifact regardless of the training outcome.

---

## Source Files

- [Literature Review](./literature-review.md) -- 30+ papers, 6 research areas
- [Industry Landscape](./industry-landscape.md) -- companies, tools, platforms
- [Clojure Ecosystem](./clojure-ecosystem.md) -- benchmarks, tooling, data sources
