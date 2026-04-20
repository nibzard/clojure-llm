(defn starts_one_ends
  "Given a positive integer n, return the count of the numbers of n-digit
  positive integers that start or end with 1."
  [n]
  (let [pow (reduce *' 1 (repeat (max 0 (dec n)) 10))]
    (+ pow
       pow
       (- (reduce *' 1 (repeat (max 0 (- n 2)) 10))))))