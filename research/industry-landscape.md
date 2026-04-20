# Industry and Competitive Landscape: Code LLMs and Clojure Tooling

**Research Date:** April 2026
**Researcher:** Industry Tracker (Task #2)

## Executive Summary

The code LLM landscape has evolved rapidly in 2025-2026, with several significant developments:

1. **Open-source models have reached parity with proprietary options** - Qwen3-Coder-Next (80B) and DeepSeek-Coder-V3 now compete directly with commercial offerings
2. **Agent runtimes are maturing** - Tools like OpenHands (66.8k stars), SWE-Agent, and pi-mono provide production-ready frameworks for autonomous coding
3. **Clojure support is limited** - Most tools focus on Python/JavaScript/TypeScript; Clojure is rarely mentioned as a first-class citizen
4. **Training infrastructure is commoditized** - Platforms like Modal, Fireworks AI, and Together offer competitive pricing for model training/inference
5. **The market opportunity exists** - No dominant Clojure-specific AI coding tool has emerged

## 1. Code LLM Companies and Products

### Qwen (Alibaba Cloud)

| Model | Parameters | Release Date | Status |
|-------|-----------|--------------|--------|
| Qwen3-Coder-Next | 80B | Feb 3, 2025 | Latest |
| Qwen2.5-Coder-7B-Instruct | 7B | Jan 12, 2025 | Stable |
| Qwen3.6-35B-A3B | 36B | April 2026 | New |
| Qwen3.5-397B-A17B | 403B | April 2026 | Flagship |

**Relevance:** Qwen is the leading open-source code LLM. The Qwen-Coder series is specifically trained for code generation tasks and benchmarks competitively.

**Source:** [HuggingFace - Qwen Organization](https://huggingface.co/Qwen) (435 models)

### DeepSeek

| Model | Parameters | Release Date | Status |
|-------|-----------|--------------|--------|
| DeepSeek-Coder-V3 | Unknown | 2025 | Latest |
| DeepSeek-Coder-V2 | Unknown | 2024 | Stable |

**Relevance:** DeepSeek models are available on HuggingFace and used by multiple platforms (including Fireworks AI).

### Other Notable Models

- **StarCoder2** (BigCode) - Open-source code generation
- **Codestral** (Mistral AI) - Code-focused model
- **Claude 3.5/4.6** (Anthropic) - Proprietary, strong coding capabilities
- **GPT-4o** (OpenAI) - Proprietary, widely used

**Clojure Support:** None of these models advertise specific Clojure training or optimizations. Most support Clojure implicitly through general code training.

## 2. Agent-Based Coding Tools

### Cursor

- **URL:** https://cursor.sh
- **Features:** Tab completion, codebase awareness, natural language editing
- **Security:** SOC 2 Type 2 certified
- **Pricing:** Not publicly listed (contact sales)

**Clojure Support:** Implicit (all languages in supported repositories)

### Windsurf / Codeium

- **Languages:** 70+ supported
- **IDEs:** 40+ integrations
- **Pricing:** Free for individuals
- **Security:** SOC 2 Type 2 certified
- **Customers:** Used by major enterprises

**Clojure Support:** Supported as one of 70+ languages

### GitHub Copilot

- **Tiers:**
  - Free: $0
  - Pro: $10/month
  - Pro+: $39/month
- **Training Data:** Natural language + source code from publicly available sources
- **IDE Support:** VS Code, JetBrains, Vim, Neovim, and more

**Clojure Support:** Yes - supports all languages in public repositories

### Other Tools

- **Aider** - CLI-based coding agent
- **Continue.dev** - Open-source VS Code extension
- **Cody (Sourcegraph)** - Enterprise-focused AI coding assistant
- **Replit Agent** - In-IDE agent for Replit users

## 3. Agent Runtimes

### pi-mono

- **Repository:** https://github.com/badlogic/pi-mono
- **License:** MIT (open-source)
- **Architecture:** Modular toolkit with multiple packages

**Key Components:**
- `@mariozechner/pi-ai` - Unified LLM API
- `@mariozechner/pi-agent-core` - Core agent framework
- `@mariozechner/pi-coding-agent` - Coding agent implementation
- `@mariozechner/pi-tui` - Terminal UI library
- `@mariozechner/pi-web-ui` - Web interface
- `@mariozechner/pi-slack-bot` - Slack integration
- `@mariozechner/pi-kubernetes-vllm` - vLLM pod deployment

**Features:**
- CLI for coding agents
- TUI and web UI libraries
- Slack bot integration
- Kubernetes vLLM pod management
- Modular, extensible architecture

**Clojure Support:** Not mentioned - likely language-agnostic through LLM APIs

### OpenHands

- **Repository:** https://github.com/OpenHands/openhands
- **Stars:** 66.8k
- **License:** MIT
- **Language Breakdown:** 77.2% Python, 19.5% TypeScript

**Deployment Options:**
- SDK (programmatic integration)
- CLI (command-line usage)
- Local GUI
- Cloud (managed service)
- Enterprise (self-hosted)

**Clojure Support:** Not explicitly mentioned; designed for general software engineering

### SWE-Agent (Princeton NLP)

- **Repository:** https://github.com/princeton-nlp/SWE-agent
- **License:** MIT
- **SWE-bench Performance:**
  - Full test set: 12.47% resolved
  - SWE-bench Lite: 23% resolved

**Key Innovation:** Agent-Computer Interface (ACI) - designed interface between agents and computer systems

**Clojure Support:** Not specifically mentioned

### Other Agent Runtimes

- **AutoCodeRover** - Agent for automated code repair
- **Agentless** - Alternative approach to agent-based coding
- **Devin** (Cognition AI) - Proprietary full-stack agent

## 4. Training Platforms

### Modal

- **URL:** https://modal.com
- **Products:** Inference, Training, Sandboxes, Batch, Notebooks
- **Free Tier:** $30/month compute credits
- **Features:**
  - Sub-second cold starts
  - Elastic GPU scaling
  - Serverless architecture
  - Built-in container management

**Pricing:** Pay-per-use with generous free tier

**Relevance:** Excellent for prototyping and training custom models

### Fireworks AI

- **Value Proposition:** "Fastest inference platform"
- **Models Available:** DeepSeek V3.1 and others
- **Notable Customers:** Cursor, Quora, Sourcegraph, Notion
- **Focus:** Production inference speed

**Relevance:** Used by Cursor for production inference

### Together AI

- **Focus:** Model training and inference
- **Offerings:** Custom model training, hosted inference
- **Pricing:** Competitive but requires quote for enterprise

### RunPod

- **Focus:** GPU rental and training
- **Use Case:** Raw GPU access for training

### Replicate

- **Focus:** Model hosting and API access
- **Use Case:** Easy model deployment without infrastructure

## 5. Clojure-Specific AI Tooling

### Current State: Limited

Based on research, there is **no dominant Clojure-specific AI coding tool**. The ecosystem lacks:

1. Clojure-optimized code LLMs
2. Clojure-specific agent runtimes
3. First-class Clojure support in major AI IDEs (beyond basic syntax highlighting)

### Potential Approaches for Clojure

1. **LLM Integration Libraries:** Direct API wrappers (Anthropic, OpenAI, etc.)
2. **Tool-Use Integration:** Leverage Claude/GPT function calling with Clojure data structures
3. **REPL Integration:** AI assistants integrated into Clojure REPL workflows
4. **Form-Aware Models:** Models trained to understand Clojure's macro/functional paradigm

**Opportunity:** This represents a significant market gap that our project could address.

## 6. Recent Technical Reports (2025-2026)

### Key Research Areas

1. **SWE-bench Evaluations:** Standard benchmark for code generation agents
2. **Agent-Computer Interface (ACI):** Princeton's SWE-Agent framework
3. **Multi-Agent Systems:** Coordination patterns for specialized agents
4. **Tool Learning:** Training models to use external tools effectively

### Notable Findings

- Agent performance on SWE-bench ranges from 12-47% depending on the approach
- Multi-agent systems show promise but add complexity
- Clojure is rarely included in benchmark evaluations

## Competitive Positioning Summary

### Market Gaps

1. **No Clojure-First AI Tool:** All major tools focus on Python/JS/TS
2. **Limited Open-Source Training Recipes:** Few resources for training code LLMs on Clojure
3. **No Clojure Agent Runtime:** No pi-mono/OpenHands equivalent for Clojure workflows

### Our Advantages

1. **Clojure Domain Expertise:** Deep understanding of the language's paradigm
2. **Open-Source Approach:** Can differentiate from proprietary tools
3. **Modular Architecture:** Can build on existing runtimes (pi-mono, OpenHands) with Clojure-specific extensions

### Competitive Threats

1. **Established Players:** Cursor, Copilot, and others could add Clojure support
2. **Model Providers:** Qwen/DeepSeek could release Clojure-optimized models
3. **Community Projects:** Open-source Clojure AI tools could emerge independently

## Recommended Tools and Platforms to Evaluate

### Immediate Evaluation (High Priority)

1. **pi-mono** - Modular architecture, could be adapted for Clojure workflows
2. **Qwen3-Coder-Next** - Leading open-source code model for fine-tuning
3. **Modal** - Free tier for prototyping training/inference pipelines

### Secondary Evaluation (Medium Priority)

4. **OpenHands** - Proven agent framework, investigate Clojure integration
5. **SWE-Agent** - ACI design patterns applicable to Clojure REPL workflows
6. **Fireworks AI** - Fast inference if we deploy production services

### Future Considerations (Low Priority)

7. **Cursor API** - If available, for integration partnerships
8. **Together AI** - For custom model training at scale

## Next Steps for Project

1. **Benchmark Existing Models:** Evaluate Qwen3-Coder on Clojure code generation
2. **Prototype Agent Runtime:** Build Clojure-specific agent framework
3. **Dataset Curation:** Collect high-quality Clojure code for training/evaluation
4. **REPL Integration:** Design AI assistant for interactive Clojure development

## Sources

- Qwen Models: https://huggingface.co/Qwen
- pi-mono: https://github.com/badlogic/pi-mono
- OpenHands: https://github.com/OpenHands/openhands
- SWE-Agent: https://github.com/princeton-nlp/SWE-agent
- Cursor: https://cursor.sh
- Modal: https://modal.com
- Fireworks AI: https://fireworks.ai
- GitHub Copilot: https://github.com/features/copilot
