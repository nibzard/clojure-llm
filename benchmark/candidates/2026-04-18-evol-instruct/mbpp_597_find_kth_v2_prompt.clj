(defn kth-merged
  "Return the k-th smallest element from two sorted sequences without fully realizing them.
  k is 1-based. Accepts any sorted seqs (including lazy or infinite ones).
  
  Examples:
  (kth-merged [1 3 5] [2 4 6] 4) => 4
  (kth-merged [1 2 3] [10 20 30] 5) => 20
  (kth-merged [1 4 7] [2 3 5 6] 1) => 1"
  [s1 s2 k])