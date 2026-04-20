(defn check_none
  "	Write a function to check if the given vector has any none value or not."
  [test_tup]
  (boolean (some nil? test_tup)))