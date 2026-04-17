(require '[clojure.test :refer [deftest is run-test]])

(def candidate below_zero)

(deftest test-humaneval

  (is (= (candidate []) false))
  (is (= (candidate [1 2 -3 1 2 -3]) false))
  (is (= (candidate [1 2 -4 5 6]) true))
  (is (= (candidate [1 -1 2 -2 5 -5 4 -4]) false))
  (is (= (candidate [1 -1 2 -2 5 -5 4 -5]) true))
  (is (= (candidate [1 -2 2 -2 5 -5 4 -4]) true))
)

(run-test test-humaneval)