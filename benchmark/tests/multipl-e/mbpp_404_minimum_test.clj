(require '[clojure.test :refer [deftest is run-test]])

(def candidate minimum)

(deftest test-humaneval

  (is (= (candidate 1 2) 1))
  (is (= (candidate -5 -4) -5))
  (is (= (candidate 0 0) 0))
)

(run-test test-humaneval)