(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (letfn [(combine-two [a b]
            (vec
             (for [x (or a [])
                   y (or b [])]
               (+ x y))))]
    (cond
      (nil? test_list) []
      (empty? test_list) []
      :else
      (reduce combine-two test_list))))