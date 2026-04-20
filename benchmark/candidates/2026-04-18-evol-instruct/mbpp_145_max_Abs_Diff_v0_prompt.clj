(defn max-abs-diff-pairs
  "Return all pairs of elements from a vector that achieve the maximum absolute difference.

  The function should return a set of 2-element vectors, each representing a pair [a b]
  where (Math/abs (- a b)) is maximal among all pairs in the input.

  Examples:
  (max-abs-diff-pairs [1 5 3 9])
  ;; => #{[1 9] [9 1]}

  (max-abs-diff-pairs [4 4 4])
  ;; => #{[4 4]}"

  [arr])