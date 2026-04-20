(defn is_prime
  "Return true if a given number is prime, and false otherwise."
  [n]
  (cond
    (or (not (integer? n)) (< n 2)) false
    (= n 2) true
    (even? n) false
    :else (let [limit (int (Math/sqrt n))]
            (loop [i 3]
              (cond
                (> i limit) true
                (zero? (rem n i)) false
                :else (recur (+ i 2)))))))