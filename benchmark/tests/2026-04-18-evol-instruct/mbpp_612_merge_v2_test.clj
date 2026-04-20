(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4 6]] (merge-by-index [[1 2] [3 4] [5 6]]))))

(run-test test-variation)
