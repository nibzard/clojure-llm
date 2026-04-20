(defn max-of-nth
  "Given a possibly lazy sequence of rows, return the maximum value in the n-th
  column across all rows.

  Rows may be vectors or lists. If a row is too short, it is ignored.
  Return nil when no row contains an n-th element.

  Examples:
  (max-of-nth [[1 2 3] [4 5 6] [7 8 9]] 1) ;=> 8
  (max-of-nth '([10 20] [3] [7 15]) 1)     ;=> 20
  (max-of-nth [[1] [] [2]] 1)              ;=> nil"
  [rows n])