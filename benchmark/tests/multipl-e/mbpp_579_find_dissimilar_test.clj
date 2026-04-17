(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_dissimilar)

(deftest test-humaneval

  (is (= (candidate [3 4 5 6] [5 7 4 10]) [3 6 7 10]))
  (is (= (candidate [1 2 3 4] [7 2 3 9]) [1 4 7 9]))
  (is (= (candidate [21 11 25 26] [26 34 21 36]) [34 36 11 25]))
)

(run-test test-humaneval)