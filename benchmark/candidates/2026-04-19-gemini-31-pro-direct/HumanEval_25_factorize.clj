(defn factorize
  "Return list of prime factors of given integer in the order from smallest to largest.
  Each of the factors should be listed number of times corresponding to how many times it appeares in factorization.
  Input number should be equal to the product of all factors"
  [n]
  (loop [n n
         d 2
         acc []]
    (cond
      (<= n 1) acc
      (> (* d d) n) (conj acc n)
      (zero? (rem n d)) (recur (quot n d) d (conj acc d))
      :else (recur n (inc d) acc))))