(require '[clojure.test :refer [deftest is run-test]])

(def candidate removezero_ip)

(deftest test-humaneval

  (is (= (candidate "216.08.094.196") "216.8.94.196"))
  (is (= (candidate "12.01.024") "12.1.24"))
  (is (= (candidate "216.08.094.0196") "216.8.94.196"))
)

(run-test test-humaneval)