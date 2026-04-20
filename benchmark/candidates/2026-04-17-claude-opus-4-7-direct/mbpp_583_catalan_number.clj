(defn catalan_number
  "Write a function which returns nth catalan number."
  [num]
  (if (zero? num)
    1
    (loop [n num
           result 1N]
      (if (<= n 1)
        result
        (recur (dec n) (*' result (/ (+ (* 2 (dec n)) 2) (dec n))))))))