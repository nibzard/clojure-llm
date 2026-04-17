(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_palindrome)

(deftest test-humaneval

  (is (= (candidate "") true))
  (is (= (candidate "aba") true))
  (is (= (candidate "aaaaa") true))
  (is (= (candidate "zbcd") false))
  (is (= (candidate "xywyx") true))
  (is (= (candidate "xywyz") false))
  (is (= (candidate "xywzx") false))
)

(run-test test-humaneval)