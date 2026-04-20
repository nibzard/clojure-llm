(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (cond
            (nil? n) 0
            (neg? n) (- n)
            :else n)]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (loop [i 1
             cnt 0]
        (if (> (* i i) n)
          cnt
          (if (= 0 (mod n i))
            (recur (inc i) (if (= i (/ n i)) (inc cnt) (+ cnt 2)))
            (recur (inc i) cnt)))))))