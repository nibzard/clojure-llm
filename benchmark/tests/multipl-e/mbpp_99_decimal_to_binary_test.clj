(require '[clojure.test :refer [deftest is run-test]])

(def candidate decimal_to_binary)

(deftest test-humaneval

  (is (= (candidate 8) "1000"))
  (is (= (candidate 18) "10010"))
  (is (= (candidate 7) "111"))
)

(run-test test-humaneval)