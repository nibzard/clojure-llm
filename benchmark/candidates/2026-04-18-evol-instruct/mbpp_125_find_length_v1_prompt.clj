(defn max-zero-one-diff
  "Return the maximum difference between the number of 0s and 1s in any contiguous subvector of a binary vector.

  The input may contain integers 0 and 1, and it may be empty.
  If the vector is empty, return 0.

  Examples:
  (max-zero-one-diff [0 0 1 0 1]) ;=> 2
  (max-zero-one-diff [1 1 1])     ;=> 0
  (max-zero-one-diff [])          ;=> 0"
  [xs])