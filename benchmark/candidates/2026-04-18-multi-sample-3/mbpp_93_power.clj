(defn power
  "	Write a function to calculate the value of 'a' to the power 'b'."
  [a b]
  (cond
    (nil? a) nil
    (nil? b) nil
    (neg? b) (/ 1 (power a (- b)))
    :else (loop [acc 1
                 base a
                 exp b]
            (if (zero? exp)
              acc
              (recur (if (odd? exp) (* acc base) acc)
                     (* base base)
                     (quot exp 2))))))