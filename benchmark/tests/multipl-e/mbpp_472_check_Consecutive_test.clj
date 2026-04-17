(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_Consecutive)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5]) true))
  (is (= (candidate [1 2 3 5 6]) false))
  (is (= (candidate [1 2 1]) false))
)

(run-test test-humaneval)