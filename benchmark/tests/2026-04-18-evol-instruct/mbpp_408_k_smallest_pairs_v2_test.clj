(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 2 3] [1 4 5] [1 6 7]]
         (k-smallest-sums [1 7 11] [2 4 6] 3)))
  (is (= [[1 1 2] [1 2 3] [2 1 3] [2 2 4]]
         (k-smallest-sums [1 2] [1 2] nil)))
  (is (= []
         (k-smallest-sums [] [1 2 3] 2))))

(run-test test-variation)
