(defn geometric_sum
  "Write a function to calculate the geometric sum of n-1."
  [n]
  (if (<= n 0)
    0
    (+ (/ 1 (Math/pow 2 n)) (geometric_sum (dec n)))))