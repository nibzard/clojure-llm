(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (cond
            (nil? n) 0
            (zero? n) 0
            :else (Math/abs (long n)))]
    (if (zero? n)
      0
      (let [limit (long (Math/sqrt n))]
        (loop [i 1
               cnt 0]
          (if (> i limit)
            cnt
            (if (zero? (mod n i))
              (recur (inc i) (+ cnt (if (= i (quot n i)) 1 2)))
              (recur (inc i) cnt)))))))