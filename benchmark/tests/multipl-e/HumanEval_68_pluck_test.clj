(require '[clojure.test :refer [deftest is run-test]])

(def candidate pluck)

(deftest test-humaneval

  (is (= (candidate [4 2 3]) [2 1]))
  (is (= (candidate [1 2 3]) [2 1]))
  (is (= (candidate []) []))
  (is (= (candidate [5 0 3 0 4 2]) [0 1]))
  (is (= (candidate [1 2 3 0 5 3]) [0 3]))
  (is (= (candidate [5 4 8 4 8]) [4 1]))
  (is (= (candidate [7 6 7 1]) [6 1]))
  (is (= (candidate [7 9 7 1]) []))
)

(run-test test-humaneval)