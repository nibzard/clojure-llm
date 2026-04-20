(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "b" "c"] (split-with-delim "a,b,c" \,)))
  (is (= ["hello"] (split-with-delim "hello" \,)))
  (is (= ["a" "" "b"] (split-with-delim "a,,b" \,)))
  (is (= [] (split-with-delim nil \,))))

(run-test test-variation)
