(defn check_distinct
  "	Write a function to check if given list contains no duplicates."
  [test_tup]
  (let [s (seq test_tup)]
    (= (count s) (count (set s)))))