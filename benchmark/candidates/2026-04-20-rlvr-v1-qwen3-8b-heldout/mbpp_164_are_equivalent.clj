(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (let [divisor-sum (fn [n]
                      (if (or (nil? n) (zero? n))
                        0
                        (reduce + (filter #(zero? (mod n %))
                                          (range 1 (inc (long (Math/sqrt n))))))))
        sum1 (divisor-sum num1)
        sum2 (divisor-sum num2)]
    (= sum1 sum2)))