(defn rotate-right
  "Return a vector with the elements of coll rotated right by n positions.

  Works with any sequential collection, treats nil as empty, and supports
  negative n values by rotating left.

  Examples:
  (rotate-right [1 2 3 4] 1)   ;=> [4 1 2 3]
  (rotate-right [1 2 3 4] 3)   ;=> [2 3 4 1]
  (rotate-right [1 2 3 4] -1)  ;=> [2 3 4 1]
  (rotate-right nil 2)         ;=> []"
  [coll n]
  (let [v (vec (or coll []))
        c (count v)]
    (if (zero? c)
      []
      (let [k (mod n c)]
        (vec (concat (subvec v (- c k)) (subvec v 0 (- c k))))))))