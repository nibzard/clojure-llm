(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (Math/abs (long (or a 0)))
        b (Math/abs (long (or b 0)))
        gcd (loop [x a y b]
              (if (zero? y)
                x
                (recur y (mod x y))))]
    (cond
      (and (zero? a) (zero? b)) 0
      (zero? gcd) 0
      :else
      (let [limit (long (Math/sqrt gcd))]
        (loop [i 1
               acc 0]
          (if (> i limit)
            acc
            (if (zero? (mod gcd i))
              (let [j (quot gcd i)]
                (recur (inc i)
                       (+ acc i (if (= i j) 0 j))))
              (recur (inc i) acc)))))))