(require '[clojure.test :refer [deftest is run-test]])

(def candidate decimal_to_binary)

(deftest test-humaneval

  (is (= (candidate 0) "db0db"))
  (is (= (candidate 32) "db100000db"))
  (is (= (candidate 103) "db1100111db"))
  (is (= (candidate 15) "db1111db"))
)

(run-test test-humaneval)