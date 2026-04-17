(require '[clojure.test :refer [deftest is run-test]])

(def candidate incr_list)

(deftest test-humaneval

  (is (= (candidate []) []))
  (is (= (candidate [3 2 1]) [4 3 2]))
  (is (= (candidate [5 2 5 2 3 3 9 0 123]) [6 3 6 3 4 4 10 1 124]))
)

(run-test test-humaneval)