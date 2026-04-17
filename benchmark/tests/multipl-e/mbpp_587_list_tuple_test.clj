(require '[clojure.test :refer [deftest is run-test]])

(def candidate list_tuple)

(deftest test-humaneval

  (is (= (candidate [5 10 7 4 15 3]) [5 10 7 4 15 3]))
  (is (= (candidate [2 4 5 6 2 3 4 4 7]) [2 4 5 6 2 3 4 4 7]))
  (is (= (candidate [58 44 56]) [58 44 56]))
)

(run-test test-humaneval)