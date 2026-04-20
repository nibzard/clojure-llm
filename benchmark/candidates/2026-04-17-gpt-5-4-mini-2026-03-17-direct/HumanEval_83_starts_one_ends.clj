(defn starts_one_ends
  "Given a positive integer n, return the count of the numbers of n-digit
  positive integers that start or end with 1."
  [n]
  (cond
    (nil? n) 0
    (= n 1) 1
    :else (+ (long (Math/pow 10 (dec n)))
             (long (Math/pow 10 (dec n)))
             -1)))