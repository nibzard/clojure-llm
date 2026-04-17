(require '[clojure.test :refer [deftest is run-test]])

(def candidate magic_square_test)

(deftest test-humaneval

  (is (= (candidate [[7 12 1 14] [2 13 8 11] [16 3 10 5] [9 6 15 4]]) true))
  (is (= (candidate [[2 7 6] [9 5 1] [4 3 8]]) true))
  (is (= (candidate [[2 7 6] [9 5 1] [4 3 7]]) false))
)

(run-test test-humaneval)