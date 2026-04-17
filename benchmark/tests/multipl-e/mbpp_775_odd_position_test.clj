(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_position)

(deftest test-humaneval

  (is (= (candidate [2 1 4 3 6 7 6 3]) true))
  (is (= (candidate [4 1 2]) true))
  (is (= (candidate [1 2 3]) false))
)

(run-test test-humaneval)