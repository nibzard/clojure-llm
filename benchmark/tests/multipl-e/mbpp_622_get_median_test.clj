(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_median)

(deftest test-humaneval

  (is (= (candidate [1 12 15 26 38] [2 13 17 30 45] 5) 16.0))
  (is (= (candidate [2 4 8 9] [7 13 19 28] 4) 8.5))
  (is (= (candidate [3 6 14 23 36 42] [2 18 27 39 49 55] 6) 25.0))
)

(run-test test-humaneval)