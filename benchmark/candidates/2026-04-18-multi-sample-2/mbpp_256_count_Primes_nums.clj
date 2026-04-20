(defn count_Primes_nums
  "	Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (let [n (long (or n 0))]
    (if (<= n 2)
      0
      (let [limit (dec n)
            prime? (fn [x]
                     (cond
                       (< x 2) false
                       (= x 2) true
                       (even? x) false
                       :else (let [root (long (Math/sqrt x))]
                               (not-any? #(zero? (mod x %))
                                         (range 3 (inc root) 2)))))]
        (count (filter prime? (range 2 n))))))