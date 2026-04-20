(defn geometric_sum
  "Write a function to calculate the geometric sum of n-1."
  [n]
  (letfn [(sum-up-to [k]
            (if (<= k 0)
              0
              (+ (/ 1.0 (Math/pow 2 k))
                 (sum-up-to (dec k)))))]
    (sum-up-to (dec n))))