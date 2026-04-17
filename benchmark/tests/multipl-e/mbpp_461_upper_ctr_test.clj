(require '[clojure.test :refer [deftest is run-test]])

(def candidate upper_ctr)

(deftest test-humaneval

  (is (= (candidate "PYthon") 1))
  (is (= (candidate "BigData") 1))
  (is (= (candidate "program") 0))
)

(run-test test-humaneval)