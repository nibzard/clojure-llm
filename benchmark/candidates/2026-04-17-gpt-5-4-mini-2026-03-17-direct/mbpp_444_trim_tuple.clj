(defn trim_tuple
  "	Write a function to trim each list by k in the given lists."
  [test_list K]
  (map #(take K %) test_list))