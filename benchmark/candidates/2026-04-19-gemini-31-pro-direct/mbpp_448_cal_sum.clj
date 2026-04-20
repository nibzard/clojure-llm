(defn cal_sum
  "Write a function to calculate the sum of perrin numbers."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (reduce + (take n (map first (iterate (fn [[a b c]] [b c (+ a b)]) [3 0 2]))))))