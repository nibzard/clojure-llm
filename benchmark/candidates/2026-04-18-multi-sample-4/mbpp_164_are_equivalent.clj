(defn are_equivalent
  "	Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (if (zero? n)
              0
              (let [n (Math/abs (long n))
                    limit (long (Math/sqrt n))]
                (loop [i 1
                       total 0]
                  (if (> i limit)
                    total
                    (if (zero? (mod n i))
                      (let [q (quot n i)]
                        (recur (inc i)
                               (+ total
                                  i
                                  (if (= i q) 0 q))))
                      (recur (inc i) total)))))))]
    (= (sum-of-divisors num1)
       (sum-of-divisors num2))))