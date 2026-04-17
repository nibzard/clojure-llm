(require '[clojure.test :refer [deftest is run-test]])

(def candidate tuple_modulo)

(deftest test-humaneval

  (is (= (candidate [10 4 5 6] [5 6 7 5]) [0 4 5 1]))
  (is (= (candidate [11 5 6 7] [6 7 8 6]) [5 5 6 1]))
  (is (= (candidate [12 6 7 8] [7 8 9 7]) [5 6 7 1]))
)

(run-test test-humaneval)