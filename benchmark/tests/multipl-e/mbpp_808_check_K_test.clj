(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_K)

(deftest test-humaneval

  (is (= (candidate [10 4 5 6 8] 6) true))
  (is (= (candidate [1 2 3 4 5 6] 7) false))
  (is (= (candidate [7 8 9 44 11 12] 11) true))
)

(run-test test-humaneval)