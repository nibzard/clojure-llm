(defn kth-from-sorted
  "Return the k-th smallest element from two sorted sequences.

  The inputs may be lazy or infinite, and either input may be nil.
  `k` is zero-based.

  Examples:
  (kth-from-sorted [1 4 9] [2 3 8] 0) => 1
  (kth-from-sorted [1 4 9] [2 3 8] 4) => 4
  (kth-from-sorted nil [10 20 30] 1) => 20
  (kth-from-sorted (range) [100 200] 5) => 5"
  [xs ys k])