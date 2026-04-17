(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_fill)

(deftest test-humaneval

  (is (= (candidate [[0 0 1 0] [0 1 0 0] [1 1 1 1]] 1) 6))
  (is (= (candidate [[0 0 1 1] [0 0 0 0] [1 1 1 1] [0 1 1 1]] 2) 5))
  (is (= (candidate [[0 0 0] [0 0 0]] 5) 0))
  (is (= (candidate [[1 1 1 1] [1 1 1 1]] 2) 4))
  (is (= (candidate [[1 1 1 1] [1 1 1 1]] 9) 2))
)

(run-test test-humaneval)