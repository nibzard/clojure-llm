(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_sub_array_sum_repeated)

(deftest test-humaneval

  (is (= (candidate [10 20 -30 -1] 4 3) 30))
  (is (= (candidate [-1 10 20] 3 2) 59))
  (is (= (candidate [-1 -2 -3] 3 3) -1))
)

(run-test test-humaneval)