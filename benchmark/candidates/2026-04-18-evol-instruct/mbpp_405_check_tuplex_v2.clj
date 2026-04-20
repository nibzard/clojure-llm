(defn contains-subvector?
  "Return true if `subvec` appears as a contiguous subsequence inside vector `v`.
  Return false for nil inputs or if `subvec` is longer than `v`.

  Examples:
  (contains-subvector? [2 3] [1 2 3 4]) ;=> true
  (contains-subvector? [3 5] [1 2 3 4]) ;=> false
  (contains-subvector? [] [1 2 3])      ;=> true"
  [subvec v]
  (cond
    (nil? subvec) false
    (nil? v) false
    (empty? subvec) true
    (> (count subvec) (count v)) false
    :else
    (boolean
      (some #(= subvec (vec (take (count subvec) (drop % v))))
            (range (inc (- (count v) (count subvec))))))))