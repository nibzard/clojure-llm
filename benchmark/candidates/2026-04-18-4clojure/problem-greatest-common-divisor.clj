(defn problem-greatest-common-divisor [a b]
  (loop [x (Math/abs (long a))
         y (Math/abs (long b))]
    (if (zero? y)
      x
      (recur y (mod x y)))))