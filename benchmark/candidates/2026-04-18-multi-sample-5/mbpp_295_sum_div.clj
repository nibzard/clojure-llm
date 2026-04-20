(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (if (nil? number) 0 number)]
    (cond
      (zero? n) 0
      (neg? n) (recur (- n))
      :else
      (reduce
       +
       (for [d (range 1 (inc (long (Math/sqrt n))))
             :when (zero? (mod n d))]
         (let [q (quot n d)]
           (if (= d q) d (+ d q))))))))