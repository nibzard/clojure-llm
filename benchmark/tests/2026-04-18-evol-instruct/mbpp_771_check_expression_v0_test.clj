(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delims? "([{}])")))
  (is (= false (balanced-delims? "([)]")))
  (is (= true (balanced-delims? nil))))

(run-test test-variation)
