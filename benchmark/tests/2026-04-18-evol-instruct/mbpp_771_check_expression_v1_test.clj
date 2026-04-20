(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delimiters? "(a[b]{c})")))
  (is (= false (balanced-delimiters? "([)]")))
  (is (= true (balanced-delimiters? nil))))

(run-test test-variation)
