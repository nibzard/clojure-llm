(defn sum_to_n
  "sum_to_n is a function that sums numbers from 1 to n."
  [n]
  (if (pos? n)
    (/ (* n (inc n)) 2)
    0))