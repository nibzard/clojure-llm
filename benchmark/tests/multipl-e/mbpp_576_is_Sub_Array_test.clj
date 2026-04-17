(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_Sub_Array)

(deftest test-humaneval

  (is (= (candidate [1 4 3 5] [1 2]) false))
  (is (= (candidate [1 2 1] [1 2 1]) true))
  (is (= (candidate [1 0 2 2] [2 2 0]) false))
)

(run-test test-humaneval)