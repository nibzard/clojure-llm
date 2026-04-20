(defn factorize
  " Return list of prime factors of given integer in the order from smallest to largest.
  Each of the factors should be listed number of times corresponding to how many times it appeares in factorization.
  Input number should be equal to the product of all factors
  >>> (factorize 8)
  [2 2 2]
  >>> (factorize 25)
  [5 5]
  >>> (factorize 70)
  [2 5 7]"
  [n]
  (let [n (long n)]
    (cond
      (<= n 1) []
      :else
      (loop [n n
             d 2
             acc []]
        (cond
          (= n 1) acc
          (> (* d d) n) (conj acc n)
          (zero? (mod n d)) (recur (/ n d) d (conj acc d))
          (= d 2) (recur n 3 acc)
          :else (recur n (+ d 2) acc))))))