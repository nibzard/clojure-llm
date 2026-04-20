(defn vector-sum-subsets
  "Return all subsets of the input vector whose elements sum to the target.
  Preserve the original order of elements within each subset.

  Examples:
  (vector-sum-subsets [1 2 3 4] 5) => [[1 4] [2 3]]
  (vector-sum-subsets [2 2 2] 4) => [[2 2] [2 2] [2 2]]"
  [v target])