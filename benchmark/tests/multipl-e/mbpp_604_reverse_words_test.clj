(require '[clojure.test :refer [deftest is run-test]])

(def candidate reverse_words)

(deftest test-humaneval

  (is (= (candidate "python program") "program python"))
  (is (= (candidate "java language") "language java"))
  (is (= (candidate "indian man") "man indian"))
)

(run-test test-humaneval)