(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Odd_Pair)

(deftest test-humaneval

  (is (= (candidate [5 4 7 2 1] 5) 6))
  (is (= (candidate [7 2 8 1 0 5 11] 7) 12))
  (is (= (candidate [1 2 3] 3) 2))
)

(run-test test-humaneval)