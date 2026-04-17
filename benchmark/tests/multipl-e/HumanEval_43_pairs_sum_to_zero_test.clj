(require '[clojure.test :refer [deftest is run-test]])

(def candidate pairs_sum_to_zero)

(deftest test-humaneval

  (is (= (candidate [1 3 5 0]) false))
  (is (= (candidate [1 3 -2 1]) false))
  (is (= (candidate [1 2 3 7]) false))
  (is (= (candidate [2 4 -5 3 5 7]) true))
  (is (= (candidate [1]) false))
  (is (= (candidate [-3 9 -1 3 2 30]) true))
  (is (= (candidate [-3 9 -1 3 2 31]) true))
  (is (= (candidate [-3 9 -1 4 2 30]) false))
  (is (= (candidate [-3 9 -1 4 2 31]) false))
)

(run-test test-humaneval)