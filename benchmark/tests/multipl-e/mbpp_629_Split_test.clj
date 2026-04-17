(require '[clojure.test :refer [deftest is run-test]])

(def candidate Split)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5]) [2 4]))
  (is (= (candidate [4 5 6 7 8 0 1]) [4 6 8 0]))
  (is (= (candidate [8 12 15 19]) [8 12]))
)

(run-test test-humaneval)