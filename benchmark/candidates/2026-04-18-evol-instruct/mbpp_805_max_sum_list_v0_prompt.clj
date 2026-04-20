(defn max_sum-subseq
  "Given a possibly infinite sequence of numbers, return the first contiguous subsequence
  of length at least 1 whose sum is the highest.

  If multiple subsequences tie for the highest sum, return the earliest one.

  Examples:
  (max_sum-subseq [1 -2 3 4 -1 2]) ;=> [3 4 -1 2]
  (max_sum-subseq [-5 -1 -3])      ;=> [-1]
  (max_sum-subseq nil)             ;=> nil"
  [nums])