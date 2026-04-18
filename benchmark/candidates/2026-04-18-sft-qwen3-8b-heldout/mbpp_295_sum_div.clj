(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (if (number? number) number 0)]
    (if (zero? n)
      0
      (reduce
       +
       (for [i (range 1 (inc (Math/sqrt n)))
             :when (zero? (mod n i))]
         (+ i (if (= i (Math/sqrt n)) 0 (/ n i))))))))