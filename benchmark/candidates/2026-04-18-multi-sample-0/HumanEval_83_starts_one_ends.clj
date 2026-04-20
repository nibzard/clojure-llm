(defn starts_one_ends
  "Given a positive integer n, return the count of the numbers of n-digit
  positive integers that start or end with 1."
  [n]
  (if (= n 1)
    1
    (+ (* 10N (reduce *' (repeat (- n 2) 10N)))
       (* 8N (reduce *' (repeat (- n 2) 10N))))))