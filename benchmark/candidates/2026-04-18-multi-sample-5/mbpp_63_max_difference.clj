(defn max_difference
  "	Write a function to find the maximum difference between available pairs in the given vector list."
  [test_list]
  (when (seq test_list)
    (let [mn (apply min test_list)
          mx (apply max test_list)]
      (- mx mn))))