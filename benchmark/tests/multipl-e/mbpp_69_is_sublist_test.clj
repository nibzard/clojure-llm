(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_sublist)

(deftest test-humaneval

  (is (= (candidate [2 4 3 5 7] [3 7]) false))
  (is (= (candidate [2 4 3 5 7] [4 3]) true))
  (is (= (candidate [2 4 3 5 7] [1 6]) false))
)

(run-test test-humaneval)