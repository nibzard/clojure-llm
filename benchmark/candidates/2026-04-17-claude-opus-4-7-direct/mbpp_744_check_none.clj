(defn check_none
  "Write a function to check if the given vector has any none value or not."
  [test_tup]
  (if (nil? test_tup)
    false
    (not (every? some? test_tup))))