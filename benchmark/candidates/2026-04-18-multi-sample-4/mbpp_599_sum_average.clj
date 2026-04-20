(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (long (or number 0))]
    (if (pos? n)
      (let [sum (/ (* n (inc n)) 2)]
        [sum (/ sum n)])
      [0 0])))