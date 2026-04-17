(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_integer)

(deftest test-humaneval

  (is (= (candidate [1 2 "abc" 1.2]) 2))
  (is (= (candidate [1 2 3]) 3))
  (is (= (candidate [1 1.2 4 5.1]) 2))
)

(run-test test-humaneval)