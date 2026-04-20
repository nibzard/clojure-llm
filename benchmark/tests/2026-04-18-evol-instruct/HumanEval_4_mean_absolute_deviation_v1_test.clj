(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1.0 (median_absolute_deviation [1.0 2.0 3.0 4.0 5.0])))
  (is (= 1.0 (median_absolute_deviation [10 10 11 12 100])))
  (is (= 0.0 (median_absolute_deviation []))))

(run-test test-variation)
