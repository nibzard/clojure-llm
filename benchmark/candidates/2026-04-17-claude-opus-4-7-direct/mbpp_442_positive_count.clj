(defn positive_count
  "Write a function to find the ration of positive numbers in a vector of integers."
  [nums]
  (if (empty? nums)
    0.0
    (/ (count (filter pos? nums)) (count nums))))