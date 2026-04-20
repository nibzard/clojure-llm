(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (or number 0)]
    (cond
      (neg? n) 0
      (zero? n) 0
      :else
      (reduce
       +
       (for [i (range 1 (inc n))
             :when (zero? (mod n i))]
         i)))))