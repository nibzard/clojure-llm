(require '[clojure.test :refer [deftest is run-test]])

(def candidate frequency)

(deftest test-humaneval

  (is (= (candidate [1 2 3] 4) 0))
  (is (= (candidate [1 2 2 3 3 3 4] 3) 3))
  (is (= (candidate [0 1 2 3 1 2] 1) 2))
)

(run-test test-humaneval)