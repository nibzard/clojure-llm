(defn divisor
  "Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (if (integer? n)
    (let [abs-n (Math/abs n)
          limit (int (Math/sqrt abs-n))]
      (loop [i 1
             count 0]
        (cond
          (> i limit) count
          (zero? (mod abs-n i))
          (if (= i (/ abs-n i))
            (recur (inc i) (inc count))
            (recur (inc i) (+ 2 count)))
          :else (recur (inc i) count))))
    0))