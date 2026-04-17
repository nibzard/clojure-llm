# 4Clojure Data for Clojure LLM

## Overview

4Clojure is a collection of Clojure problems for learning and practice. It contains ~500 problems ranging from elementary to difficult, each with example-based tests.

## Repository

- **GitHub:** https://github.com/4clojure/4clojure
- **Website:** https://4clojure.oxalide.net/

## Data Extraction

The 4Clojure repository contains problems in a database format. For the curation pipeline, we need to extract and convert these to EDN format.

## Download Instructions

```bash
# Clone the 4clojure repository
git clone https://github.com/4clojure/4clojure.git /tmp/4clojure

# Extract problems from the database
# The problems are stored as resources in the webapp
# For manual extraction, use the web interface or parse the resources
```

## Data Format

After extraction, convert to this EDN format:

```clojure
{:id 1
 :title "Nothing but the Truth"
 :description "Write a function that always returns true."
 :difficulty "Elementary"
 :tags [:intro :basics]
 :tests '((= true (solution)))
 :restricted '()}  ; functions that cannot be used

{:id 2
 :title "Simple Math"
 :description "Write a function that adds two numbers."
 :difficulty "Elementary"
 :tags [:math]
 :tests '((= 4 (solution 2 2))
          (= 7 (solution 3 4)))
 :restricted '(+)}
```

## Converting Example Tests to clojure.test

The 4clojure format uses example-based tests like:

```clojure
((= true (solution 1 2))
 (= false (solution 1 1)))
```

The curation pipeline converts these to proper `clojure.test` format:

```clojure
(deftest test-4clojure-42
  (is (= true (solution 1 2)))
  (is (= false (solution 1 1))))
```

## Usage in Pipeline

```bash
# After extracting to EDN format
clojure -M -m learn.curate 4clojure \
  data/4clojure/4clojure.edn \
  data/sft/4clojure.jsonl
```

## Extraction Script

A helper script to extract problems from the 4clojure repository:

```bash
# From the 4clojure repo root
# Problems are in resources/problems/ directory
# Each file contains problem data in EDN format already

# Copy all problem files
cp -r resources/problems/ /home/agent/clojure-llm/data/4clojure/
```

## License

4Clojure is released under the Eclipse Public License (EPL). The problems themselves are freely usable for educational and research purposes.

## Problem Categories

- **Elementary:** Introductory problems
- **Easy:** Basic language features
- **Medium:** Intermediate techniques
- **Hard:** Advanced Clojure idioms

## References

- 4clojure GitHub: https://github.com/4clojure/4clojure
- 4clojure Website: https://4clojure.oxalide.net/
