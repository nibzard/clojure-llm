(require '[clojure.test :refer [deftest is run-test]])

(def candidate second_smallest)

(deftest test-humaneval

  (is (= (candidate [1 2 -8 -2 0 -2]) -2))
  (is (= (candidate [1 1 -0.5 0 2 -2 -2]) -0.5))
  (is (= (candidate [2 2]) nil))
  (is (= (candidate [2 2 2]) nil))
)

(run-test test-humaneval)