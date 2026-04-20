(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_decimal_in_range "12.34")))
  (is (= true (is_decimal_in_range "0.00")))
  (is (= false (is_decimal_in_range "100.01")))
  (is (= false (is_decimal_in_range nil))))

(run-test test-variation)
