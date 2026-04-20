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
      (reduce
       +
       (mapcat
        (fn [d]
          (let [q (quot g d)]
            (if (= d q) [d] [d q])))
        (filter #(zero? (mod g %))
                (range 1 (inc (long (Math/sqrt g))))))))))