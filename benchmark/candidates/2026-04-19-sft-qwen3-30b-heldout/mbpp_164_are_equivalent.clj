(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (if (nil? n)
              0
              (let [n (Math/abs (long n))]
                (reduce
                 +
                 (for [i (range 1 (inc n))
                       :when (zero? (mod n i))]
                   i)))))]
    (= (sum-of-divisors num1) (sum-of-divisors num2))))