(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_values_string)

(deftest test-humaneval

  (is (= (candidate "abcdef") "ace"))
  (is (= (candidate "python") "pto"))
  (is (= (candidate "data") "dt"))
  (is (= (candidate "lambs") "lms"))
)

(run-test test-humaneval)