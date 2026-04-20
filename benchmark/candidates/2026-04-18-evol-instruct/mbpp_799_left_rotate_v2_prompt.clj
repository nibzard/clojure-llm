(defn left-rotate-vector
  "Return a new vector with the elements rotated left by d positions.

  Supports any vector length, including 0 and 1. Negative d rotates right.

  Examples:
  (left-rotate-vector [1 2 3 4 5] 2) => [3 4 5 1 2]
  (left-rotate-vector [1 2 3 4 5] -1) => [5 1 2 3 4]
  (left-rotate-vector [] 3) => []
  "
  [v d])