(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8.0 (median-trapezium 10 6 4))))

(run-test test-variation)
