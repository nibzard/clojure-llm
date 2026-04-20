(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 1]] (common-element-indexes [1 2 3] [4 2 5]))))

(run-test test-variation)
