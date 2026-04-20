(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (long (or number 0))
        sum (/ (* n (inc n)) 2)
        avg (if (zero? n) 0 (/ sum n))]
    [sum avg]))