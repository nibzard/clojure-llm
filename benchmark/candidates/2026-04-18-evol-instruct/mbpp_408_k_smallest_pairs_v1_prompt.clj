(defn kth-smallest-sums
  "Return the k smallest pair sums formed by taking one element from each of two vectors.
  
  The result is a vector of maps with keys :a, :b, and :sum, ordered from smallest sum
  to largest sum among the returned items.
  
  Handles nil or empty inputs by returning [].
  
  Examples:
  (kth-smallest-sums [1 7 11] [2 4 6] 3)
  ;; => [{:a 1, :b 2, :sum 3}
  ;;     {:a 1, :b 4, :sum 5}
  ;;     {:a 1, :b 6, :sum 7}]
  
  (kth-smallest-sums nil [1 2] 2)
  ;; => []"
  [xs ys k])