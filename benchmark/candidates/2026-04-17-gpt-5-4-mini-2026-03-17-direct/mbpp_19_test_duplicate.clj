(defn test_duplicate
  "	Write a function to find whether a given vector of integers contains any duplicate element."
  [arraynums]
  (not= (count arraynums) (count (distinct arraynums))))