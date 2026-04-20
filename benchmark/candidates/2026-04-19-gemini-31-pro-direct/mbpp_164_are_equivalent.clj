(defn are_equivalent
  "Write a function to determine if the sum of the divisors of two integers are the same."
  [num1 num2]
  (let [sum-divs (fn [n]
                   (let [abs-n (Math/abs n)]
                     (if (zero? abs-n)
                       0
                       (reduce + (filter #(zero? (rem abs-n %)) (range 1 (inc abs-n)))))))]
    (= (sum-divs num1) (sum-divs num2))))