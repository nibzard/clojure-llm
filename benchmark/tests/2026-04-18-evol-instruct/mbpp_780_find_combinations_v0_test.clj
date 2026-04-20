(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 4] [2 3]] (vector-sum-subsets [1 2 3 4] 5))))

(run-test test-variation)
