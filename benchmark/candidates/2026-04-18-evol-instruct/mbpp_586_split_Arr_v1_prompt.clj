(defn rotate-left
  "Rotate a vector left by n positions. Negative n rotates right.
  Return an empty vector for nil input.

  Examples:
  (rotate-left [1 2 3 4] 1)   ;=> [2 3 4 1]
  (rotate-left [1 2 3 4] 6)   ;=> [3 4 1 2]
  (rotate-left [1 2 3 4] -1)  ;=> [4 1 2 3]
  (rotate-left nil 3)         ;=> []"
  [v n])