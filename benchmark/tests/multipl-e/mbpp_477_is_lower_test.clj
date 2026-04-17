(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_lower)

(deftest test-humaneval

  (is (= (candidate "InValid") "invalid"))
  (is (= (candidate "TruE") "true"))
  (is (= (candidate "SenTenCE") "sentence"))
)

(run-test test-humaneval)