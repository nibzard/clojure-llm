(defn power_base_sum
  "Return the sum of the digits of base raised to power.

  Handles arbitrarily large integers and returns 0 for 0^0 by convention.

  Examples:
  (power_base_sum 2 10) => 7
  (power_base_sum 12 3) => 9"
  [base power]
  (if (and (zero? base) (zero? power))
    0
    (->> (bigint base)
         (bigint)
         (#(clojure.math.numeric-tower/expt % power))
         str
         (re-seq #"\d")
         (map #(Character/digit ^char (first %) 10))
         (reduce + 0))))