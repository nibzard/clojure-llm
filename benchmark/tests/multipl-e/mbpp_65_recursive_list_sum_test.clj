(require '[clojure.test :refer [deftest is run-test]])

(def candidate recursive_list_sum)

(deftest test-humaneval

  (is (= (candidate [1 2 [3 4] [5 6]]) 21))
  (is (= (candidate [7 10 [15 14] [19 41]]) 106))
  (is (= (candidate [10 20 [30 40] [50 60]]) 210))
)

(run-test test-humaneval)