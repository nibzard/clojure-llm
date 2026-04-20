(defn odd-collatz-counts
  "Given a positive integer n, return a map of every odd number encountered in the Collatz sequence
  starting at n to the number of times it appears.

  Rules:
  - The Collatz sequence is defined as:
    - if the current number is even, next is half the current number
    - if the current number is odd, next is 3 * current number + 1
  - Include the starting number if it is odd.
  - Continue until reaching 1.
  - Return nil for non-positive inputs.

  Examples:
  (odd-collatz-counts 1) => {1 1}
  (odd-collatz-counts 5) => {1 1, 5 1}
  (odd-collatz-counts 13) => {1 1, 5 1, 13 1, 17 1, 31 1, 41 1}
  "
  [n])