(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [a (Math/abs (long (or a 0)))
        b (Math/abs (long (or b 0)))
        g (loop [x a y b]
            (if (zero? y)
              x
              (recur y (mod x y))))]
    (cond
      (and (zero? a) (zero? b)) 0
      (zero? g) 0
      :else
      (->> (range 1 (inc (long (Math/sqrt g))))
           (reduce (fn [acc d]
                     (if (zero? (mod g d))
                       (let [q (quot g d)]
                         (+ acc d (if (= d q) 0 q)))
                       acc))
                   0)))))