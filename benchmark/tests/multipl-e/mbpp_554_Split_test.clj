(require '[clojure.test :refer [deftest is run-test]])

(def candidate Split)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6]) [1 3 5]))
  (is (= (candidate [10 11 12 13]) [11 13]))
  (is (= (candidate [7 8 9 1]) [7 9 1]))
)

(run-test test-humaneval)