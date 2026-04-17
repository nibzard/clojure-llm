(require '[clojure.test :refer [deftest is run-test]])

(def candidate greatest_common_divisor)

(deftest test-humaneval

  (is (= (candidate 3 7) 1))
  (is (= (candidate 10 15) 5))
  (is (= (candidate 49 14) 7))
  (is (= (candidate 144 60) 12))
)

(run-test test-humaneval)