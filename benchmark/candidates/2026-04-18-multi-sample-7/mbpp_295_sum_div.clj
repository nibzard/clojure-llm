(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (if (and (integer? number) (pos? number))
    (let [limit (long (Math/sqrt number))]
      (reduce
       (fn [sum d]
         (if (zero? (mod number d))
           (let [q (quot number d)]
             (+ sum d (if (= d q) 0 q)))
           sum))
       0
       (range 1 (inc limit))))
    0))