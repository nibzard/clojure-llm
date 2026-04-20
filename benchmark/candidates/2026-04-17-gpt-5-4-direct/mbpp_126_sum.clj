(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (long (Math/abs (or a 0)))
        b (long (Math/abs (or b 0)))
        g (cond
            (and (zero? a) (zero? b)) 0
            (zero? a) b
            (zero? b) a
            :else (loop [x a y b]
                    (if (zero? y)
                      x
                      (recur y (mod x y)))))]
    (if (zero? g)
      0
      (reduce
       +
       (for [i (range 1 (inc (long (Math/sqrt g))))
             :when (zero? (mod g i))]
         (let [j (quot g i)]
           (if (= i j) i (+ i j))))))))