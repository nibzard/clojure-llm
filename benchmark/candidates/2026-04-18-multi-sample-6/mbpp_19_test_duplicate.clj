(defn test_duplicate
  "	Write a function to find whether a given vector of integers contains any duplicate element."
  [arraynums]
  (let [nums (or arraynums [])]
    (not= (count nums) (count (distinct nums)))))