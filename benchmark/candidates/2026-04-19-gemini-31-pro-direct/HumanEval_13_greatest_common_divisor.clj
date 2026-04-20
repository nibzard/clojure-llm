(defn greatest_common_divisor
  " Return a greatest common divisor of two integers a and b"
  [a b]
  (let [a (Math/abs (long a))
        b (Math/abs (long b))]
    (loop [x a y b]
      (if (zero? y)
        x
        (recur y (mod x y))))))