(defn is_not_prime
  "	Write a cljthon function to identify non-prime numbers."
  [n]
  (or (not (integer? n))
      (< n 2)
      (and (> n 2)
           (or (even? n)
               (let [limit (long (Math/sqrt n))]
                 (boolean
                  (some #(zero? (mod n %))
                        (range 3 (inc limit) 2))))))))