(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_upper)

(deftest test-humaneval

  (is (= (candidate "aBCdEf") 1))
  (is (= (candidate "abcdefg") 0))
  (is (= (candidate "dBBE") 0))
  (is (= (candidate "B") 0))
  (is (= (candidate "U") 1))
  (is (= (candidate "") 0))
  (is (= (candidate "EEEE") 2))
)

(run-test test-humaneval)