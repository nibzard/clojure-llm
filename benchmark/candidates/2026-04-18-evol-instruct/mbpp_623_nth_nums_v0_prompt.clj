(defn nth-nums
  "Return a lazy sequence of the n-th powers of the numbers in `nums`.

  Works with finite or infinite sequences and preserves laziness.

  Examples:
  (take 4 (nth-nums [1 2 3 4] 2)) ;=> (1 4 9 16)
  (take 3 (nth-nums (range) 3))   ;=> (0 1 8)"
  [nums n])