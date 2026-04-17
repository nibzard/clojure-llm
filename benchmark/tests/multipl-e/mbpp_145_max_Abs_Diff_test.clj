(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_Abs_Diff)

(deftest test-humaneval

  (is (= (candidate [2 1 5 3]) 4))
  (is (= (candidate [9 3 2 5 1]) 8))
  (is (= (candidate [3 2 1]) 2))
)

(run-test test-humaneval)