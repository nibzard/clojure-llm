(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_pairs_count)

(deftest test-humaneval

  (is (= (candidate [1 1 1 1] 2) 6))
  (is (= (candidate [1 5 7 -1 5] 6) 3))
  (is (= (candidate [1 -2 3] 1) 1))
  (is (= (candidate [-1 -2 3] -3) 1))
)

(run-test test-humaneval)