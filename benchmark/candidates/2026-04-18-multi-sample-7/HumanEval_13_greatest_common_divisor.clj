(defn greatest_common_divisor
  " Return a greatest common divisor of two integers a and b
  >>> (greatest_common_divisor 3 5)
  1
  >>> (greatest_common_divisor 25 15)
  5"
  [a b]
  (loop [x (abs (long a))
         y (abs (long b))]
    (if (zero? y)
      x
      (recur y (mod x y)))))