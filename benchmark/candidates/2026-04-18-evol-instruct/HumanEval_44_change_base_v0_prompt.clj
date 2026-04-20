(defn change-base-digits
  "Convert a number x to the given base and return a vector of digits.
  The result should use the most-significant digit first.

  Examples:
  (change-base-digits 8 3) => [2 2]
  (change-base-digits 8 2) => [1 0 0 0]
  (change-base-digits 7 2) => [1 1 1]

  The function should handle x = 0 by returning [0]."
  [x base])