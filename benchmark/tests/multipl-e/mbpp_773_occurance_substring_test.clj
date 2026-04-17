(require '[clojure.test :refer [deftest is run-test]])

(def candidate occurance_substring)

(deftest test-humaneval

  (is (= (candidate "python programming, python language" "python") ["python" 0 6]))
  (is (= (candidate "python programming,programming language" "programming") ["programming" 7 18]))
  (is (= (candidate "python programming,programming language" "language") ["language" 31 39]))
  (is (= (candidate "c++ programming, c++ language" "python") nil))
)

(run-test test-humaneval)