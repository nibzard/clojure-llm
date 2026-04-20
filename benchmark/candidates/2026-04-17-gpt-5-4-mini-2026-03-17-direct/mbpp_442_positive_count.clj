(defn positive_count
  "Write a function to find the ration of positive numbers in a vector of integers."
  [nums]
  (let [n (count nums)]
    (if (zero? n)
      0
      (/ (count (filter pos? nums)) n))))