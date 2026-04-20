(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (bigint (or a 0))
        b (bigint (or b 0))
        gcd (loop [x (abs a), y (abs b)]
              (if (zero? y)
                x
                (recur y (mod x y))))]
    (cond
      (zero? gcd) 0
      (= gcd 1) 1
      :else
      (let [root (long (Math/sqrt (double gcd)))]
        (loop [i 1
               acc 0N]
          (if (> i root)
            acc
            (if (zero? (mod gcd i))
              (let [j (quot gcd i)]
                (recur (inc i)
                       (+ acc i (if (= i j) 0 j))))
              (recur (inc i) acc)))))))