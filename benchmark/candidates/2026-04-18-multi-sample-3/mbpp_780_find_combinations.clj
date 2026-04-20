(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (let [vectors (or test_list [])]
    (if (empty? vectors)
      []
      (reduce (fn [acc v]
                (for [a acc
                      b v]
                  (conj (vec a) b)))
              [[]]
              vectors))))