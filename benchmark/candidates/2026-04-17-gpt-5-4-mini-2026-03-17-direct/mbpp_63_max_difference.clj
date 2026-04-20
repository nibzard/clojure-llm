(defn max_difference
  "	Write a function to find the maximum difference between available pairs in the given vector list."
  [test_list]
  (when (seq test_list)
    (let [vals (mapcat identity test_list)
          mn (apply min vals)
          mx (apply max vals)]
      (- mx mn))))