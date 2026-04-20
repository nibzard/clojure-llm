(defn is_not_prime
  "	Write a cljthon function to identify non-prime numbers."
  [n]
  (cond
    (nil? n) true
    (not (integer? n)) true
    (< n 2) true
    (= n 2) false
    (even? n) true
    :else
    (let [limit (long (Math/sqrt n))]
      (boolean
       (some #(zero? (rem n %))
             (range 3 (inc limit) 2))))))