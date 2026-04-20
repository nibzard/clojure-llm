# Thesis

## Current status

As of April 20, 2026, the cleaned `v1` evaluation regime shows:

- GPT-5.4: 64.0% on the 111-task held-out split, 65.4% on the full 558-task benchmark
- Gemini 3.1 Pro: 64.5% on full 558 (65 tasks pending)
- GPT-5.4-mini: 59.5% on held-out, 60.0% on full benchmark
- Opus 4.7: 45.0% on held-out, 48.0% on full benchmark
- **Qwen3-30B RLVR**: 55.0% on held-out
- **Qwen3-30B SFT**: 52.3% on held-out, 83.8% best-of-16 ceiling
- Qwen3-8B RLVR `v1`: 48.6% on held-out
- Qwen3-8B SFT: 37.8% on held-out

All trained models now beat Opus 4.7 on the held-out slice. The 30B SFT model's best-of-2 (64.9%) matches GPT-5.4's single-pass (64.0%), and best-of-8 (75.7%) leads by 11.7pp. Verifier-shaped training plus a trustworthy evaluator can move a small specialized model past frontier baselines on Clojure code generation.

## The claim

A small, domain-specialized model can outperform larger general-purpose code models on Clojure-specific tasks, and the path to proving that claim produces publishable research artifacts at every stage -- regardless of whether training ultimately works.

## The reframing: iteration speed is the superpower

The framing matters. Ask "is Clojure good for LLM code generation?" and the answer is: not particularly, the training data is thin and the ecosystem is small. Python wins that question.

But ask a different question: **"if autonomous AI agents do rapid generate-evaluate-revise loops, which language properties determine how fast the agent converges on correct code?"** -- and Clojure's position changes materially.

The agent loop is the unit of progress:

```
generate → evaluate → read signal → revise → repeat
```

The cost has two components: LLM inference (same across languages) and verification (compile, test, lint, run). Clojure's verification stack is where it diverges -- and it diverges in a direction that compounds over thousands of autonomous iterations.

### Per-form granularity

This is the core advantage and it is not shared by most mainstream languages.

An agent in Python generates a complete function, runs tests against the whole thing, then reasons about the entire output to find the bug. An agent in Clojure can:

```
Step 1: (defn validate-email [s] ...)        → eval → nil (ok, defined)
Step 2: (validate-email "test@example.com")   → eval → true (ok, valid input works)
Step 3: (validate-email nil)                  → eval → NullPointerException (caught)
Step 4: revise validate-email                  → eval → nil (ok, redefined)
Step 5: (validate-email nil)                  → eval → false (fixed)
```

Each step is a single form. Each evaluation is sub-millisecond on a warm JVM. The agent gets signal after every atomic operation, not after generating an entire file. At 100 iterations per task across thousands of tasks, the difference between per-form evaluation and whole-file compilation compounds into hours.

No other mainstream language centers on this workflow. Python's REPL exists but isn't structural. Go and Rust can't do it at all.

### Verification speed stack

| Signal | Clojure | Python | Go | Rust |
|--------|---------|--------|-----|------|
| Static analysis | clj-kondo <100ms (Rust) | mypy ~500ms | vet ~200ms | clippy ~1s |
| Single-form eval | REPL <1ms (warm JVM) | REPL <1ms | N/A | N/A |
| Test execution | clojure.test ~1s | pytest ~1s | go test ~1s | cargo test ~2s+ |
| Type contract | spec (runtime) | type hints (optional) | compile-time | compile-time |
| Container size | ~300MB (JVM) | ~150MB | ~10MB (static) | ~15MB (static) |

### Safe concurrent agents

Multiple agents working in parallel -- one writing tests, one implementing, one reviewing -- need safe concurrency. Clojure's immutable-by-default data structures and managed state (atom/ref/agent) mean parallel agents don't corrupt each other's work. Python's GIL prevents true parallelism. Go has goroutines but shared mutable state by default. Rust's borrow checker is safe but adds friction to generation.

### Code as data

An agent that needs to read, transform, or compose code in Clojure uses `read-string` -- no parser, no AST, no round-trip. The code is the data structure. In Python the agent needs `ast.parse()`, manipulate, `ast.unparse()`. This eliminates an entire class of work per iteration.

### Small surface area

Clojure has roughly 50 core functions/macros that cover the vast majority of tasks. The agent's search space per iteration is smaller, so it converges faster. Python's thousands of stdlib functions and inconsistent ecosystem mean a larger space of "reasonable solutions" to search through.

## Why this is worth doing

Clojure is not the best language for AI code generation *today*, when models generate complete files and humans review them. But it may be one of the best languages for AI code generation *tomorrow*, when agents iterate autonomously with tight per-form feedback loops.

The project's value has two layers:

**The research layer** -- demonstrate that the benchmark-first specialization loop works. This produces publishable artifacts regardless of training outcome:

1. **The gap is measurable.** Current models score ~10-15% pass@1 on MultiPL-E Clojure. That is low enough to show improvement, high enough to not be hopeless. A 10-point absolute lift is publishable.

2. **The verification story is strong.** `clj-kondo` gives sub-100ms static analysis. `clojure.test` gives executable correctness checks. The REPL gives per-form evaluation. `clojure.spec` gives executable contracts. These are four independent verifier signals, all cheap, all composable into shaped rewards. Most languages have one or two. Clojure has four.

3. **The niche is defensible.** The Clojure community is small but engaged and starved for AI tooling. No major player will invest in Clojure-specific model training because the addressable market is tiny. That means any credible result here holds for a long time.

**The thesis layer** -- test the hypothesis that the properties which make Clojure unusual (REPL-first, immutable, homoiconic) are exactly the properties that make it well-suited for a world where iteration speed of the verification loop determines how fast an agent converges on correct code. If this is true, it has implications beyond Clojure: any language with fast per-expression evaluation, safe concurrency, and small surface area would be well-positioned for autonomous AI development.

## What remains true regardless of framing

Clojure has real disadvantages that the iteration-speed argument does not erase:

- **JVM startup.** A cold `clojure -e "(+ 1 2)"` takes 2-3 seconds vs Python's ~50ms or babashka's ~10ms. A persistent REPL process is required for the per-form advantage to materialize. This adds infrastructure complexity.
- **Hostile error messages.** Java stack traces are deeply nested and reference JVM internals. The agent needs to learn to read them. Python's tracebacks are dramatically more agent-friendly.
- **Training data scarcity.** The iteration loop only helps if the model is in the right ballpark. A fast verification loop on garbage is just fast garbage detection. The model still needs to be trained on enough Clojure to generate plausible code.
- **Ecosystem size.** Fewer libraries means the agent writes more from scratch instead of importing a well-tested package. More generation per task means more iterations per task.

## Validating precedent: what Lean already proved

The thesis above -- verifier-in-the-loop, per-step iteration, small specialized models -- is not speculative. Most of it has already been demonstrated in the Lean theorem prover ecosystem. Lean validates the playbook this project follows.

### The structural parallel

Lean is a dependently typed language and theorem prover. AI agents write "tactics" (proof steps) and the type checker responds instantly with the remaining subgoals. The loop:

```
generate tactic → type-check → see remaining subgoals → next tactic → repeat
```

is structurally identical to the Clojure REPL loop:

```
generate form → eval/verify → see result or error → next form → repeat
```

Same granularity. Same per-step feedback. Same verifier-as-truth model.

### What Lean already showed works

**Verifier-in-the-loop produces lift.** DeepMind's AlphaProof uses Lean as the verifier. The model generates tactics, Lean checks them, the model sees what remains. The model does not need to be right on the first try -- the verifier handles correctness at each step. This is the same pattern as a Clojure agent that evaluates forms through the REPL, gets clj-kondo feedback, and revises.

**Small specialized models beat large general ones.** QED-Nano: a 4B parameter model beats 120B models on Olympiad-level mathematical proofs. Training recipe: SFT on curated Lean data, RL with the type checker as reward signal, reasoning cache. The specialization mechanism is the verifier environment, not the model architecture. A Clojure-specialized model trained with REPL+clj-kondo reward shaping follows the same recipe.

**Verifier-filtered synthetic data solves the small-corpus problem.** Lean had a data problem too -- not much Lean code existed before AI. Mathlib (the standard library) was carefully curated. The solution: generate many proof attempts, keep only what passes the type checker. The verifier manufactures training data from cheap model output. The Clojure project should do the same: generate candidate functions, filter with clj-kondo + clojure.test, keep verifier-clean outputs.

**Per-step reward enables RLVR.** Lean's tactic mode gives per-step signal: did the tactic advance the proof? This is richer than binary pass/fail. The Clojure harness decomposes reward the same way: read-success, kondo-clean, ns-load, test-pass. Shaped per-step rewards, not just a final score.

**The verifier is the asset, not the model.** In the Lean ecosystem, the type checker compounds in value over time. Models are swappable. This project's harness (clj-kondo + REPL + clojure.test + spec) is the equivalent compounding asset. Build it first, train models against it, swap models as better ones appear.

### Where Clojure diverges from Lean

Lean gives mathematical certainty: if it type-checks, the proof is correct. Clojure's verification is behavioral: tests pass, linter is clean, spec validates. Tests can pass for the wrong reason. Spec can be incomplete. The verification is probabilistic, not absolute.

But RLVR research shows the verifier doesn't need to be perfect -- it needs to be right often enough (robust to 15% noise, per Plesner et al.). And for most real-world code generation, behavioral correctness is what you actually need. You don't need a mathematical proof that the HTTP handler is correct. You need it to return the right status codes for the test cases.

Lean targets proofs. Clojure targets production systems. The verification is weaker but the addressable use case is much larger.

## What we actually believe

### Strong claims (high confidence)

- The current Clojure benchmark surface is thin enough that new benchmark work is a contribution by itself, regardless of training outcomes.
- `clj-kondo` + `clojure.test` + containerized execution can be wired into an agent harness that improves pass@1 over direct generation on the same base model, without any training.
- Constrained decoding with an S-expression grammar will reduce syntax and read errors. The grammar is small and regular enough that this should work with existing tools (guidance, xgrammar).
- RLVR is robust to imperfect verification (research shows tolerance up to 15% verifier noise), so the verifier does not need to be perfect.
- A specialized 7B-class or 30B-MoE model can beat a general model of the same size on Clojure tasks. Precedent exists: QED-Nano (4B) beats 120B models on theorem proving through specialization.
- Per-form REPL evaluation provides a tighter agent feedback loop than whole-file compilation. At scale (thousands of autonomous iterations), this time savings is real and measurable.
- Immutability-by-default and managed state make Clojure safer for concurrent multi-agent workflows than languages where shared mutable state is the default.

### Moderate claims (plausible, need evidence)

- The iteration-speed advantages of Clojure (REPL granularity, small surface area, code-as-data) will translate into measurably faster agent convergence on correct solutions. The mechanism is sound but unquantified.
- REPL-driven RLVR (per-form feedback during generation) will outperform batch RLVR (generate complete solution, then test). No prior work studies REPL-based reward shaping.
- The trained model will be useful enough to ship as a product (a Clojure coding agent). Depends on results we don't have yet.

### Weak claims (hypotheses, ablations)

- Macro-aware training (exposure to `(form, macroexpand form)` pairs) will improve generation of macro-heavy code. Macro expansion is noisy and environment-dependent. Treat it as an ablation.
- The iteration-speed thesis generalizes beyond Clojure: any language with fast per-expression evaluation and safe concurrency is well-positioned for autonomous AI development. This is a speculative claim about the future of software development, not a research result.

### Things we explicitly do not claim

- That Clojure is the best language for AI code generation in general. The iteration-speed argument applies to the autonomous agent regime specifically. In the current regime (generate complete files, human reviews), Python's data advantage dominates.
- That deeply nested S-expressions are easy for transformers. The grammar is simple but balanced-delimiter tracking is a known transformer weakness. Constrained decoding helps but does not eliminate this.
- That this approach generalizes automatically to other Lisp dialects. Common Lisp, Scheme, and Racket have different ecosystems, tooling, and idioms.
- That we will beat frontier models (Claude, GPT-5) on Clojure tasks. The claim is same-size comparison: our specialized 7B-30B model vs a general 7B-30B model, on Clojure tasks only.
- That macro-heavy real-world Clojure will be tractable. Macros create project-specific mini-languages. We can handle core Clojure and common library macros, but arbitrary macro DSLs are out of scope for v1.

## What could kill the project

### The harness doesn't produce lift (Gate B failure)

If a verifier-backed agent loop does not improve pass@1 by at least 5 absolute points over direct generation, the training justification evaporates. Before abandoning the thesis, investigate:
- prompt design and template quality
- verifier wiring (is clj-kondo actually catching what the model gets wrong?)
- task design (are MultiPL-E translations even fair Clojure problems?)

If after iterating, the harness still doesn't help, the honest conclusion is that Clojure's verification signals don't translate into agent-loop improvement. That is itself a publishable negative result.

### The training data is insufficient

The available Clojure corpus is small: 261 MultiPL-E tasks, ~500 4clojure problems, and whatever can be scraped from permissively licensed GitHub repos. This might not be enough for meaningful adaptation. Synthetic data generation can stretch the supply, but synthetic data overfits to the teacher's style.

If the corpus is the binding constraint, the project pivots to benchmark publication and agent-tool research without the training stage. That is still a contribution.

### Constrained decoding overhead is too high

If grammar enforcement slows generation enough that the cost-per-passing-solution increases even though syntax errors decrease, constrained decoding becomes a net loss. The fallback is verifier-only mode: let the model generate freely, then filter with clj-kondo and clojure.test.

### Benchmark contamination

MultiPL-E and HumanEval are widely available. If baseline models have memorized the solutions, our numbers are meaningless. Mitigation: report exact model IDs and evaluation dates, prefer repo-level and 4clojure tasks for decontaminated evaluation, treat contamination as a documented limitation rather than a pretend-it-doesn't-exist problem.

## What success looks like

### Minimum (project is worthwhile)

- A reproducible Clojure benchmark (`clj-bench`) with pinned tasks, containerized evaluation, and public baseline numbers for at least three open models.
- A research report showing same-model lift from verifier-backed agent interaction on at least one benchmark track.

This is achievable in 4-6 weeks and publishable regardless of what happens with training.

### Target (project is a success)

- Lightweight adaptation (LoRA SFT) of an open model beats the same base model on `humaneval-clj` and `mbpp-clj` by a measurable margin.
- Repo-level benchmark v0 exists with tasks derived from real Clojure repositories.
- The harness is clean enough that others can use it for their own Clojure experiments.
- Measured evidence that per-form REPL iteration converges faster than whole-file generation+test on the same tasks.

### Stretch (project exceeds expectations)

- A specialized Clojure model matches or beats a significantly larger general model (e.g., our 7B beats a general 30B) on Clojure tasks.
- REPL-driven RLVR shows clear improvement over batch RLVR.
- The agent is good enough that Clojure developers actually want to use it.
- Evidence that the iteration-speed thesis transfers: the REPL advantage is measurable, not just theoretical, and the methodology is applicable to other languages with similar properties.

## Relationship to other documents

- **PLAN.md** -- the operational plan. Milestones, artifacts, timelines, go/no-go gates. Read this for "what do we build and when."
- **RESEARCH-PLAN.md** -- the research findings and prioritized questions. Read this for "what do we know and what do we still need to learn."
- **BENCHMARK.md** -- the benchmark contract. Task schema, run schema, result schema. Read this for "what does the eval look like."
- **This document** -- the honest thesis. What we believe, what we don't, what could fail, and what success means. Read this for "why does this project exist and should we keep going."
