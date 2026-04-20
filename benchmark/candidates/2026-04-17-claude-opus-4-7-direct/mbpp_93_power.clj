(defn power
  "Write a function to calculate the value of 'a' to the power 'b'."
  [a b]
  (if (and (number? a) (number? b))
    (cond
      (zero? b) 1
      (pos? b) (reduce * 1 (repeat b a))
      :else (/ 1 (reduce * 1 (repeat (- b) a))))
    (throw (IllegalArgumentException. "Both arguments must be numbers"))))