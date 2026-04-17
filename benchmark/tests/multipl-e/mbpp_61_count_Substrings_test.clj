(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_Substrings)

(deftest test-humaneval

  (is (= (candidate "112112") 6))
  (is (= (candidate "111") 6))
  (is (= (candidate "1101112") 12))
)

(run-test test-humaneval)