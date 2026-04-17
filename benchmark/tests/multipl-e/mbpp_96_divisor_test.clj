(require '[clojure.test :refer [deftest is run-test]])

(def candidate divisor)

(deftest test-humaneval

  (is (= (candidate 15) 4))
  (is (= (candidate 12) 6))
  (is (= (candidate 9) 3))
)

(run-test test-humaneval)