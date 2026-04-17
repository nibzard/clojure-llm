(require '[clojure.test :refer [deftest is run-test]])

(def candidate division_elements)

(deftest test-humaneval

  (is (= (candidate [10 4 6 9] [5 2 3 3]) [2 2 2 3]))
  (is (= (candidate [12 6 8 16] [6 3 4 4]) [2 2 2 4]))
  (is (= (candidate [20 14 36 18] [5 7 6 9]) [4 2 6 2]))
)

(run-test test-humaneval)