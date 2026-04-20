(defn nth-nonconsecutive
  "Return the nth nonconsecutive number (0-indexed) represented as a sum of nonconsecutive Fibonacci numbers.

  The sequence starts:
  0 -> 0
  1 -> 1
  2 -> 2
  3 -> 4
  4 -> 5
  5 -> 7

  Examples:
  (nth-nonconsecutive 0) => 0
  (nth-nonconsecutive 5) => 7

  Handle nil by returning nil."
  [n])