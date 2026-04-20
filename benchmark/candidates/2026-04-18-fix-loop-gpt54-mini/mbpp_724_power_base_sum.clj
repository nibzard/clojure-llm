(defn power_base_sum
  "	Write a function that takes base and power as arguments and calculate the sum of all digits of the base to the specified power."
  [base power]
  (let [n (reduce *' 1N (repeat power base))]
    (reduce + (map #(- (int %) 48) (str n)))))