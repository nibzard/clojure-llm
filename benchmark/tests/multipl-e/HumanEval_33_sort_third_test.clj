(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_third)

(deftest test-humaneval

  (is (= (candidate [5 6 3 4 8 9 2]) [2 6 3 4 8 9 5]))
  (is (= (candidate [5 8 3 4 6 9 2]) [2 8 3 4 6 9 5]))
  (is (= (candidate [5 6 9 4 8 3 2]) [2 6 9 4 8 3 5]))
  (is (= (candidate [5 6 3 4 8 9 2 1]) [2 6 3 4 8 9 5 1]))
)

(run-test test-humaneval)