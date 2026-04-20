(defn check_distinct
  "	Write a function to check if given list contains no duplicates."
  [test_tup]
  (or (nil? test_tup)
      (= (count test_tup) (count (set test_tup)))))