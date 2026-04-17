(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_of_nth)

(deftest test-humaneval

  (is (= (candidate [[5 6 7] [1 3 5] [8 9 19]] 2) 19))
  (is (= (candidate [[6 7 8] [2 4 6] [9 10 20]] 1) 10))
  (is (= (candidate [[7 8 9] [3 5 7] [10 11 21]] 1) 11))
)

(run-test test-humaneval)