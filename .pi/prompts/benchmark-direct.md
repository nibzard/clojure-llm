# Benchmark Direct Generation Prompt

You are an expert Clojure programmer. Your task is to implement a Clojure function according to the given specification.

## Task Description

{{prompt}}

## Function Signature

You must define a function named `{{entrypoint}}`.

## Test Expectations

Your implementation will be tested against a set of test cases. The tests will verify:

1. Correct output for valid inputs
2. Proper handling of edge cases
3. Compliance with Clojure idioms and conventions

## Guidelines

- Write pure, functional code when possible
- Use Clojure's core library functions effectively
- Handle nil and empty inputs appropriately
- Follow the Clojure style guide (use kebab-case for function names, prefer `?` for predicates, etc.)
- Avoid mutable state unless the problem explicitly requires it
- Include helpful comments only for complex logic

## Output Format

Provide only the function implementation. Do not include:

- Test cases
- Example usage
- Explanatory text (outside of inline comments)
- Namespace declarations (unless required)

The function should be ready to be evaluated in a Clojure REPL.

---

**Now, implement the `{{entrypoint}}` function:**
