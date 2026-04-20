(defn divisor
  "Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (Math/abs (long n))]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else (loop [i 1
                   count 0]
              (if (> (* i i) n)
                count
                (recur (inc i)
                       (+ count
                          (if (zero? (mod n i))
                            (if (= i (/ n i)) 1 2)
                            0))))))))