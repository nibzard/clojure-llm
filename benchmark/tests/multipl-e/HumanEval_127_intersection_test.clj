(require '[clojure.test :refer [deftest is run-test]])

(def candidate intersection)

(deftest test-humaneval

  (is (= (candidate [1 2] [2 3]) "NO"))
  (is (= (candidate [-1 1] [0 4]) "NO"))
  (is (= (candidate [-3 -1] [-5 5]) "YES"))
  (is (= (candidate [-2 2] [-4 0]) "YES"))
  (is (= (candidate [-11 2] [-1 -1]) "NO"))
  (is (= (candidate [1 2] [3 5]) "NO"))
  (is (= (candidate [1 2] [1 2]) "NO"))
  (is (= (candidate [-2 -2] [-3 -2]) "NO"))
)

(run-test test-humaneval)