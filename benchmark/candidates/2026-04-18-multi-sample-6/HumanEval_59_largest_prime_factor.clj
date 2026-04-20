(defn largest_prime_factor
  "Return the largest prime factor of n. Assume n > 1 and is not a prime.
  >>> (largest_prime_factor 13195)
  29
  >>> (largest_prime_factor 2048)
  2"
  [n]
  (loop [n n
         d 2
         largest nil]
    (cond
      (= n 1) largest
      (> (* d d) n) n
      (zero? (rem n d)) (recur (/ n d) d d)
      (= d 2) (recur n 3 largest)
      :else (recur n (+ d 2) largest))))