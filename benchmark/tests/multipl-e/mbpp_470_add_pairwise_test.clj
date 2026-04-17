(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_pairwise)

(deftest test-humaneval

  (is (= (candidate [1 5 7 8 10]) [6 12 15 18]))
  (is (= (candidate [2 6 8 9 11]) [8 14 17 20]))
  (is (= (candidate [3 7 9 10 12]) [10 16 19 22]))
)

(run-test test-humaneval)