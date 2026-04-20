(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 72 (sum_cubes [1.0 2.0 3.0]))))

(run-test test-variation)
