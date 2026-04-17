(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_divisors)

(deftest test-humaneval

  (is (= (candidate 10) true))
  (is (= (candidate 100) false))
  (is (= (candidate 125) true))
)

(run-test test-humaneval)