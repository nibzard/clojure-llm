(defn sum_average
  "Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (max 0 number)
        sum (/ (* n (inc n)) 2)
        avg (if (zero? n) 0 (/ sum n))]
    {:sum sum :average avg}))