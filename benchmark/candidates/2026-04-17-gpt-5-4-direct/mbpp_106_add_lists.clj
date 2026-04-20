(defn add_lists
  "	Write a function to append the given list to the given vectors."
  [test_list test_tup]
  (vec (concat (or test_tup []) (or test_list []))))