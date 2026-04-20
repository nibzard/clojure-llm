(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (if (nil? a) 0 a)
        b (if (nil? b) 0 b)
        g (loop [x (Math/abs (long a))
                 y (Math/abs (long b))]
            (if (zero? y)
              x
              (recur y (mod x y))))]
    (if (zero? g)
      0
      (reduce +
              (for [d (range 1 (inc (long (Math/sqrt g))))
                    :when (zero? (mod g d))]
                (let [q (quot g d)]
                  (if (= d q)
                    d
                    (+ d q))))))))