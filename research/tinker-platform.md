# Tinker Platform Research

**Platform:** Tinker by Thinking Machines Lab
**URL:** https://thinkingmachines.ai/tinker
**Date:** April 2026

## Company

- **Founded:** February 2025
- **Founder/CEO:** Mira Murati (former CTO of OpenAI)
- **Team background:** Creators of ChatGPT, PyTorch, OpenAI Gym, Fairseq, Segment Anything, Mistral open-weights
- **Research blog:** "Connectionism" at thinkingmachines.ai/blog
- **NVIDIA partnership:** Announced March 2026 (gigawatt-scale)
- **Notable researchers:** John Schulman (PPO, LoRA research), Kevin Lu (distillation), Horace He, Jeremy Bernstein

## Supported Models

| Model | Params | Type | Notes |
|-------|--------|------|-------|
| Llama-3.2-1B | 1B | Dense | Smallest supported |
| Llama-3.1-8B | 8B | Dense | |
| **Qwen3-4B-Instruct-2507** | 4B | Dense instruct | Already instruction-tuned |
| **Qwen3-8B** | 8B | Dense | |
| **Qwen3-8B-Base** | 8B | Dense base | Best for SFT from scratch |
| Qwen3-30B-A3B-Base | 30B/3B active | MoE | PLAN.md recommendation |
| Qwen3-30B-A3B | 30B/3B active | MoE Hybrid | Reasoning-capable |
| Qwen3-32B | 32B | Dense | |
| Qwen3-235B-A22B | 235B/22B active | MoE | |
| Qwen3-235B-A22B-Instruct-2507 | 235B/22B active | MoE instruct | |
| Qwen3.5-397B-A17B | 397B/17B active | MoE | Largest supported |
| Qwen3-VL-30B-A3B-Instruct | 30B/3B active | MoE vision | |
| Qwen3-VL-235B-A22B-Instruct | 235B/22B active | MoE vision | |

**NOT on Tinker:** DeepSeek, StarCoder, Qwen2.5-Coder (code-specific variants). Only the models above.

**Relevant for this project (under 8B effective):**
- `Qwen3-8B-Base` -- 8B dense, clean base for SFT
- `Qwen3-8B` -- 8B dense, already has some instruction tuning
- `Qwen3-4B-Instruct-2507` -- 4B dense instruct, already usable
- `Qwen3-30B-A3B-Base` -- 3B active compute, 30B total capacity (PLAN.md recommendation)

## Training Methods

### Fine-Tuning
- **LoRA exclusively.** No full fine-tuning.
- LoRA applied to ALL layers: MLP + MoE + attention (not just attention).
- Configurable rank (default rank=32).
- Research from their team: LoRA matches FullFT performance when applied to all layers with proper LR (optimal LoRA LR is ~10x FullFT LR).

### RL Methods
- **PPO** -- standard policy gradient
- **GRPO** -- group relative policy optimization (DeepSeek-style)
- **DPO** -- direct preference optimization
- **3-stage RLHF** pipeline
- **Distillation** -- on-policy and off-policy, single and multi-teacher
- **Custom reward functions** via `Env` and `MessageEnv` classes

### RL Recipes
- **Math RL** -- mathematical reasoning
- **Code RL** -- replicates DeepCoder with sandboxed code execution
- **Verifiers RL** -- reward from verifier models
- **Agent RL** -- agent-based training
- **Multi-Agent RL** -- multi-agent coordination
- **Harbor RL** -- unspecified

### Code RL (Critical for this project)
- **Sandboxed code execution environment** built in
- `SandboxError` exception class confirms execution isolation
- Can run code, check output, use as reward signal
- This is the mechanism for RLVR with Clojure code

## Pricing

- **Unit:** USD per million tokens
- **Storage:** $0.10/GB-month
- **Exact per-model rates:** Pricing page is JS-rendered and not accessible to scrapers
- **PLAN.md reference:** Qwen3-30B-A3B at $0.36/MT train, $0.30/MT sample

## API/Interface

### Python SDK
```python
import tinker

# Core training primitives
client.forward_backward()  # forward + backward pass, accumulating gradients
client.optim_step()         # update weights from accumulated gradients
client.sample()             # generate tokens for RL/evaluation
client.save_state()         # checkpoint training progress
client.save_weights_and_get_sampling_client()  # create inference endpoint
```

### Data Format
- `ModelInput` with list of `Chunk` objects
- `EncodedTextChunk` -- tokenized text
- `SupervisedDataset` / `ChatDatasetBuilder` for SFT
- `RLDataset` / `EnvGroupBuilder` for RL
- Support for HuggingFace datasets (streaming and non-streaming)

### OpenAI-Compatible API
- Uses `tinker://` model paths
- Works with standard OpenAI client library
- Completions endpoint for inference
- **This is how Pi would call our trained model**

### Claude Code Integration
7 skills: `/tinker:core`, `/tinker:sft`, `/tinker:rl`, `/tinker:preferences`, `/tinker:ops`, `/tinker:debug`, `/tinker:dev`

### Other Interfaces
- CLI: `tinker run`, `tinker checkpoint`
- Web console: https://tinker-console.thinkingmachines.ai
- Cookbook: https://github.com/thinking-machines-lab/tinker-cookbook

## Weight Export
- Download as tar.gz archive
- Build HuggingFace model
- Publish to HuggingFace Hub
- This means our trained model can be deployed anywhere

## Information Gaps

- Exact per-model pricing breakdown (JS-rendered pages)
- GPU hardware specs
- Context length limits
- Dataset size / training time limits
- Free tier details post-GA

## Sources

- https://thinkingmachines.ai/tinker (product page)
- https://thinkingmachines.ai/news/announcing-tinker (launch announcement)
- https://thinkingmachines.ai/news/tinker-general-availability (GA announcement)
- https://thinkingmachines.ai/blog/lora (LoRA Without Regret)
- https://github.com/thinking-machines-lab/tinker-cookbook (cookbook README)
- https://pypi.org/project/tinker-cookbook/ (PyPI page)
- https://tinker-docs.thinkingmachines.ai (docs, partially JS-rendered)
