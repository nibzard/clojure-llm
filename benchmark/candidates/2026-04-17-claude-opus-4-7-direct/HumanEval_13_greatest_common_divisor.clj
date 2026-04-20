(defn greatest_common_divisor
  "Return a greatest common divisor of two integers a and b"
  [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))