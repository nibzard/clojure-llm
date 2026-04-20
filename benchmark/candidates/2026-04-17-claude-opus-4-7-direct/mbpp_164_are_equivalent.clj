(defn are_equivalent
  "Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (letfn [(sum-of-divisors [n]
            (let [limit (int (Math/sqrt n))]
              (loop [i 1
                     sum 0]
                (cond
                  (> i limit) sum
                  (zero? (mod n i))
                  (let [quotient (/ n i)]
                    (if (= i quotient)
                      (recur (inc i) (+ sum i))
                      (recur (inc i) (+ sum i quotient))))
                  :else
                  (recur (inc i) sum)))))]
    (= (sum-of-divisors num1) (sum-of-divisors num2))))