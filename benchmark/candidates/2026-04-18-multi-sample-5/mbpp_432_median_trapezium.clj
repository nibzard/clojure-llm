(defn median_trapezium
  "	Write a function to find the median length of a trapezium."
  [base1 base2 height]
  (when (and (number? base1) (number? base2))
    (/ (+ base1 base2) 2)))