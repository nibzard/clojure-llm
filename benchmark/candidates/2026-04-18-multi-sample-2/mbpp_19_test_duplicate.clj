(defn test_duplicate
  "	Write a function to find whether a given vector of integers contains any duplicate element."
  [arraynums]
  (let [xs (or arraynums [])]
    (not= (count xs) (count (distinct xs)))))