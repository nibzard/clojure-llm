(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_upper)

(deftest test-humaneval

  (is (= (candidate "person") "PERSON"))
  (is (= (candidate "final") "FINAL"))
  (is (= (candidate "Valid") "VALID"))
)

(run-test test-humaneval)