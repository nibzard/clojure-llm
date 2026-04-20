(defn is_not_prime
  "	Write a cljthon function to identify non-prime numbers."
  [n]
  (if (and (integer? n) (> n 1))
    (let [limit (long (Math/sqrt n))]
      (boolean
       (some #(zero? (mod n %))
             (range 2 (inc limit)))))
    true))