(defn find_combinations
  "Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (let [pairs (for [v1 test_list
                    v2 test_list
                    :when (and (vector? v1) (vector? v2))]
                (vec (map + v1 v2)))]
    (vec (distinct pairs))))