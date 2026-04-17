(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_none)

(deftest test-humaneval

  (is (= (candidate [10 4 5 6 nil]) true))
  (is (= (candidate [7 8 9 11 14]) false))
  (is (= (candidate [1 2 3 4 nil]) true))
)

(run-test test-humaneval)