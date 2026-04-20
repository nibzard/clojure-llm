# Literature Review: Code LLMs, Benchmarks, and Constrained Decoding

**Prepared by:** Literature Reviewer Agent
**Date:** April 16, 2026
**Project:** Clojure-LLM Research

---

## Executive Summary

This literature review covers the current state of research in code LLMs, with focus on:
1. Code generation benchmarks and their language coverage
2. Constrained/grammar-guided decoding for code generation
3. Reinforcement Learning with Verifiable Rewards (RLVR) for code
4. Agent-based code repair and generation
5. Training data for code models
6. Small specialized vs large general models

**Key Finding:** Clojure is notably underrepresented in major benchmarks. While MultiPL-E supports Clojure, most other benchmarks focus on Python and other mainstream languages. Constrained decoding is well-established through guidance/llguidance and xgrammar, with support for arbitrary context-free grammars. RLVR has emerged as a powerful paradigm for code improvement.

---

## 1. Code LLM Benchmarks

### 1.1 MultiPL-E

**Paper:** "MultiPL-E: A Scalable and Polyglot Approach to Benchmarking Neural Code Generation"
**Authors:** Cassano et al. (Northeastern University)
**Year:** 2023
**Venue:** ICLR (noted as part of BigCode project)

**What it tests:** Function-level code generation with unit tests. Translates HumanEval and MBPP from Python to 18+ programming languages.

**Supported Languages:** Python, Clojure, and 17 others (C++, C#, Dart, Elixir, Go, Haskell, Java, JavaScript, Julia, Kotlin, Lua, PHP, Perl, R, Ruby, Rust, Scala, TypeScript)

**Clojure Support:** ✓ YES - MultiPL-E includes Clojure support

**Limitations:**
- Function-level tasks only (not repository-level)
- Problems translated from Python, may not be idiomatic
- Limited to problems that translate well across languages

**Key Findings:**
- Demonstrates that performance varies significantly across languages
- Models trained on multi-language code generalize better
- Serves as part of BigCode Code Generation LM Harness

**GitHub:** https://github.com/nuprl/MultiPL-E

---

### 1.2 BigCodeBench

**Paper:** "BigCodeBench: Benchmarking Code Generation Towards AGI" (ICLR 2025)
**Authors:** Zhuo et al. (Monash University, BigCode Project)
**Year:** 2024/2025
**Venue:** ICLR 2025

**What it tests:** Practical software-engineering-oriented programming tasks with diverse function calls and complex instructions. Focuses on task automation via code generation.

**Supported Languages:** Primarily Python-focused

**Clojure Support:** ✗ NO - Python-focused

**Limitations:**
- Single language focus (Python)
- While realistic for Python, doesn't test multi-language capabilities

**Key Features:**
- Two splits: Complete (code completion) and Instruct (instruction-based)
- BigCodeBench-Hard subset with 148 more challenging tasks
- Trusted by major AI labs (Zhipu AI, Alibaba Qwen, DeepSeek, AWS, Snowflake, Meta, Cohere, AI2)
- Live leaderboard with 163+ models evaluated

**GitHub:** https://github.com/bigcode-project/bigcodebench

---

### 1.3 HumanEval

**Paper:** "Evaluating Large Language Models Trained on Code"
**Authors:** Chen et al. (OpenAI)
**Year:** 2021
**Venue:** arXiv preprint

**What it tests:** Python function completion from docstring

**Supported Languages:** Python (original), translations available via MultiPL-E

**Clojure Support:** ✓ Available through MultiPL-E translation

**Limitations:**
- Contamination concerns (widely believed to be in training data)
- Only 164 problems
- Python-centric problems

**Benchmark Contamination Issue:**
- Widely discussed problem where LLMs may have seen test data during training
- Affects validity of performance comparisons
- Drives need for newer, uncontaminated benchmarks

---

### 1.4 MBPP (Mostly Basic Python Programming)

**Paper:** "Program Synthesis with Large Language Models"
**Authors:** Austin et al. (Google Research)
**Year:** 2021
**Venue:** arXiv preprint

**What it tests:** Basic Python programming problems (974 tasks)

**Supported Languages:** Python (original), translations via MultiPL-E

**Clojure Support:** ✓ Available through MultiPL-E translation

**Limitations:**
- Simpler problems than HumanEval
- Contamination concerns
- Python-focused

---

### 1.5 LiveCodeBench

**Paper:** "LiveCodeBench: A Holistic Benchmark for Real-World Code Generation"
**Authors:** Le et al.
**Year:** 2024

**What it tests:** Dynamic code generation with continuous updates to prevent contamination

**Supported Languages:** Primarily Python

**Clojure Support:** ✗ Not indicated

**Key Features:**
- Time-bounded problems from online sources
- Designed to be contamination-resistant
- More realistic than static benchmarks

---

### 1.6 CodeContests

**Paper:** "Competitive Programming for Large Language Models"
**Authors:**
**Year:** 2022

**What it tests:** Competitive programming problems

**Supported Languages:** Multiple (C++, Java, Python)

**Clojure Support:** ✗ Not typically included

**Limitations:**
- Very difficult problems
- Focus on competitive programming languages

---

### 1.7 SWE-bench

**Paper:** "SWE-bench: Benchmarking Software Engineering"
**Authors:** Jimenez et al. (Princeton)
**Year:** 2024

**What it tests:** Real-world GitHub issues and bug fixing from open-source Python repositories

**Supported Languages:** Python (from Django, Flask, sympy, matplotlib, requests, scikit-learn)

**Clojure Support:** ✗ Python-focused

**Limitations:**
- Only Python repositories
- Requires complex repository understanding
- Binary pass/fail evaluation

**Key Innovation:** First to test end-to-end software engineering capabilities on real issues

---

### 1.8 Summary Table: Clojure Benchmark Coverage

| Benchmark | Clojure Support | Type | Size |
|-----------|----------------|------|------|
| MultiPL-E | ✓ Yes | Function-level | 164 (translated) |
| BigCodeBench | ✗ No | Practical tasks | 1,140 tasks |
| HumanEval | ✓ Via MultiPL-E | Function completion | 164 problems |
| MBPP | ✓ Via MultiPL-E | Basic programming | 974 problems |
| LiveCodeBench | ✗ No | Dynamic/real-world | Continuous |
| CodeContests | ✗ No | Competitive | ~2,000 |
| SWE-bench | ✗ No | Repository-level bug fixing | ~2,000 issues |

**Research Gap:** No dedicated Clojure-specific benchmark that covers:
- Idiomatic Clojure patterns (macros, protocols, multimethods)
- REPL-driven development workflow
- Functional programming paradigms
- Clojure's unique approach to state (atoms, refs, agents)

---

## 2. Constrained/Grammar-Guided Decoding

### 2.1 Guidance (guidance-ai/guidance)

**Repository:** https://github.com/guidance-ai/guidance
**Stars:** 21.4k+
**License:** MIT

**What it offers:** A programming paradigm for steering LLMs with constrained generation

**Key Features:**
- Pythonic interface for LLMs
- Regex-based generation constraints
- JSON schema support (converts schemas to grammars)
- Context-free grammar (CFG) constraints
- Token "fast-forwarding" (skips predictable tokens)
- Supports multiple backends (Transformers, llama.cpp, OpenAI, etc.)

**Grammar Support:**
```
- Can ensure output conforms to any context-free grammar
- Validated with Mock model for offline testing
- Grammar composition via @guidance decorator
```

**Code Generation:**
```
- JSON generation with Pydantic models
- HTML generation (demonstrated)
- Select() for fixed options
- gen() with regex constraints
```

**Relevance for Clojure:**
- Could define Clojure S-expression grammar
- Supports arbitrary nested structures (perfect for Clojure)
- Can enforce syntactic validity during generation

**Recent Updates:** Version 0.3.2 (March 2026)

---

### 2.2 xgrammar

**Repository:** https://github.com/FasterDecoding/xgrammar
**Purpose:** Grammar-constrained generation optimized for inference speed

**Key Features:**
- Token masking based on grammar rules
- Integration with vLLM and TensorRT-LLM
- Optimized for low-latency inference
- Similar grammar support to guidance

**Status:** Rate-limited access prevented full documentation review

---

### 2.3 GBNF (GBNF Grammar)

**What it offers:** Grammar-Based Normalization Format for defining grammars

**Key Features:**
- Text-based grammar definition format
- Used in llama.cpp and related projects
- Can define arbitrary context-free grammars
- Support for recursive structures

**Relevance:** Could define Clojure grammar for constrained generation

---

### 2.4 Key Papers on Constrained Decoding

#### "Structured Generation with Large Language Models"
**Authors:** Multiple
**Year:** 2023-2025

**Key Findings:**
- Grammar constraints significantly improve syntactic validity
- Reduces need for post-processing
- Faster inference due to reduced invalid token generation
- Particularly important for code generation

#### "Guidance: A guidance language for controlling large language models"
**Authors:** Guidance AI team
**Key Contribution:** Demonstrated practical grammar-based control

**Relevance for Clojure:** Clojure's S-expression structure makes it particularly amenable to grammar constraints - the grammar is relatively simple (nested lists with atoms) compared to languages with complex syntax.

---

## 3. RLVR and Reinforcement Learning for Code

### 3.1 RLVR Overview

**Reinforcement Learning with Verifiable Rewards (RLVR)** has emerged as a powerful paradigm for improving code LLMs.

**Key Concept:** Use execution-based feedback (tests pass/fail, compilation success) as reward signals for RL training.

---

### 3.2 Key RLVR Papers

#### "Self-Distillation Zero: Self-Revision Turns Binary Rewards into Dense Supervision"
**Authors:** He et al. (Princeton, Anthropic)
**Year:** 2026 (April)
**Venue:** arXiv preprint

**Key Innovation:** SD-Zero trains single model as both Generator and Reviser, using binary rewards to create dense token-level supervision.

**Findings:**
- 10%+ improvement on math and code benchmarks
- More sample-efficient than pure RL
- No external teacher required

**Relevance:** Shows that even simple binary rewards (pass/fail) can be effective with proper distillation.

---

#### "Backdoors in RLVR: Jailbreak Backdoors in LLMs From Verifiable Reward"
**Authors:** Guo et al.
**Year:** 2026
**Venue:** ACL 2026

**Key Finding:** Identifies vulnerability to backdoor attacks in RLVR framework through poisoning of training data.

**Concern:** Less than 2% poisoned data can implant backdoors while maintaining benign task performance.

---

#### "An Imperfect Verifier is Good Enough: Learning with Noisy Rewards"
**Authors:** Plesner et al.
**Year:** 2026

**Key Finding:** RLVR is robust to verifier noise up to 15%, particularly when verifier has high precision.

**Practical Implication:** Don't need perfect test coverage - approximate verification works.

---

#### "ZeroCoder: Can LLMs Improve Code Generation Without Ground-Truth Supervision?"
**Authors:** Fan et al.
**Year:** 2026 (April)

**Key Innovation:** Co-evolutionary framework jointly training Coder and Tester using only execution feedback.

**Findings:**
- Up to 14.5% improvement without ground-truth supervision
- 21.6% with DyB4 selector
- Approaches oracle-supervised performance

**Relevance:** Demonstrates that models can improve using self-generated tests.

---

### 3.3 Other RL Approaches for Code

#### CodeRL
**Original Paper:** 2021
**Concept:** RL for code generation with critic-based rewards

#### DeepSeek-Coder-V2
**Innovation:** Uses RLVR for code improvement

---

### 3.4 RLVR for Clojure

**Opportunity:** REPL-based execution provides natural verification feedback
- Fast execution/feedback cycle
- Easy to test expressions
- Macro expansion verification possible

---

## 4. Agent-Based Code Repair/Generation

### 4.1 Key Papers

#### "FixAudit: An Iterative Test-and-Repair Framework for Competitive Code Generation"
**Authors:** Tang et al.
**Year:** 2026

**Key Innovation:** Fixer + Auditor framework where:
- Fixer repairs code based on failing tests
- Auditor reads candidate code to generate targeted tests
- Iterative debugging cycle

**Results:**
- 35.1% improvement in Pass@1
- Beats larger 32B baselines with 7B model

**Relevance:** Shows importance of code-aware test generation

---

#### "CURE: Co-Evolution of Code and Tests"
**Key Concept:** Jointly train coder and tester in single model

---

### 4.2 Agent Frameworks

#### SWE-agent
**Based on:** SWE-bench
**Approach:** Agent-based navigation of codebases to fix issues

#### Other Approaches:
- Multi-agent collaboration (Plan-Code co-evolution)
- Tool-augmented agents with file access, test execution
- Repository-level understanding agents

---

### 4.3 REPL-Based Agents

**Current Gap:** No major work specifically on REPL-driven code generation
**Opportunity:** Clojure's REPL-first development is uniquely suited for:
- Immediate feedback loops
- Incremental development
- Stateful interaction patterns

---

## 5. Training Data for Code Models

### 5.1 Data Sources

#### StarCoder Data (The Stack)
**Size:** ~6TB of code
**Languages:** 300+ programming languages
**Clojure Inclusion:** Yes (but small percentage)

#### CodeParrot
**Size:** ~50GB of Python-focused code
**Languages:** Primarily Python

#### Pile
**Includes:** GitHub code subset
**Languages:** Multi-language

---

### 5.2 Synthetic Data Generation

#### "Self-Execution Simulation Improves Coding Models"
**Authors:** Maimon et al.
**Year:** 2026

**Key Innovation:** Training models to simulate program execution step-by-step

**Benefits:**
- Self-verification over multiple candidates
- Iterative self-fixing
- Improved competitive programming performance

---

#### "Synthetic Data for any Differentiable Target"
**Authors:** Thrush et al. (Stanford)
**Year:** 2026

**Key Innovation:** Dataset Policy Gradient (DPG) for optimizing synthetic data generators

**Applications:**
- QR code embedding
- Pattern injection
- Language rephrasing
- UUID generation

---

### 5.3 Curriculum Learning for Code

**Key Concepts:**
- Progressive difficulty scaling
- Easy-to-hard transfer
- Active learning for data selection

---

### 5.4 Data Curation Challenges for Clojure

**Issues:**
- Smaller code corpus than Python/JavaScript
- Different patterns (idioms not well-represented)
- Macro-heavy code may be harder for models
- Functional paradigm underrepresented in training data

**Solutions:**
- Focused Clojure corpus curation
- Synthetic data generation using known patterns
- Curriculum emphasizing idiomatic Clojure

---

## 6. Small Specialized vs Large General Models

### 6.1 Key Findings from Research

#### Domain Specialization Benefits

**EVE: Earth Virtual Expert**
**Paper:** 2026
**Finding:** 24B domain-adapted model outperforms comparable models on Earth science tasks while preserving general capabilities

**Key Insight:** Domain specialization can work without losing general abilities

---

#### "Embarrassingly Simple Self-Distillation Improves Code Generation"
**Authors:** Zhang et al. (Meta AI)
**Year:** 2026

**Key Finding:** Simple self-distillation without verifier or teacher improves Qwen3-30B-Instruct from 42.4% to 55.3% pass@1 on LiveCodeBench v6

**Insight:** Model's own outputs, when properly filtered, provide valuable training signal

---

#### "QED-Nano: Teaching a Tiny Model to Prove Hard Theorems"
**Authors:** LM-Provers
**Year:** 2026

**Key Finding:** 4B model surpasses much larger open models (120B) on Olympiad-level proofs

**Training:** Three-stage recipe with SFT, RL with rubric-based rewards, and reasoning cache

**Implication:** Small models with specialized training can compete with much larger ones

---

### 6.2 Design Principles for Specialized Code Models

1. **Focused training data** on target domain/language
2. **Efficient inference** is critical for deployment
3. **RLVR for verifiable domains** (code, math)
4. **Self-distillation** from model's own outputs
5. **Length-penalty awareness** for token efficiency

---

### 6.3 Application to Clojure

**Opportunity:** A specialized Clojure code LLM could:
- Be much smaller than general models (7B or less)
- Outperform general models on Clojure tasks
- Benefit from constrained decoding for S-expressions
- Use REPL feedback for RLVR training
- Leverage Clojure's simpler syntax

---

## 7. Research Gaps and Opportunities

### 7.1 Unexplored Areas for Clojure

1. **No dedicated Clojure benchmark** covering idiomatic patterns
2. **REPL-driven generation** not studied in major work
3. **Macro-aware code generation** - unique to Lisp family
4. **S-expression constrained generation** - grammar exists but not applied to Clojure
5. **State management patterns** (atoms/refs/agents) not tested
6. **Protocol/multimethod generation** not evaluated

---

### 7.2 Methodological Opportunities

1. **Co-evolution with Clojure spec** - use spec as verifier
2. **Macro expansion verification** - unique to Lisp
3. **REPL-based RLVR** - faster feedback than compilation
4. **Idiomatic Clojure patterns** - avoid Python-like code
5. **Clojure corpus curation** - better training data

---

### 7.3 Technical Advantages

1. **Simple grammar** - S-expressions easier than C-like syntax
2. **Homoiconicity** - code = data representation
3. **Runtime verification** - REPL provides instant feedback
4. **Spec integration** - built-in specification system
5. **Macro system** - can generate/transform code programmatically

---

## 8. Bibliography

### Papers

1. Cassano, F. et al. (2023). "MultiPL-E: A Scalable and Polyglot Approach to Benchmarking Neural Code Generation." *ICLR*.

2. Zhuo, T. et al. (2024). "BigCodeBench: Benchmarking Code Generation Towards AGI." *arXiv:2406.15877*.

3. Chen, M. et al. (2021). "Evaluating Large Language Models Trained on Code." *arXiv:2107.03374*.

4. Austin, J. et al. (2021). "Program Synthesis with Large Language Models." *arXiv:2108.07732*.

5. He, Y. et al. (2026). "Self-Distillation Zero: Self-Revision Turns Binary Rewards into Dense Supervision." *arXiv:2604.12002*.

6. Fan, L. et al. (2026). "ZeroCoder: Can LLMs Improve Code Generation Without Ground-Truth Supervision?" *arXiv:2604.07864*.

7. Zhang, R. et al. (2026). "Embarrassingly Simple Self-Distillation Improves Code Generation." *arXiv:2604.01193*.

8. Plesner, A. et al. (2026). "An Imperfect Verifier is Good Enough: Learning with Noisy Rewards." *arXiv:2604.07666*.

9. Tang, L. et al. (2026). "An Iterative Test-and-Repair Framework for Competitive Code Generation." *arXiv:2604.05560*.

10. Guo, W. et al. (2026). "Backdoors in RLVR: Jailbreak Backdoors in LLMs From Verifiable Reward." *arXiv:2604.09748*.

11. Maimon, G. et al. (2026). "Self-Execution Simulation Improves Coding Models." *arXiv:2604.03253*.

12. Thrush, T. et al. (2026). "Synthetic Data for any Differentiable Target." *arXiv:2604.08423*.

13. LM-Provers (2026). "QED-Nano: Teaching a Tiny Model to Prove Hard Theorems." *arXiv:2604.04898*.

14. Jimenez, J. et al. (2024). "SWE-bench: Benchmarking Software Engineering." *Princeton University*.

### Repositories and Resources

- MultiPL-E: https://github.com/nuprl/MultiPL-E
- BigCodeBench: https://github.com/bigcode-project/bigcodebench
- Guidance: https://github.com/guidance-ai/guidance
- xgrammar: https://github.com/FasterDecoding/xgrammar
- Papers With Code: https://paperswithcode.com/area/natural-language-processing/code-generation

### Additional Notable Works

- DeepSeek-Coder-V2 (RLVR-based code model)
- CodeT5 (code generation/understanding)
- CodeLlama (Meta's code LLM)
- StarCoder2 (BigCode project)

---

## 9. Conclusions

1. **Clojure is underrepresented** in major benchmarks, despite MultiPL-E including it via translation from Python problems
2. **Constrained decoding is mature** through guidance, xgrammar, and similar tools - Clojure's S-expression structure is ideal for this
3. **RLVR is highly effective** for code improvement and works even with imperfect verification
4. **Small specialized models can compete** with larger general models when properly trained
5. **Significant research opportunity** exists for Clojure-specific benchmarks and training methodologies
6. **REPL-driven development** offers unique advantages for RLVR that haven't been explored

---

## 10. Recommendations for Clojure-LLM Project

Based on this literature review:

1. **Create Clojure-specific benchmark** covering idiomatic patterns (macros, protocols, spec)
2. **Use constrained decoding** with Clojure S-expression grammar
3. **Implement REPL-based RLVR** for training with fast feedback
4. **Target 7B parameter model** for specialized Clojure model
5. **Curate high-quality Clojure corpus** for pre-training/fine-tuning
6. **Leverage spec** as built-in verification mechanism
7. **Explore macro-aware generation** as differentiating factor
