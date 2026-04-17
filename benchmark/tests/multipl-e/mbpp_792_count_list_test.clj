(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_list)

(deftest test-humaneval

  (is (= (candidate [[1 3] [5 7] [9 11] [13 15 17]]) 4))
  (is (= (candidate [[1 2] [2 3] [4 5]]) 3))
  (is (= (candidate [[1 0] [2 0]]) 2))
)

(run-test test-humaneval)