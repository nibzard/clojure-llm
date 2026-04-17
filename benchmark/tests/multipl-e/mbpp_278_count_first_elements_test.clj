(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_first_elements)

(deftest test-humaneval

  (is (= (candidate [1 5 7 [4 6] 10]) 3))
  (is (= (candidate [2 9 [5 7] 11]) 2))
  (is (= (candidate [11 15 5 8 [2 3] 8]) 4))
)

(run-test test-humaneval)