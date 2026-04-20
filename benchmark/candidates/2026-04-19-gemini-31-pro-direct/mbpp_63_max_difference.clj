(defn max_difference
  "Write a function to find the maximum difference between available pairs in the given vector list."
  [test_list]
  (when (next test_list)
    (- (reduce max test_list) (reduce min test_list))))