(require '[clojure.test :refer [deftest is run-test]])

(def candidate test_duplicate)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5]) false))
  (is (= (candidate [1 2 3 4 4]) true))
  (is (= (candidate [1 1 2 2 3 3 4 4 5]) true))
)

(run-test test-humaneval)