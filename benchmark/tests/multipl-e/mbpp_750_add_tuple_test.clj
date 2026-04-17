(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_tuple)

(deftest test-humaneval

  (is (= (candidate [5 6 7] [9 10]) [5 6 7 9 10]))
  (is (= (candidate [6 7 8] [10 11]) [6 7 8 10 11]))
  (is (= (candidate [7 8 9] [11 12]) [7 8 9 11 12]))
)

(run-test test-humaneval)