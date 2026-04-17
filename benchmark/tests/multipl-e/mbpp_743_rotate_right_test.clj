(require '[clojure.test :refer [deftest is run-test]])

(def candidate rotate_right)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] 3) [8 9 10 1 2 3 4 5 6 7]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] 2) [9 10 1 2 3 4 5 6 7 8]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] 5) [6 7 8 9 10 1 2 3 4 5]))
)

(run-test test-humaneval)