(require '[clojure.test :refer [deftest is run-test]])

(def candidate first_repeated_char)

(deftest test-humaneval

  (is (= (candidate "abcabc") "a"))
  (is (= (candidate "abc") nil))
  (is (= (candidate "123123") "1"))
)

(run-test test-humaneval)