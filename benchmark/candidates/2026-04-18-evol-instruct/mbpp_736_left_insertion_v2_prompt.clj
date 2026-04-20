(defn right-insertion
  "Return the index where x should be inserted in sorted order so that it appears to the right of any existing equal values.

Examples:
  (right-insertion [1 2 2 2 3] 2) => 4
  (right-insertion [1 3 5] 4) => 2
  (right-insertion [] 10) => 0

Works with any sorted sequential collection, including vectors and lists."
  [coll x])