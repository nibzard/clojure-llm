(require '[clojure.test :refer [deftest is run-test]])

(def candidate big_diff)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4]) 3))
  (is (= (candidate [4 5 12]) 8))
  (is (= (candidate [9 2 3]) 7))
)

(run-test test-humaneval)