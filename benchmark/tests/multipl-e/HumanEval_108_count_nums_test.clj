(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_nums)

(deftest test-humaneval

  (is (= (candidate []) 0))
  (is (= (candidate [-1 -2 0]) 0))
  (is (= (candidate [1 1 2 -2 3 4 5]) 6))
  (is (= (candidate [1 6 9 -6 0 1 5]) 5))
  (is (= (candidate [1 100 98 -7 1 -1]) 4))
  (is (= (candidate [12 23 34 -45 -56 0]) 5))
  (is (= (candidate [0 1]) 1))
  (is (= (candidate [1]) 1))
)

(run-test test-humaneval)