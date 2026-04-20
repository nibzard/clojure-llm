(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (max 0 (long (or number 0)))]
    (if (zero? n)
      {:sum 0 :average 0.0}
      {:sum (/ (* n (inc n)) 2)
       :average (/ (inc n) 2)})))