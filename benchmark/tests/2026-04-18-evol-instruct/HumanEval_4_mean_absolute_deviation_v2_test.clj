(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1.0 (median_absolute_deviation [1 2 3 4 5])))
  (is (= 1.0 (median_absolute_deviation [1 nil 2 100])))
  (is (= 0.0 (median_absolute_deviation []))))

(run-test test-variation)
