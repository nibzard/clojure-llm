(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_Pairs)

(deftest test-humaneval

  (is (= (candidate [1 2 1] 3) 2))
  (is (= (candidate [1 1 1 1] 4) 0))
  (is (= (candidate [1 2 3 4 5] 5) 10))
)

(run-test test-humaneval)