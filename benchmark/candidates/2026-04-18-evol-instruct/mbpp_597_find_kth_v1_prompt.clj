(defn kth-merge
  "Return the kth smallest element from two sorted sequences without fully realizing them.
  k is 1-based. Works with vectors, lists, and infinite sorted sequences.

  Examples:
  (kth-merge [1 3 5] [2 4 6] 4) => 4
  (kth-merge [1 2] [3 4 5] 5) => 5
  (kth-merge (range) [10 20 30] 3) => 2"
  [xs ys k])