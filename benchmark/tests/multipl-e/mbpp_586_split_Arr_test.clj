(require '[clojure.test :refer [deftest is run-test]])

(def candidate split_Arr)

(deftest test-humaneval

  (is (= (candidate [12 10 5 6 52 36] 2) [5 6 52 36 12 10]))
  (is (= (candidate [1 2 3 4] 1) [2 3 4 1]))
  (is (= (candidate [0 1 2 3 4 5 6 7] 3) [3 4 5 6 7 0 1 2]))
)

(run-test test-humaneval)