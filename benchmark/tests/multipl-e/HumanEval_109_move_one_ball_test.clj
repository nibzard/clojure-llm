(require '[clojure.test :refer [deftest is run-test]])

(def candidate move_one_ball)

(deftest test-humaneval

  (is (= (candidate [3 4 5 1 2]) true))
  (is (= (candidate [3 5 10 1 2]) true))
  (is (= (candidate [4 3 1 2]) false))
  (is (= (candidate [3 5 4 1 2]) false))
  (is (= (candidate []) true))
)

(run-test test-humaneval)