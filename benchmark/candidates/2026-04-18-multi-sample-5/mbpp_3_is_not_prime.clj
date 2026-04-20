(defn is_not_prime
  "	Write a cljthon function to identify non-prime numbers."
  [n]
  (let [n (when (number? n) (long n))]
    (cond
      (nil? n) true
      (< n 2) true
      (= n 2) false
      (even? n) true
      :else
      (let [limit (long (Math/sqrt n))]
        (loop [d 3]
          (cond
            (> d limit) false
            (zero? (mod n d)) true
            :else (recur (+ d 2))))))))