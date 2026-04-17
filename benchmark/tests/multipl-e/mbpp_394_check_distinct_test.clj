(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_distinct)

(deftest test-humaneval

  (is (= (candidate [1 4 5 6 1 4]) false))
  (is (= (candidate [1 4 5 6]) true))
  (is (= (candidate [2 3 4 5 6]) true))
)

(run-test test-humaneval)