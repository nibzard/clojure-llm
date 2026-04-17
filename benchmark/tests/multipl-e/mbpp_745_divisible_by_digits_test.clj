(require '[clojure.test :refer [deftest is run-test]])

(def candidate divisible_by_digits)

(deftest test-humaneval

  (is (= (candidate 1 22) [1 2 3 4 5 6 7 8 9 11 12 15 22]))
  (is (= (candidate 1 15) [1 2 3 4 5 6 7 8 9 11 12 15]))
  (is (= (candidate 20 25) [22 24]))
)

(run-test test-humaneval)