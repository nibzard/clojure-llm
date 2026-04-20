(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (let [v (vec (or test_list []))
        n (count v)]
    (if (zero? n)
      []
      (letfn [(combine [a b]
                (vec (for [x a
                           y b]
                       (+ x y))))
              (step [idx]
                (if (= idx n)
                  [[]]
                  (let [rest-combos (step (inc idx))]
                    (mapcat (fn [r]
                              (map #(into [%] r) (v idx)))
                            rest-combos))))]
        (->> (step 0)
             (mapv #(reduce + 0 %)))))))