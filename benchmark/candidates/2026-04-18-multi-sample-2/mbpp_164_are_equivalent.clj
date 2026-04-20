(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (if (nil? n)
              nil
              (let [n (Math/abs (long n))]
                (if (zero? n)
                  0
                  (reduce +
                          (filter #(zero? (mod n %))
                                  (range 1 (inc n))))))))]
    (= (sum-of-divisors num1)
       (sum-of-divisors num2))))