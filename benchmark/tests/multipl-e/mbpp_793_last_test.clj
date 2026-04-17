(require '[clojure.test :refer [deftest is run-test]])

(def candidate last)

(deftest test-humaneval

  (is (= (candidate [1 2 3] 1) 0))
  (is (= (candidate [1 1 1 2 3 4] 1) 2))
  (is (= (candidate [2 3 2 3 6 8 9] 3) 3))
)

(run-test test-humaneval)