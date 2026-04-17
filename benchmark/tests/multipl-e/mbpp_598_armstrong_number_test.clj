(require '[clojure.test :refer [deftest is run-test]])

(def candidate armstrong_number)

(deftest test-humaneval

  (is (= (candidate 153) true))
  (is (= (candidate 259) false))
  (is (= (candidate 4458) false))
)

(run-test test-humaneval)