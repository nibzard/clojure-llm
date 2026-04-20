(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (or num 0)]
    (if (neg? n)
      nil
      (let [factorial (fn [x]
                        (reduce *' 1N (range 1 (inc x))))]
        (/ (factorial (* 2 n))
           (*' (factorial (inc n))
               (factorial n)))))))