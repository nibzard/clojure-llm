(require '[clojure.test :refer [deftest is run-test]])

(def candidate solve)

(deftest test-humaneval

  (is (= (candidate 1000) "1"))
  (is (= (candidate 150) "110"))
  (is (= (candidate 147) "1100"))
  (is (= (candidate 333) "1001"))
  (is (= (candidate 963) "10010"))
)

(run-test test-humaneval)