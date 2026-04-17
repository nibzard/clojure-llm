(require '[clojure.test :refer [deftest is run-test]])

(def candidate validate)

(deftest test-humaneval

  (is (= (candidate 1234) true))
  (is (= (candidate 51241) false))
  (is (= (candidate 321) true))
)

(run-test test-humaneval)