(defn is_not_prime
  "	Write a cljthon function to identify non-prime numbers."
  [n]
  (let [n (if (integer? n) n nil)]
    (cond
      (nil? n) true
      (< n 2) true
      (= n 2) false
      (even? n) true
      :else
      (let [limit (long (Math/sqrt n))]
        (boolean
         (some #(zero? (mod n %))
               (range 3 (inc limit) 2)))))))