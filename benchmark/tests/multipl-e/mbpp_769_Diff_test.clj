(require '[clojure.test :refer [deftest is run-test]])

(def candidate Diff)

(deftest test-humaneval

  (is (= (candidate [10 15 20 25 30 35 40] [25 40 35]) [10 20 30 15]))
  (is (= (candidate [1 2 3 4 5] [6 7 1]) [2 3 4 5 6 7]))
  (is (= (candidate [1 2 3] [6 7 1]) [2 3 6 7]))
)

(run-test test-humaneval)