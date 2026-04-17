(require '[clojure.test :refer [deftest is run-test]])

(def candidate bitwise_xor)

(deftest test-humaneval

  (is (= (candidate [10 4 6 9] [5 2 3 3]) [15 6 5 10]))
  (is (= (candidate [11 5 7 10] [6 3 4 4]) [13 6 3 14]))
  (is (= (candidate [12 6 8 11] [7 4 5 6]) [11 2 13 13]))
)

(run-test test-humaneval)