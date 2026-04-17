(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_integer)

(deftest test-humaneval

  (is (= (candidate "python") false))
  (is (= (candidate "1") true))
  (is (= (candidate "12345") true))
)

(run-test test-humaneval)