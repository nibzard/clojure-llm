(defn cube-nums
  "Return a lazy sequence of cubes for the numbers in `nums`.

  Works with finite or infinite sequences.

  Examples:
  (take 4 (cube-nums [1 2 3 4]))
  ;; => (1 8 27 64)

  (take 3 (cube-nums (range)))
  ;; => (0 1 8)"
  [nums])