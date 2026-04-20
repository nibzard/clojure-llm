(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (power_base_sum 2 10)))
  (is (= 9 (power_base_sum 12 2)))
  (is (= 1 (power_base_sum 1 999))))

(run-test test-variation)
