(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delimiters "()")))
  (is (= true (balanced-delimiters "([{}])")))
  (is (= false (balanced-delimiters "([)]"))))

(run-test test-variation)
