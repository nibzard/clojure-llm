(require '[clojure.test :refer [deftest is run-test]])

(def candidate flatten_list)

(deftest test-humaneval

  (is (= (candidate [0 10 [20 30] 40 50 [60 70 80] [90 100 110 120]]) [0 10 20 30 40 50 60 70 80 90 100 110 120]))
  (is (= (candidate [[10 20] [40] [30 56 25] [10 20] [33] [40]]) [10 20 40 30 56 25 10 20 33 40]))
  (is (= (candidate [[1 2 3] [4 5 6] [10 11 12] [7 8 9]]) [1 2 3 4 5 6 10 11 12 7 8 9]))
)

(run-test test-humaneval)