(defn positive_count
  "	Write a function to find the ration of positive numbers in a vector of integers."
  [nums]
  (let [nums (or nums [])
        total (count nums)]
    (if (zero? total)
      0
      (/ (count (filter pos? nums)) total))))