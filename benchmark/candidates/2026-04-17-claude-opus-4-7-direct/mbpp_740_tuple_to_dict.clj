(defn tuple_to_dict
  "Write a function to convert the given vector to a key-value map using adjacent elements."
  [test_tup]
  (when (and test_tup (even? (count test_tup)))
    (apply hash-map test_tup)))