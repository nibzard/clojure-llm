(defn rotate-left
  "Rotate a vector left by n positions. If n is negative, rotate right.
Returns an empty vector unchanged. Supports n larger than the vector length.

Examples:
(rotate-left [1 2 3 4] 1) => [2 3 4 1]
(rotate-left [1 2 3 4] -1) => [4 1 2 3]
(rotate-left [] 3) => []"
  [v n]
  (let [cnt (count v)]
    (if (zero? cnt)
      []
      (let [k (mod n cnt)]
        (vec (concat (subvec (vec v) k) (subvec (vec v) 0 k)))))))