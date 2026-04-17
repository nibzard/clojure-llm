(require '[clojure.test :refer [deftest is run-test]])

(def candidate largest_neg)

(deftest test-humaneval

  (is (= (candidate [1 2 3 -4 -6]) -6))
  (is (= (candidate [1 2 3 -8 -9]) -9))
  (is (= (candidate [1 2 3 4 -1]) -1))
)

(run-test test-humaneval)