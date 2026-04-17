(require '[clojure.test :refer [deftest is run-test]])

(def candidate maxAverageOfPath)

(deftest test-humaneval

  (is (= (candidate [[1 2 3] [6 5 4] [7 3 9]]) 5.2))
  (is (= (candidate [[2 3 4] [7 6 5] [8 4 10]]) 6.2))
  (is (= (candidate [[3 4 5] [8 7 6] [9 5 11]]) 7.2))
  (is (= (candidate [[1 2 3] [4 5 6] [7 8 9]]) 5.8))
)

(run-test test-humaneval)