(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (if (number? number) (long number) 0)]
    (if (pos? n)
      (reduce
       +
       (for [i (range 1 (inc n))
             :when (zero? (mod n i))]
         i))
      0)))