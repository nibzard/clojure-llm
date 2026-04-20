(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (cond
            (nil? num) 0
            (number? num) (long num)
            :else 0)]
    (if (neg? n)
      0
      (let [numerator (reduce *' 1N (range (inc (+ n 2)) (+ (* 2 n) 1)))
            denominator (reduce *' 1N (range 1 (inc n)))]
        (/ numerator denominator (inc n))))))