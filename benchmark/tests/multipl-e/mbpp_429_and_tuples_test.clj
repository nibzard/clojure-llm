(require '[clojure.test :refer [deftest is run-test]])

(def candidate and_tuples)

(deftest test-humaneval

  (is (= (candidate [10 4 6 9] [5 2 3 3]) [0 0 2 1]))
  (is (= (candidate [1 2 3 4] [5 6 7 8]) [1 2 3 0]))
  (is (= (candidate [8 9 11 12] [7 13 14 17]) [0 9 10 0]))
)

(run-test test-humaneval)