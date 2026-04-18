(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (when (and (number? number) (pos? number))
    {:sum (/ (* number (inc number)) 2)
     :average (/ (inc number) 2)}))