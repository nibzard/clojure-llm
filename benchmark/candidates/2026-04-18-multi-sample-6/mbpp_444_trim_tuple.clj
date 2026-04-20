(defn trim_tuple
  "	Write a function to trim each list by k in the given lists."
  [test_list K]
  (mapv #(vec (drop K %)) (or test_list [])))