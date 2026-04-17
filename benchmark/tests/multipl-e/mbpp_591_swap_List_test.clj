(require '[clojure.test :refer [deftest is run-test]])

(def candidate swap_List)

(deftest test-humaneval

  (is (= (candidate [12 35 9 56 24]) [24 35 9 56 12]))
  (is (= (candidate [1 2 3]) [3 2 1]))
  (is (= (candidate [4 5 6]) [6 5 4]))
)

(run-test test-humaneval)