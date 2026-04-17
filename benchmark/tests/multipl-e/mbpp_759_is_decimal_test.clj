(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_decimal)

(deftest test-humaneval

  (is (= (candidate "123.11") true))
  (is (= (candidate "e666.86") false))
  (is (= (candidate "3.124587") false))
  (is (= (candidate "1.11") true))
  (is (= (candidate "1.1.11") false))
)

(run-test test-humaneval)