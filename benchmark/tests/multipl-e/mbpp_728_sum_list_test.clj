(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_list)

(deftest test-humaneval

  (is (= (candidate [10 20 30] [15 25 35]) [25 45 65]))
  (is (= (candidate [1 2 3] [5 6 7]) [6 8 10]))
  (is (= (candidate [15 20 30] [15 45 75]) [30 65 105]))
)

(run-test test-humaneval)