(require '[clojure.test :refer [deftest is run-test]])

(def candidate compare)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 1] [1 2 3 4 2 -2]) [0 0 0 0 3 3]))
  (is (= (candidate [0 0 0 0 0 0] [0 0 0 0 0 0]) [0 0 0 0 0 0]))
  (is (= (candidate [1 2 3] [-1 -2 -3]) [2 4 6]))
  (is (= (candidate [1 2 3 5] [-1 2 3 4]) [2 0 0 1]))
)

(run-test test-humaneval)