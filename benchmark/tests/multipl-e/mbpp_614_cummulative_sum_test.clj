(require '[clojure.test :refer [deftest is run-test]])

(def candidate cummulative_sum)

(deftest test-humaneval

  (is (= (candidate [[1 3] [5 6 7] [2 6]]) 30))
  (is (= (candidate [[2 4] [6 7 8] [3 7]]) 37))
  (is (= (candidate [[3 5] [7 8 9] [4 8]]) 44))
)

(run-test test-humaneval)