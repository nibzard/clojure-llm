(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_matrix)

(deftest test-humaneval

  (is (= (candidate [[1 2 3] [2 4 5] [1 1 1]]) [[1 1 1] [1 2 3] [2 4 5]]))
  (is (= (candidate [[1 2 3] [-2 4 -5] [1 -1 1]]) [[-2 4 -5] [1 -1 1] [1 2 3]]))
  (is (= (candidate [[5 8 9] [6 4 3] [2 1 4]]) [[2 1 4] [6 4 3] [5 8 9]]))
)

(run-test test-humaneval)