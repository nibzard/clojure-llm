(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (long (or number 0))
        sum (if (pos? n) (/ (* n (inc n)) 2) 0)
        avg (if (pos? n) (/ sum n) 0)]
    [sum avg]))