(defn return-sum-values
  "Return the sum of all numeric values in the given map.

  Ignores nil values. Works with empty maps.

  Examples:
  (return-sum-values {:a 1 :b 2 :c 3}) ;=> 6
  (return-sum-values {:a 1 :b nil :c 4}) ;=> 5
  (return-sum-values {}) ;=> 0"
  [m])