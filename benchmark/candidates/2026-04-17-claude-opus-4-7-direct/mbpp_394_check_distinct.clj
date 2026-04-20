(defn check_distinct
  "Write a function to check if given list contains no duplicates."
  [test_tup]
  (if (empty? test_tup)
    true
    (= (count test_tup) (count (set test_tup)))))