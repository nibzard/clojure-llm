(defn sum_average
  "	Write a function to find sum and average of first n natural numbers."
  [number]
  (let [n (when (number? number) (long number))]
    (if (and (integer? n) (pos? n))
      [(quot (* n (inc n)) 2) (/ (double (* n (inc n))) 2.0)]
      [0 0.0])))