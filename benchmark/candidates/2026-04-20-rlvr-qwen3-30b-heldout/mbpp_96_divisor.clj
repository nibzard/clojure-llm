(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (cond
      (zero? n) 0
      (neg? n) 0
      :else
      (loop [i 1
             count 0]
        (if (> (* i i) n)
          count
          (recur (inc i)
                 (if (zero? (mod n i))
                   (if (= i (/ n i))
                     (inc count)
                     (+ count 2))
                   count)))))))