(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_Monotonic)

(deftest test-humaneval

  (is (= (candidate [6 5 4 4]) true))
  (is (= (candidate [1 2 2 3]) true))
  (is (= (candidate [1 3 2]) false))
)

(run-test test-humaneval)