# Shopify Flow Agent Fine-Tuning: Relevance to clojure-llm

Source: [Flow generation through natural language: An agentic modeling approach (2026)](https://shopify.engineering/fine-tuning-agent-shopify-flow)

Shopify fine-tuned Qwen3-32B into a tool-calling agent that generates Flow automations from natural language. 2.2x faster, 68% cheaper than their previous closed-model setup, with a weekly retraining flywheel.

---

## Strong Parallels

**Both fine-tune Qwen3 to beat frontier models in a narrow domain.** Shopify uses Qwen3-32B for Flow DSL generation; we use Qwen3-8B/30B for Clojure code generation. Both achieve frontier-parity or better at lower cost. Our 30B SFT (52.3%) beats Opus 4.7 (45.0%); their fine-tuned model beats their previous closed-model setup at 68% lower cost.

**Representation matters more than model size.** Their biggest single win was switching from JSON DSL to Python DSL (+22 syntactic, +13 semantic points) — same model, same task, different surface format. Our equivalent: we train on verified Clojure pairs directly, not on synthetic representations. This is why our eval pipeline is `syntax → kondo → clojure.test` in the actual target language, not an approximation.

**Verifiable rewards are the frontier they're building toward — and we already have.** Their "what's next" section describes building simulation environments with structured feedback for on-policy optimization. We already did this: RLVR with shaped rewards (`syntax`/`kondo`/`load`/`tests`) using subprocess-isolated Clojure evaluation. Our verifier signal is *cleaner* than what they're planning — test outcomes are deterministic, not LLM-judged.

---

## Where We're Ahead

- **RLVR/on-policy optimization**: They're still off-policy (curated examples), planning to move to on-policy. We already ran GRPO with shaped verifier rewards and saw pass@1 jump from 37.8% (SFT) to 48.6% (RLVR 8B) and 52.3% to 55.0% (30B).
- **Deterministic evaluation**: They need an LLM judge calibrated against human annotations. We have `clojure.test` — binary pass/fail, no calibration needed.
- **Agent integration with response-driven fix loops**: Our Pi agent extension routes all Clojure generation through the RLVR model with auto-doctest and fix mode. Their agent is more traditional tool-calling.

---

## Where They Validate Our Concerns

**Best-of-K ceiling narrowing under RLVR.** We found shaped-reward RLVR narrows the solution distribution: 8B ceiling dropped from 72.1% (SFT) to 64.0% (RLVR v1), 30B from 83.8% to 79.3%. Their article doesn't discuss this, but their planned move to on-policy optimization will likely surface the same tension — optimizing pass@1 can sacrifice diversity.

**Benchmark ≠ production.** Their 35% gap between benchmark parity and real traffic performance is striking. We're insulated from this because our benchmark (558 MultiPL-E tasks) is the evaluation, not a proxy. But if we ever deploy beyond the benchmark, we should expect the same gap.

**Production mirroring sensitivity.** Their finding that tool naming, key ordering, and prompt wording drift between training and inference degrades accuracy is a concrete warning. We should audit whether our SFT prompt format exactly matches what the model sees during inference in the Pi agent.

---

## What We Could Borrow

1. **Tagged slice analysis** — Break our 558 tasks by category (string manipulation, math, collections, etc.) and identify systematic weak spots rather than treating pass@1 as monolithic.
2. **Weekly retraining flywheel** — If we get production usage data from the Pi agent (which tasks required fix-mode retries, which passed first try), we could close the loop from inference back to training.
3. **Their "two cheap calls instead of one expensive one" pattern** for tool optimization maps to our auto-doctest design — verify cheaply first, only escalate to full test when needed.

---

## Bottom Line

This article is strong external validation of our thesis. Shopify independently arrived at the same playbook: fine-tune open models, use domain-specific verifiers, build agent tool integrations, iterate with real signals. The key difference is they're a large engineering org building production infrastructure, while we're a research project that got to on-policy RLVR first because our verification signal (Clojure tests) is cleaner and cheaper than theirs (workflow correctness).

Their conclusion — *"lasting differentiation comes from proprietary data, the training recipe, the infrastructure, and the speed of iteration"* — is exactly our argument in `THESIS.md`. The verifier loop is the moat.

### Key Takeaways for Our Roadmap

| Shopify Finding | Our Status | Action |
|----------------|------------|--------|
| Python DSL > JSON DSL (+22 syntactic) | We already train in Clojure directly | No change needed |
| Production mirroring sensitivity | Not audited | Audit SFT prompt ↔ inference format |
| Benchmark ≠ production (35% gap) | Benchmark IS our eval | Expect gap if we deploy to real agents |
| Tagged slice analysis | Not done | Categorize 558 tasks, find weak spots |
| Weekly retraining flywheel | Not in place | Use Pi agent retry data as training signal |
| Simulation → on-policy (their next step) | Already done (RLVR) | We're ahead here |
| LLM judge for evaluation | Not needed (deterministic tests) | Our signal is cleaner |
| Best-of-K ceiling narrowing under RL | Observed but not fully analyzed | Investigate diversity-regularized RLVR |
