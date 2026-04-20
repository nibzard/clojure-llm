(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4]] (split-even-odd [1 2 3 4 5]))))

(run-test test-variation)
