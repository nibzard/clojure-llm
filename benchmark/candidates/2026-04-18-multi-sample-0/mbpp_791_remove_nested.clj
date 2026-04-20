(defn remove_nested
  "	Write a function to remove vectors from the given vector."
  [test_tup]
  (vec (remove vector? test_tup)))