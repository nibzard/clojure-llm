(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (neg? n) (- n) n)]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (loop [i 1
             cnt 0]
        (if (> (* i i) n)
          cnt
          (if (zero? (mod n i))
            (recur (inc i) (+ cnt (if (= (* i i) n) 1 2)))
            (recur (inc i) cnt)))))))