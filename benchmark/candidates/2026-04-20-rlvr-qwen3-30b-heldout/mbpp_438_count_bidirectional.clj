(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [v (vec (or test_list []))
        n (count v)]
    (reduce
      (fn [acc i]
        (let [j (- n 1 i)]
          (if (< i j)
            (+ acc 2)
            acc)))
      0
      (range (quot n 2)))))