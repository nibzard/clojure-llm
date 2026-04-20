(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (long number)
        s (/ (* n (inc n)) 2)
        a (/ s n)]
    [s a]))