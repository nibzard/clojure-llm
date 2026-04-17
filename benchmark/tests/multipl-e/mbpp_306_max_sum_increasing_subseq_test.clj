(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_sum_increasing_subseq)

(deftest test-humaneval

  (is (= (candidate [1 101 2 3 100 4 5] 7 4 6) 11))
  (is (= (candidate [1 101 2 3 100 4 5] 7 2 5) 7))
  (is (= (candidate [11 15 19 21 26 28 31] 7 2 4) 71))
)

(run-test test-humaneval)