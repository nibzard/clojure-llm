(defn sort-subvectors
  "Return a new vector of vectors where each subvector is sorted in ascending order.

  Handles nil entries by leaving them unchanged.

  Examples:
  (sort-subvectors [[3 1 2] [9 7] nil [4]])
  => [[1 2 3] [7 9] nil [4]]"
  [v])