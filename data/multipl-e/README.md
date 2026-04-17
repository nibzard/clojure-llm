# MultiPL-E Data for Clojure LLM

## Overview

MultiPL-E is a benchmark for evaluating large language models on code generation across multiple programming languages. For this project, we use the Clojure variants of HumanEval and MBPP.

## Repository

- **GitHub:** https://github.com/nuprl/MultiPL-E
- **Paper:** "MultiPL-E: A Benchmark and Dataset for Multi-Language Code Generation" (2023)

## Required Files

From the MultiPL-E repository, download the following files from the `data/` directory:

1. **humaneval-clj.json** - HumanEval translated to Clojure (~164 problems)
2. **mbpp-clj.json** - MBPP (Mostly Basic Python Problems) translated to Clojure (~374 problems)

## Download Instructions

```bash
# Clone the MultiPL-E repository
git clone https://github.com/nuprl/MultiPL-E.git /tmp/multipl-e
cd /tmp/multipl-e

# Copy the Clojure data files
cp data/humaneval-clj.json /home/agent/clojure-llm/data/multipl-e/
cp data/mbpp-clj.json /home/agent/clojure-llm/data/multipl-e/

# Clean up
cd /home/agent/clojure-llm
rm -rf /tmp/multipl-e
```

## Data Format

Each JSON file contains an array of tasks with the following structure:

```json
{
  "task_id": "HumanEval_0",
  "prompt": "Write a function that...",
  "canonical_solution": "(defn has-close-elements ...)",
  "test": "(deftest test-HumanEval_0 ...)",
  "entry_point": "has_close_elements",
  "language": "clojure"
}
```

## Usage in Pipeline

The `data/curate.clj` pipeline processes these files:

```bash
clojure -M -m learn.curate multipl-e \
  data/multipl-e/humaneval-clj.json \
  data/multipl-e/mbpp-clj.json \
  data/sft/multipl-e.jsonl
```

## License

MultiPL-E data is released under the MIT License (SPDX: MIT). The original HumanEval and MBPP datasets have their own licensing terms which are compatible with research use.

## References

- nuprl/MultiPL-E GitHub: https://github.com/nuprl/MultiPL-E
- HumanEval Paper: "Evaluating Large Language Models Trained on Code" (Chen et al., 2021)
- MBPP Paper: "Program Synthesis with Large Language Models" (Austin et al., 2021)
