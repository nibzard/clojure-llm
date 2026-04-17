(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_sum_list)

(deftest test-humaneval

  (is (= (candidate [[1 2 3] [4 5 6] [10 11 12] [7 8 9]]) [10 11 12]))
  (is (= (candidate [[3 2 1] [6 5 4] [12 11 10]]) [12 11 10]))
  (is (= (candidate [[2 3 1]]) [2 3 1]))
)

(run-test test-humaneval)