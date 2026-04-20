(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (when (and (number? number) (pos? number))
    (let [n number
          sum (/ (* n (inc n)) 2)
          avg (double (/ sum n))]
      [sum avg])))