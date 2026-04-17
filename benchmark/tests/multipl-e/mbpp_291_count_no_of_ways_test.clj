(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_no_of_ways)

(deftest test-humaneval

  (is (= (candidate 2 4) 16))
  (is (= (candidate 3 2) 6))
  (is (= (candidate 4 4) 228))
)

(run-test test-humaneval)