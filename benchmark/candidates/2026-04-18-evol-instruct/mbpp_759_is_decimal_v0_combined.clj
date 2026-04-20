(defn is_decimal_in_range
  "Return true if x is a decimal number with exactly 2 digits after the decimal point
   and its numeric value is within the inclusive range [0.00, 100.00].

   Examples:
   (is_decimal_in_range \"12.34\") => true
   (is_decimal_in_range \"0.00\")  => true
   (is_decimal_in_range \"100.01\") => false
   (is_decimal_in_range nil)       => false"
  [x]
  (when (string? x)
    (let [m (re-matches #"^\d+(\.\d{2})$" x)]
      (when m
        (let [n (Double/parseDouble x)]
          (<= 0.0 n 100.0))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_decimal_in_range "12.34")))
  (is (= true (is_decimal_in_range "0.00")))
  (is (= false (is_decimal_in_range "100.01")))
  (is (= false (is_decimal_in_range nil))))

(run-test test-variation)
