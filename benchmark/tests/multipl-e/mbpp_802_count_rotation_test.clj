(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_rotation)

(deftest test-humaneval

  (is (= (candidate [3 2 1]) 1))
  (is (= (candidate [4 5 1 2 3]) 2))
  (is (= (candidate [7 8 9 1 2 3]) 3))
  (is (= (candidate [1 2 3]) 0))
  (is (= (candidate [1 3 2]) 2))
)

(run-test test-humaneval)