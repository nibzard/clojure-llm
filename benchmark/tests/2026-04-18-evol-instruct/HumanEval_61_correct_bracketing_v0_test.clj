(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delimiters "(a[1]{b})")))
  (is (= false (balanced-delimiters "([)]")))
  (is (= true (balanced-delimiters "foo(bar)[baz]{qux}")))
  (is (= false (balanced-delimiters "("))))

(run-test test-variation)
