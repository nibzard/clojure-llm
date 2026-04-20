(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (vec (take (or n 0) (or arr [])))
        len (count xs)]
    (loop [i 0
           total 0]
      (if (>= i len)
        total
        (recur (inc i)
               (+ total
                  (loop [j (inc i)
                         acc 0]
                    (if (>= j len)
                      acc
                      (recur (inc j)
                             (+ acc (bit-xor (xs i) (xs j)))))))))))