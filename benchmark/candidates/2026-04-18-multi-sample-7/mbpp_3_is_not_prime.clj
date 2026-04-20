(defn is_not_prime
  "Write a cljthon function to identify non-prime numbers."
  [n]
  (let [n (when (number? n) n)]
    (cond
      (nil? n) true
      (not (integer? n)) true
      (< n 2) true
      (= n 2) false
      (even? n) true
      :else
      (let [limit (long (Math/sqrt n))]
        (loop [i 3]
          (cond
            (> i limit) false
            (zero? (mod n i)) true
            :else (recur (+ i 2))))))))