(defn max_difference
  "	Write a function to find the maximum difference between available pairs in the given vector list."
  [test_list]
  (when (seq test_list)
    (let [[mn mx] (reduce (fn [[mn mx] x]
                            [(min mn x) (max mx x)])
                          [(first test_list) (first test_list)]
                          (rest test_list))]
      (- mx mn))))