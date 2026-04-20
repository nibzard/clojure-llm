(defn max-zero-one-diff
  "Return the maximum difference between the number of 0s and 1s in any contiguous subsequence of a vector of integers.

  Treat 0 as +1 and 1 as -1, and return the best possible sum over all non-empty contiguous sub-vectors.

  Examples:
  (max-zero-one-diff [0 1 0 0 1]) ;=> 2
  (max-zero-one-diff [1 1 1])     ;=> -1
  (max-zero-one-diff [])          ;=> nil"
  [nums])