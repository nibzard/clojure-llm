(require '[clojure.test :refer [deftest is run-test]])

(def candidate sumofFactors)

(deftest test-humaneval

  (is (= (candidate 18) 26))
  (is (= (candidate 30) 48))
  (is (= (candidate 6) 8))
)

(run-test test-humaneval)