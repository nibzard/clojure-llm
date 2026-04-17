(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_min_heap)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6]) true))
  (is (= (candidate [2 3 4 5 10 15]) true))
  (is (= (candidate [2 10 4 5 3 15]) false))
)

(run-test test-humaneval)