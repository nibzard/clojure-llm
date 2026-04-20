(defn index_minimum
  "Given a list of vectors, write a function that returns the first value of the vector with the smallest second value."
  [test_list]
  (when-let [v (first (sort-by second test_list))]
    (first v)))