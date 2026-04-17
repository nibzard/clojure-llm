(require '[clojure.test :refer [deftest is run-test]])

(def candidate pos_count)

(deftest test-humaneval

  (is (= (candidate [1 -2 3 -4]) 2))
  (is (= (candidate [3 4 5 -1]) 3))
  (is (= (candidate [1 2 3 4]) 4))
)

(run-test test-humaneval)