(defn check_distinct
  "	Write a function to check if given list contains no duplicates."
  [test_tup]
  (= (count test_tup) (count (distinct test_tup))))