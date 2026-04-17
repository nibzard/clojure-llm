(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_type)

(deftest test-humaneval

  (is (= (candidate [5 6 7 3 5 6]) true))
  (is (= (candidate [1 2 "4"]) false))
  (is (= (candidate [3 2 1 4 5]) true))
)

(run-test test-humaneval)