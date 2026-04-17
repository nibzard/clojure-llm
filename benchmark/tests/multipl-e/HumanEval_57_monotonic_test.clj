(require '[clojure.test :refer [deftest is run-test]])

(def candidate monotonic)

(deftest test-humaneval

  (is (= (candidate [1 2 4 10]) true))
  (is (= (candidate [1 2 4 20]) true))
  (is (= (candidate [1 20 4 10]) false))
  (is (= (candidate [4 1 0 -10]) true))
  (is (= (candidate [4 1 1 0]) true))
  (is (= (candidate [1 2 3 2 5 60]) false))
  (is (= (candidate [1 2 3 4 5 60]) true))
  (is (= (candidate [9 9 9 9]) true))
)

(run-test test-humaneval)