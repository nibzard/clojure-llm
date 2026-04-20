(defn sum-negative-indexed
  "Return the sum of the values at odd indexes (1-based positions 2, 4, 6, ...)
in a collection. Ignore nil values.

Examples:
(sum-negative-indexed [1 2 3 4 5]) => 6
(sum-negative-indexed [10 nil 20 30]) => 30
(sum-negative-indexed []) => 0"
  [nums])