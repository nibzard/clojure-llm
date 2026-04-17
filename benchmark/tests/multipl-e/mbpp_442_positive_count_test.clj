(require '[clojure.test :refer [deftest is run-test]])

(def candidate positive_count)

(deftest test-humaneval

  (is (= (candidate [0 1 2 -1 -5 6 0 -3 -2 3 4 6 8]) 0.54))
  (is (= (candidate [2 1 2 -1 -5 6 4 -3 -2 3 4 6 8]) 0.69))
  (is (= (candidate [2 4 -6 -9 11 -12 14 -5 17]) 0.56))
)

(run-test test-humaneval)