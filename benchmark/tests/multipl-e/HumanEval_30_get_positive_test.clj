(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_positive)

(deftest test-humaneval

  (is (= (candidate [-1 -2 4 5 6]) [4 5 6]))
  (is (= (candidate [5 3 -5 2 3 3 9 0 123 1 -10]) [5 3 2 3 3 9 123 1]))
  (is (= (candidate [-1 -2]) []))
  (is (= (candidate []) []))
)

(run-test test-humaneval)