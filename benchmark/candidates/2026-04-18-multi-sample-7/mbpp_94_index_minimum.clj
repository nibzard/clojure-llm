(defn index_minimum
  "Given a list of vectors, write a function that returns the first value of the vector with the smallest second value."
  [test_list]
  (when (seq test_list)
    (first (reduce (fn [min-v v]
                     (if (< (second v) (second min-v))
                       v
                       min-v))
                   test_list))))