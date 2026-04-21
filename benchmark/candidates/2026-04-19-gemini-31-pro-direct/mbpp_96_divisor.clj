(defn divisor
  "Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (Math/abs (long n))]
    (if (zero? n)
      0
      (let [limit (long (Math/sqrt n))]
        (reduce (fn [cnt i]
                  (if (zero? (rem n i))
                    (+ cnt (if (= (* i i) n) 1 2))
                    cnt))
                0
                (range 1 (inc limit)))))))