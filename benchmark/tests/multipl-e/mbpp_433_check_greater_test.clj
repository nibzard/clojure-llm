(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_greater)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5] 4) false))
  (is (= (candidate [2 3 4 5 6] 8) true))
  (is (= (candidate [9 7 4 8 6 1] 11) true))
)

(run-test test-humaneval)