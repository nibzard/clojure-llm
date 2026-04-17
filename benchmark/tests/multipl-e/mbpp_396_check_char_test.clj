(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_char)

(deftest test-humaneval

  (is (= (candidate "abba") "Valid"))
  (is (= (candidate "a") "Valid"))
  (is (= (candidate "abcd") "Invalid"))
)

(run-test test-humaneval)