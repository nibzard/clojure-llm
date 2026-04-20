(defn pairs_sum_to_zero
  "pairs_sum_to_zero takes a list of integers as an input.
  it returns true if there are two distinct elements in the list that
  sum to zero, and false otherwise."
  [l]
  (loop [s (set l) [x & xs] l]
    (cond
      (nil? x) false
      (contains? s (- x)) true
      :else (recur s xs))))