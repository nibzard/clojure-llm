(defn left-rotate-by
  "Rotate a collection left by d positions.

  Works on any sequential collection, including lists, vectors, and empty collections.
  Negative d rotates to the right.

  Examples:
  (left-rotate-by [1 2 3 4 5] 2) => [3 4 5 1 2]
  (left-rotate-by '(a b c d) 1)   => (b c d a)
  (left-rotate-by [] 3)          => []
  (left-rotate-by [1 2 3] -1)    => [3 1 2]"
  [coll d])