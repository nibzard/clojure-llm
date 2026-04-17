(require '[clojure.test :refer [deftest is run-test]])

(def candidate checks)

(deftest test-humaneval

  (is (= (candidate 70) false))
  (is (= (candidate 23) false))
  (is (= (candidate 73) true))
)

(run-test test-humaneval)