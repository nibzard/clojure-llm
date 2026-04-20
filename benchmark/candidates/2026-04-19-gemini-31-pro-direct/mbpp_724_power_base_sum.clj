(defn power_base_sum
  "Write a function that takes base and power as arguments and calculate the sum of all digits of the base to the specified power."
  [base power]
  (let [num (.abs (.pow (biginteger base) (int power)))]
    (reduce + (map #(Character/digit % 10) (str num)))))