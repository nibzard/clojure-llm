(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (long (Math/abs (or a 0)))
        b (long (Math/abs (or b 0)))
        g (loop [x a y b]
            (if (zero? y)
              x
              (recur y (mod x y))))]
    (cond
      (and (zero? a) (zero? b)) 0
      (zero? g) 0
      :else
      (let [root (long (Math/sqrt g))]
        (loop [i 1
               acc 0]
          (if (> i root)
            acc
            (if (zero? (mod g i))
              (let [j (quot g i)]
                (recur (inc i) (+ acc i (if (= i j) 0 j))))
              (recur (inc i) acc))))))))