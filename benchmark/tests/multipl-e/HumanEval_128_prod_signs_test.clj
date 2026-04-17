(require '[clojure.test :refer [deftest is run-test]])

(def candidate prod_signs)

(deftest test-humaneval

  (is (= (candidate [1 2 2 -4]) -9))
  (is (= (candidate [0 1]) 0))
  (is (= (candidate [1 1 1 2 3 -1 1]) -10))
  (is (= (candidate []) nil))
  (is (= (candidate [2 4 1 2 -1 -1 9]) 20))
  (is (= (candidate [-1 1 -1 1]) 4))
  (is (= (candidate [-1 1 1 1]) -4))
  (is (= (candidate [-1 1 1 0]) 0))
)

(run-test test-humaneval)