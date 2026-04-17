(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_remainder)

(deftest test-humaneval

  (is (= (candidate [100 10 5 25 35 14] 11) 9))
  (is (= (candidate [1 1 1] 1) 0))
  (is (= (candidate [1 2 1] 2) 0))
)

(run-test test-humaneval)