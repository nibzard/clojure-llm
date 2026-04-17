(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_length_list)

(deftest test-humaneval

  (is (= (candidate [[0] [1 3] [5 7] [9 11] [13 15 17]]) [3 [13 15 17]]))
  (is (= (candidate [[1 2 3 4 5] [1 2 3 4] [1 2 3] [1 2] [1]]) [5 [1 2 3 4 5]]))
  (is (= (candidate [[3 4 5] [6 7 8 9] [10 11 12]]) [4 [6 7 8 9]]))
)

(run-test test-humaneval)