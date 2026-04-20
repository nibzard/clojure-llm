(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (let [n (Math/abs (long n))]
              (if (zero? n)
                0
                (reduce
                  (fn [sum d]
                    (if (zero? (mod n d))
                      (let [q (/ n d)]
                        (if (= d q)
                          (+ sum d)
                          (+ sum d q)))
                      sum))
                  0
                  (range 1 (inc (long (Math/sqrt n))))))))]
    (= (sum-of-divisors num1) (sum-of-divisors num2))))