(require '[clojure.test :refer [deftest is run-test]])

(def candidate rolling_max)

(deftest test-humaneval

  (is (= (candidate []) []))
  (is (= (candidate [1 2 3 4]) [1 2 3 4]))
  (is (= (candidate [4 3 2 1]) [4 4 4 4]))
  (is (= (candidate [3 2 3 100 3]) [3 3 3 100 100]))
)

(run-test test-humaneval)