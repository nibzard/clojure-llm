(defn nth-positive
  "Return a lazy sequence of the n-th powers of the positive numbers in `nums`.

  Non-numeric values and nils are ignored.
  Negative numbers are excluded.
  Works with finite or infinite sequences.

  Examples:
  (nth-positive [1 -2 3 nil 4] 2) ;=> (1 9 16)
  (take 3 (nth-positive (range) 3)) ;=> (1 8 27)"
  [nums n])