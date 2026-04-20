(defn sum-numeric-values
  "Return the sum of all numeric values in the given map.
  Ignore any non-numeric values and treat nil as 0.

  Examples:
  (sum-numeric-values {:a 1 :b 2 :c 3}) => 6
  (sum-numeric-values {:a 1 :b \"x\" :c nil}) => 1"
  [m]
  (reduce + 0 (keep #(when (number? %) %) (vals m))))