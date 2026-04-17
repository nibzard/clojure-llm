(require '[clojure.test :refer [deftest is run-test]])

(def candidate long_words)

(deftest test-humaneval

  (is (= (candidate 3 "python is a programming language") ["python" "programming" "language"]))
  (is (= (candidate 2 "writing a program") ["writing" "program"]))
  (is (= (candidate 5 "sorting list") ["sorting"]))
)

(run-test test-humaneval)