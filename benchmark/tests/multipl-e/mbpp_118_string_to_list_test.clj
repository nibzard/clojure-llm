(require '[clojure.test :refer [deftest is run-test]])

(def candidate string_to_list)

(deftest test-humaneval

  (is (= (candidate "python programming") ["python" "programming"]))
  (is (= (candidate "lists tuples strings") ["lists" "tuples" "strings"]))
  (is (= (candidate "write a program") ["write" "a" "program"]))
)

(run-test test-humaneval)