(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_nested)

(deftest test-humaneval

  (is (= (candidate [1 5 7 [4 6] 10]) [1 5 7 10]))
  (is (= (candidate [2 6 8 [5 7] 11]) [2 6 8 11]))
  (is (= (candidate [3 7 9 [6 8] 12]) [3 7 9 12]))
  (is (= (candidate [3 7 9 [6 8] [5 12] 12]) [3 7 9 12]))
)

(run-test test-humaneval)