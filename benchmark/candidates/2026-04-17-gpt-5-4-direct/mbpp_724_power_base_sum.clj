(defn power_base_sum
  "	Write a function that takes base and power as arguments and calculate the sum of all digits of the base to the specified power."
  [base power]
  (let [n (.pow (bigint base) (int power))]
    (reduce + (map #(Character/digit ^char % 10) (str n)))))