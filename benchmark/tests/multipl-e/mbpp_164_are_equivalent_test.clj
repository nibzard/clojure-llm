(require '[clojure.test :refer [deftest is run-test]])

(def candidate are_equivalent)

(deftest test-humaneval

  (is (= (candidate 36 57) false))
  (is (= (candidate 2 4) false))
  (is (= (candidate 23 47) true))
)

(run-test test-humaneval)