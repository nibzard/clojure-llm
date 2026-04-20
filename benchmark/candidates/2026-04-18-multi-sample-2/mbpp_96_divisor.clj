(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (cond
            (nil? n) 0
            (integer? n) (Math/abs (long n))
            :else (Math/abs (long n)))]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (let [limit (long (Math/sqrt n))]
        (loop [i 1
               cnt 0]
          (if (> i limit)
            cnt
            (recur (inc i)
                   (if (zero? (rem n i))
                     (+ cnt (if (= i (quot n i)) 1 2))
                     cnt))))))))