(defn power
  "	Write a function to calculate the value of 'a' to the power 'b'."
  [a b]
  (cond
    (nil? a) nil
    (nil? b) nil
    (neg? b) (/ 1 (power a (- b)))
    (zero? b) 1
    :else
    (loop [base a
           exp b
           acc 1]
      (if (zero? exp)
        acc
        (recur (* base base)
               (quot exp 2)
               (if (odd? exp) (* acc base) acc))))))