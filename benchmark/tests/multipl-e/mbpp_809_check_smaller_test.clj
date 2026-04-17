(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_smaller)

(deftest test-humaneval

  (is (= (candidate [1 2 3] [2 3 4]) false))
  (is (= (candidate [4 5 6] [3 4 5]) true))
  (is (= (candidate [11 12 13] [10 11 12]) true))
)

(run-test test-humaneval)