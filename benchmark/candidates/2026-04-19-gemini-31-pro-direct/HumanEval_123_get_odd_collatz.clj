(defn get_odd_collatz
  "Given a positive integer n, return a sorted list that has the odd numbers in collatz sequence."
  [n]
  (loop [curr n
         odds []]
    (let [new-odds (if (odd? curr) (conj odds curr) odds)]
      (if (= curr 1)
        (vec (sort new-odds))
        (recur (if (even? curr)
                 (quot curr 2)
                 (inc (* 3 curr)))
               new-odds)))))