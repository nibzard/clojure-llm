(require '[clojure.test :refer [deftest is run-test]])

(def candidate Find_Max_Length)

(deftest test-humaneval

  (is (= (candidate [[1] [1 4] [5 6 7 8]]) 4))
  (is (= (candidate [[0 1] [2 2] [3 2 1]]) 3))
  (is (= (candidate [[7] [22 23] [13 14 15] [10 20 30 40 50]]) 5))
)

(run-test test-humaneval)