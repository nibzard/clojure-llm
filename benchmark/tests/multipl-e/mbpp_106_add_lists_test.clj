(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_lists)

(deftest test-humaneval

  (is (= (candidate [5 6 7] [9 10]) [9 10 5 6 7]))
  (is (= (candidate [6 7 8] [10 11]) [10 11 6 7 8]))
  (is (= (candidate [7 8 9] [11 12]) [11 12 7 8 9]))
)

(run-test test-humaneval)