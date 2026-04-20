(defn tuple_to_dict
  "Write a function to convert the given vector to a key-value map using adjacent elements."
  [test_tup]
  (into {} (partition 2 test_tup)))