(defn cummulative_sum
  "Write a function to find the cumulative sum of all the values that are present in the given list of lists."
  [test_list]
  (reduce + 0 (mapcat #(or % []) (or test_list []))))