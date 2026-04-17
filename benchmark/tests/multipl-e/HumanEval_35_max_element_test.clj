(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_element)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 3))
  (is (= (candidate [5 3 -5 2 -3 3 9 0 124 1 -10]) 124))
)

(run-test test-humaneval)