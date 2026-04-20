(defn remove_nested
  "	Write a function to remove vectors from the given vector."
  [test_tup]
  (->> test_tup
       (remove vector?)
       vec))