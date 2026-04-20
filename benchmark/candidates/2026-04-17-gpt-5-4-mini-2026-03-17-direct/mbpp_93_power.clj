(defn power
  "Write a function to calculate the value of 'a' to the power 'b'."
  [a b]
  (cond
    (zero? b) 1
    (pos? b) (reduce *' (repeat b a))
    :else (/ 1 (power a (- b)))))