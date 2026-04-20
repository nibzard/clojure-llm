(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (bigint (or num 0))]
    (if (neg? n)
      nil
      (let [numerator (reduce *' 1N (range (+ n 2N) (*' 2N n)))
            denominator (reduce *' 1N (range 2N (inc n)))]
        (/ numerator denominator)))))