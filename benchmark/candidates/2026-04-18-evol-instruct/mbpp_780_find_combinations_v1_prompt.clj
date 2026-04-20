(defn vector-subset-sum
  "Return the first subset of the given vector whose values sum to target.

  The input is a vector of integers. Search subsets in increasing length order,
  and within the same length, preserve the original left-to-right order.

  Return a vector containing the matching subset, or nil if no subset exists.

  Examples:
  (vector-subset-sum [1 2 3 4] 5) => [1 4]
  (vector-subset-sum [2 4 6] 5) => nil
  (vector-subset-sum [] 0) => []"
  [nums target])