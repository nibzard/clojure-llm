(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_bidirectional)

(deftest test-humaneval

  (is (= (candidate [[5 6] [1 2] [6 5] [9 1] [6 5] [2 1]]) 3))
  (is (= (candidate [[5 6] [1 3] [6 5] [9 1] [6 5] [2 1]]) 2))
  (is (= (candidate [[5 6] [1 2] [6 5] [9 2] [6 5] [2 1]]) 4))
)

(run-test test-humaneval)