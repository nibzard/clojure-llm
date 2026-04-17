(require '[clojure.test :refer [deftest is run-test]])

(def candidate test_three_equal)

(deftest test-humaneval

  (is (= (candidate 1 1 1) 3))
  (is (= (candidate -1 -2 -3) 0))
  (is (= (candidate 1 2 2) 2))
)

(run-test test-humaneval)