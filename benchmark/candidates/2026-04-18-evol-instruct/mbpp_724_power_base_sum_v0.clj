(defn power_base_sum
  "Return the sum of the digits of (base^power).

  Examples:
  (power_base_sum 2 10) => 7
  (power_base_sum 12 2) => 9"
  [base power]
  (->> (str (bigint (Math/pow base power)))
       (map #(Character/digit ^char % 10))
       (reduce +)))