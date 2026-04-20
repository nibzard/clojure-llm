# Training Data Improvement Plan

This document covers planned improvements to the training pipeline based on findings from
session `4eefcd76` and the tinker-clojure extension fixes.

## 1. Kondo-Aware SFT Pairs

Current SFT data uses only test-passing candidates. Adding kondo-error → kondo-fix pairs
would teach the model to avoid common lint violations.

**Approach:**
- During SFT data generation, capture candidates that fail kondo but pass syntax
- Pair each failing candidate with the next kondo-passing candidate for the same task
- Structure: `(kondo-failing-code, kondo-passing-code)` as negative → positive pairs
- Estimated yield: ~500 additional pairs from existing generation runs

**Benefit:** Model learns to avoid unused bindings, unresolved symbols, and common
Clojure idioms that kondo flags — before it ever sees RLVR rewards.

## 2. Namespace-Level SFT Data

The current model is single-defn trained. Real Clojure code operates at the namespace
level with imports, helper functions, and interdependencies.

**Approach:**
- Generate namespace-level prompts (ns declaration + multiple defn stubs)
- Source from existing Clojure open-source repos (clojure.core, clojure.set, etc.)
- Keep single-defn as primary training mode but add ~10% namespace-level examples
- This is a future-phase improvement; current extension enforces single-defn prompts

**Benefit:** Broader applicability beyond the MultiPL-E benchmark, which is single-defn
by construction.

## 3. RLVR Completeness Reward

Current shaped reward covers: `syntax` → `kondo` → `load` → `tests`. A completeness
reward could ensure generated code doesn't get truncated.

**Approach:**
- Add a `balanced_parens` reward component: check that all opened parens are closed
- Weight: half the syntax reward (completeness is less important than correctness)
- Penalize generation that ends mid-expression more heavily than balanced-but-wrong code

**Benefit:** Reduces partial outputs where the model generates valid code up to max_tokens
but doesn't close all forms.

## 4. Chat Template Consistency

**Key finding:** Both SFT (`training/sft/train.py:93-94`) and RLVR (`training/rlvr/train.py:188`)
use raw `tokenizer.encode()` without chat template. The tinker-clojure extension now
defaults to `use_chat_template: false` to match.

**Future consideration:**
- If switching to chat-template-based training, ensure inference matches training
- The `use_chat_template` config flag allows per-checkpoint control
- Document the tokenization mode in each checkpoint's metadata

## 5. extract_code Fix Needed in Other Pipelines

The `rfind("(defn")` bug fixed in `tinker_bridge.py` also exists in:

- **`scripts/best_of_k.py`** (lines 55-68): Uses same pattern on combined prompt+generation.
  Should be updated to search generated text first, then fall back to combined.
- **`training/rlvr/train.py`**: If it extracts code from model outputs, same bug may apply.

**Recommended fix:** Extract the corrected `extract_code` function into a shared module
(e.g., `src/learn/extract.py`) and import from all consumers.
