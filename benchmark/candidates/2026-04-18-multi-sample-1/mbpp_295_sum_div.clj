(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (long (Math/abs (or number 0)))]
    (cond
      (zero? n) 0
      :else
      (reduce
       +
       (for [i (range 1 (inc (long (Math/sqrt n))))
             :when (zero? (mod n i))]
         (let [q (quot n i)]
           (if (= i q) i (+ i q))))))))