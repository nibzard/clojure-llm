(defn largest_prime_factor
  "Return the largest prime factor of n. Assume n > 1 and is not a prime.
  >>> (largest_prime_factor 13195)
  29
  >>> (largest_prime_factor 2048)
  2"
  [n]
  (loop [n n
         factor 2
         last-factor 1]
    (cond
      (> (* factor factor) n) (max n last-factor)
      (zero? (mod n factor)) (recur (/ n factor) factor factor)
      (= factor 2) (recur n 3 last-factor)
      :else (recur n (+ factor 2) last-factor))))