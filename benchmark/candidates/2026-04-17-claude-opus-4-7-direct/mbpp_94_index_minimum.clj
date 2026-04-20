(defn index_minimum
  "Given a list of vectors, write a function that returns the first value of the vector with the smallest second value."
  [test_list]
  (when (seq test_list)
    (ffirst (sort-by second test_list))))