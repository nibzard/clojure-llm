(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_length)

(deftest test-humaneval

  (is (= (candidate [[0] [1 3] [5 7] [9 11] [13 15 17]]) [3 [13 15 17]]))
  (is (= (candidate [[1] [5 7] [10 12 14 15]]) [4 [10 12 14 15]]))
  (is (= (candidate [[5] [15 20 25]]) [3 [15 20 25]]))
)

(run-test test-humaneval)