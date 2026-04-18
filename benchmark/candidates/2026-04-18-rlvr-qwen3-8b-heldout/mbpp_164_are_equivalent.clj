(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(divisor-sum [n]
            (if (or (nil? n) (zero? n))
              0
              (reduce
               +
               1
               (filter #(zero? (mod n %))
                       (range 2 (inc (long (Math/sqrt n))))))))]
    (= (divisor-sum num1) (divisor-sum num2))))