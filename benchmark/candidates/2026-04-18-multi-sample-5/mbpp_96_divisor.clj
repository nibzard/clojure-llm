(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (neg? n) (- n) n)]
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
                   (if (zero? (mod n i))
                     (+ cnt (if (= i (quot n i)) 1 2))
                     cnt))))))))