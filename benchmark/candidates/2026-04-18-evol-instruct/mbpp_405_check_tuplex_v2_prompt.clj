(defn contains-subvector?
  "Return true if `subvec` appears as a contiguous subsequence inside vector `v`.
  Return false for nil inputs or if `subvec` is longer than `v`.

  Examples:
  (contains-subvector? [2 3] [1 2 3 4]) ;=> true
  (contains-subvector? [3 5] [1 2 3 4]) ;=> false
  (contains-subvector? [] [1 2 3])      ;=> true"
  [subvec v])