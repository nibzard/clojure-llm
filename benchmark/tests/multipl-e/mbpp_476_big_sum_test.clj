(require '[clojure.test :refer [deftest is run-test]])

(def candidate big_sum)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 4))
  (is (= (candidate [-1 2 3 4]) 3))
  (is (= (candidate [2 3 6]) 8))
)

(run-test test-humaneval)