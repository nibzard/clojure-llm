(require '[clojure.test :refer [deftest is run-test]])

(def candidate issort_list)

(deftest test-humaneval

  (is (= (candidate [1 2 4 6 8 10 12 14 16 17]) true))
  (is (= (candidate [1 2 4 6 8 10 12 14 20 17]) false))
  (is (= (candidate [1 2 4 6 8 10 15 14 20]) false))
)

(run-test test-humaneval)