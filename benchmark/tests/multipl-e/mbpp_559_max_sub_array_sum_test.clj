(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_sub_array_sum)

(deftest test-humaneval

  (is (= (candidate [-2 -3 4 -1 -2 1 5 -3] 8) 7))
  (is (= (candidate [-3 -4 5 -2 -3 2 6 -4] 8) 8))
  (is (= (candidate [-4 -5 6 -3 -4 3 7 -5] 8) 10))
)

(run-test test-humaneval)