(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (cond
      (zero? n) 0
      (neg? n) 0
      :else
      (let [limit (long (Math/sqrt n))]
        (loop [i 1
               count 0]
          (if (> i limit)
            count
            (if (zero? (mod n i))
              (let [count' (inc count)]
                (if (= i (/ n i))
                  count'
                  (inc count')))
              (recur (inc i) count))))))))