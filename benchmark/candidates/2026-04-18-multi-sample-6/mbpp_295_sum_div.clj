(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (cond
    (nil? number) 0
    (not (integer? number)) 0
    (zero? number) 0
    :else
    (let [n (Math/abs (long number))
          limit (long (Math/sqrt n))]
      (reduce
       (fn [acc d]
         (if (zero? (mod n d))
           (let [q (quot n d)]
             (+ acc d (if (= d q) 0 q)))
           acc))
       0
       (range 1 (inc limit))))))