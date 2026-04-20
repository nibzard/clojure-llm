(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-divisors [n]
            (reduce + (filter #(zero? (mod n %)) (range 1 (inc n)))))]
    (= (sum-divisors num1) (sum-divisors num2))))