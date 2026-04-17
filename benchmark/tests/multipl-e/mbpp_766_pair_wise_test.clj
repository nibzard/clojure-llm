(require '[clojure.test :refer [deftest is run-test]])

(def candidate pair_wise)

(deftest test-humaneval

  (is (= (candidate [1 1 2 3 3 4 4 5]) [[1 1] [1 2] [2 3] [3 3] [3 4] [4 4] [4 5]]))
  (is (= (candidate [1 5 7 9 10]) [[1 5] [5 7] [7 9] [9 10]]))
  (is (= (candidate [5 1 9 7 10]) [[5 1] [1 9] [9 7] [7 10]]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10]) [[1 2] [2 3] [3 4] [4 5] [5 6] [6 7] [7 8] [8 9] [9 10]]))
)

(run-test test-humaneval)