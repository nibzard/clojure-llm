(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_equal)

(deftest test-humaneval

  (is (= (candidate [[11 22 33] [44 55 66]]) true))
  (is (= (candidate [[1 2 3] [4 5 6 7]]) false))
  (is (= (candidate [[1 2] [3 4]]) true))
)

(run-test test-humaneval)