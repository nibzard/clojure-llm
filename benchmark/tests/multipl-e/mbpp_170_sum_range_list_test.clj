(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_range_list)

(deftest test-humaneval

  (is (= (candidate [2 1 5 6 8 3 4 9 10 11 8 12] 8 10) 29))
  (is (= (candidate [2 1 5 6 8 3 4 9 10 11 8 12] 5 7) 16))
  (is (= (candidate [2 1 5 6 8 3 4 9 10 11 8 12] 7 10) 38))
)

(run-test test-humaneval)