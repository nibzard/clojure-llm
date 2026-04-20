(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (or num 0)]
    (if (neg? n)
      nil
      (letfn [(fact [x] (reduce *' 1N (range 1 (inc x))))]
        (/ (fact (* 2 n))
           (*' (fact (inc n)) (fact n)))))))