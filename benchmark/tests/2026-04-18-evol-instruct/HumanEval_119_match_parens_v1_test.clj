(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "Yes" (balanced-from-parts ["()" "(())"])))
  (is (= "Yes" (balanced-from-parts [")" "("])))
  (is (= "No" (balanced-from-parts ["(" ")("]))))

(run-test test-variation)
