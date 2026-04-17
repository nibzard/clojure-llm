(require '[clojure.test :refer [deftest is run-test]])

(def candidate overlapping)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5] [6 7 8 9]) false))
  (is (= (candidate [1 2 3] [4 5 6]) false))
  (is (= (candidate [1 4 5] [1 4 5]) true))
)

(run-test test-humaneval)