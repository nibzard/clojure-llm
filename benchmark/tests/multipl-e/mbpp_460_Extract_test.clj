(require '[clojure.test :refer [deftest is run-test]])

(def candidate Extract)

(deftest test-humaneval

  (is (= (candidate [[1 2] [3 4 5] [6 7 8 9]]) [1 3 6]))
  (is (= (candidate [[1 2 3] [4 5]]) [1 4]))
  (is (= (candidate [[9 8 1] [1 2]]) [9 1]))
)

(run-test test-humaneval)