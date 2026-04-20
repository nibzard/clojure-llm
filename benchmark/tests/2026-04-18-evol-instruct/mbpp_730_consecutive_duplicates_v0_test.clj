(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 2] [3 1]] (compress-runs [1 1 1 2 2 3]))))

(run-test test-variation)
