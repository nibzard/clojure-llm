(require '[clojure.test :refer [deftest is run-test]])

(def candidate union_elements)

(deftest test-humaneval

  (is (= (candidate [3 4 5 6] [5 7 4 10]) [3 4 5 6 7 10]))
  (is (= (candidate [1 2 3 4] [3 4 5 6]) [1 2 3 4 5 6]))
  (is (= (candidate [11 12 13 14] [13 15 16 17]) [11 12 13 14 15 16 17]))
)

(run-test test-humaneval)