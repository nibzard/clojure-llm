(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 70 (sum-negative-indexed [10 20 30 40] [-1 -3]))))

(run-test test-variation)
