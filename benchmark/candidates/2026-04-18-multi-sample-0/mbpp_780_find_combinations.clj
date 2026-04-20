(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (letfn [(sums [v]
            (if (empty? v)
              [0]
              (reduce (fn [acc x]
                        (mapcat #[(+ % x) %] acc))
                      [0]
                      v)))]
    (if (or (nil? test_list) (empty? test_list))
      []
      (vec (map sums test_list)))))