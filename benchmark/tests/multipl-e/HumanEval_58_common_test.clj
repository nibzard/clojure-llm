(require '[clojure.test :refer [deftest is run-test]])

(def candidate common)

(deftest test-humaneval

  (is (= (candidate [1 4 3 34 653 2 5] [5 7 1 5 9 653 121]) [1 5 653]))
  (is (= (candidate [5 3 2 8] [3 2]) [2 3]))
  (is (= (candidate [4 3 2 8] [3 2 4]) [2 3 4]))
  (is (= (candidate [4 3 2 8] []) []))
)

(run-test test-humaneval)