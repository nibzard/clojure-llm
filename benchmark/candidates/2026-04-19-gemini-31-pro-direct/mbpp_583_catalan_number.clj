(defn catalan_number
  "Write a function which returns nth catalan number."
  [num]
  (let [fact (fn [n] (reduce *' 1 (range 1 (inc n))))]
    (/ (fact (* 2 num))
       (*' (fact (inc num)) (fact num)))))