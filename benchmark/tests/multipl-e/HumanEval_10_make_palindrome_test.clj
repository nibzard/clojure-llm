(require '[clojure.test :refer [deftest is run-test]])

(def candidate make_palindrome)

(deftest test-humaneval

  (is (= (candidate "") ""))
  (is (= (candidate "x") "x"))
  (is (= (candidate "xyz") "xyzyx"))
  (is (= (candidate "xyx") "xyx"))
  (is (= (candidate "jerry") "jerryrrej"))
)

(run-test test-humaneval)