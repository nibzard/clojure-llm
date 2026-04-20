(defn add_dict_to_tuple
  "	Write a function to add a map to the vector. The output should be a vector."
  [test_tup test_dict]
  (vec (concat (or test_tup []) [(or test_dict {})])))