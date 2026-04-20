(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (power_base_sum 2 10))))

(run-test test-variation)
