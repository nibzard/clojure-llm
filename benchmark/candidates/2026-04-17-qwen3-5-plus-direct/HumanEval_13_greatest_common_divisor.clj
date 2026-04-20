(defn greatest_common_divisor
  " Return a greatest common divisor of two integers a and b
  >>> (greatest_common_divisor 3 5)
  1
  >>> (greatest_common_divisor 25 15)
  5"
  [a b]
  (when (and (some? a) (some? b))
    (loop [a a b b]
      (if (zero? b)
        (if (neg? a) (- a) a)
        (recur b (mod a b))))))