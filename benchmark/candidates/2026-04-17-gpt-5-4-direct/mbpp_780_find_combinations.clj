(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (letfn [(combine-two [a b]
            (vec
             (for [x a
                   y b]
               (+ x y))))
          (step [acc v]
            (if (nil? acc)
              (vec v)
              (combine-two acc v)))]
    (if (seq test_list)
      (reduce step nil test_list)
      [])))