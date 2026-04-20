(defn power
  "	Write a function to calculate the value of 'a' to the power 'b'."
  [a b]
  (cond
    (nil? a) nil
    (nil? b) nil
    (not (integer? b)) (throw (ex-info "Exponent must be an integer" {:a a :b b}))
    (zero? b) 1
    (neg? b) (/ 1 (power a (- b)))
    :else (loop [result 1
                 base a
                 exp b]
            (if (zero? exp)
              result
              (recur (if (odd? exp) (* result base) result)
                     (* base base)
                     (quot exp 2))))))