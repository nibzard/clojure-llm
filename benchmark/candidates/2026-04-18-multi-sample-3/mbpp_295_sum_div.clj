(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (cond
            (nil? number) 0
            (integer? number) (Math/abs (long number))
            :else (long (Math/abs number)))]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (let [limit (long (Math/sqrt n))]
        (reduce
         (fn [sum d]
           (if (zero? (mod n d))
             (let [q (quot n d)]
               (+ sum d (if (= d q) 0 q)))
             sum))
         0
         (range 1 (inc limit)))))))