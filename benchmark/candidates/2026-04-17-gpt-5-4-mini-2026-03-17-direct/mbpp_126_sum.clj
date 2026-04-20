(defn sum
  "	Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [n (long (Math/abs (long a)))
        m (long (Math/abs (long b)))
        limit (long (Math/min n m))]
    (reduce
      (fn [acc d]
        (if (and (zero? (mod n d))
                 (zero? (mod m d)))
          (+ acc d)
          acc))
      0
      (range 1 (inc limit)))))