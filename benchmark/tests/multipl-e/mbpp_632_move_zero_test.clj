(require '[clojure.test :refer [deftest is run-test]])

(def candidate move_zero)

(deftest test-humaneval

  (is (= (candidate [1 0 2 0 3 4]) [1 2 3 4 0 0]))
  (is (= (candidate [2 3 2 0 0 4 0 5 0]) [2 3 2 4 5 0 0 0 0]))
  (is (= (candidate [0 1 0 1 1]) [1 1 1 0 0]))
)

(run-test test-humaneval)