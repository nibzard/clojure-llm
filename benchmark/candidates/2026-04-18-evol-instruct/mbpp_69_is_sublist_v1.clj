(defn is-contiguous-subvector?
  "Return true if `subvec` appears as a contiguous subsequence of `v`.

  Works with vectors, lists, nil, and empty inputs.

  Examples:
  (is-contiguous-subvector? [1 2 3 4] [2 3]) ;=> true
  (is-contiguous-subvector? [1 2 3 4] [2 4]) ;=> false
  (is-contiguous-subvector? [1 2 3] [])      ;=> true
  (is-contiguous-subvector? nil [1])         ;=> false"
  [v subvec]
  (let [v (or v [])
        subvec (or subvec [])]
    (cond
      (empty? subvec) true
      (empty? v) false
      :else
      (boolean
       (some #(= subvec %)
             (partition (count subvec) 1 v))))))