(defn sum_negativenum
  "	Write a function to calculate the sum of the negative numbers of a given list of numbers."
  [nums]
  (reduce (fn [sum n]
            (if (neg? n)
              (+ sum n)
              sum))
          0
          nums))