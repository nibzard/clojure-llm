(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_X)

(deftest test-humaneval

  (is (= (candidate [10 8 5 2 10 15 10 8 5 8 8 2] 4) 0))
  (is (= (candidate [10 8 5 2 10 15 10 8 5 8 8 2] 10) 3))
  (is (= (candidate [10 8 5 2 10 15 10 8 5 8 8 2] 8) 4))
)

(run-test test-humaneval)