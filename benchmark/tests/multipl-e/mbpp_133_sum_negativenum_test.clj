(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_negativenum)

(deftest test-humaneval

  (is (= (candidate [2 4 -6 -9 11 -12 14 -5 17]) -32))
  (is (= (candidate [10 15 -14 13 -18 12 -20]) -52))
  (is (= (candidate [19 -65 57 39 152 -639 121 44 90 -190]) -894))
)

(run-test test-humaneval)