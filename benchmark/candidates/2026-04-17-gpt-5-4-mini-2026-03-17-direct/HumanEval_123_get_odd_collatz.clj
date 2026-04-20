(defn get_odd_collatz
  [n]
  (letfn [(collatz-step [x]
            (if (even? x)
              (/ x 2)
              (inc (* 3 x))))]
    (loop [x n
           odds #{}]
      (if (= x 1)
        (vec (sort (conj odds 1)))
        (recur (collatz-step x)
               (if (odd? x) (conj odds x) odds))))))