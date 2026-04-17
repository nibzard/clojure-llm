(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_samepair)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8] [2 2 3 1 2 6 7 9] [2 1 3 1 2 6 7 9]) 3))
  (is (= (candidate [1 2 3 4 5 6 7 8] [2 2 3 1 2 6 7 8] [2 1 3 1 2 6 7 8]) 4))
  (is (= (candidate [1 2 3 4 2 6 7 8] [2 2 3 1 2 6 7 8] [2 1 3 1 2 6 7 8]) 5))
)

(run-test test-humaneval)