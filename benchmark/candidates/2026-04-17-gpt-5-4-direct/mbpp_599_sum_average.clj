(defn sum_average
  "Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (if (and (number? number) (pos? number))
            (long number)
            0)
        sum (/ (* n (inc n)) 2)
        avg (if (pos? n) (/ sum n) 0)]
    [sum avg]))