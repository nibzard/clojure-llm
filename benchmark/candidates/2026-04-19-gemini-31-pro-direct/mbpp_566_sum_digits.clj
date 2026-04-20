(defn sum_digits
  "Write a function to get the sum of the digits of a non-negative integer."
  [n]
  (if n
    (reduce + (keep #(let [d (Character/digit % 10)]
                       (when (pos? d) d))
                    (str n)))
    0))