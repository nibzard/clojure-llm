(defn min_k
  "Write a function to find minimum k records from vector list."
  [test_list K]
  (take K (sort test_list)))