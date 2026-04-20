(defn power_base_sum
  "Write a function that takes base and power as arguments and calculate the sum of all digits of the base to the specified power."
  [base power]
  (-> (bigint (Math/pow base power))
      str
      (->> (map #(Character/digit ^char % 10)))
      (reduce + 0)))