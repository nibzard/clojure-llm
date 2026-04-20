(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (if (and (integer? n) (pos? n))
              (reduce + (filter #(zero? (mod n %)) (range 1 (inc n))))
              0))]
    (= (sum-of-divisors num1)
       (sum-of-divisors num2))))