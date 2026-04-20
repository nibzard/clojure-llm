(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delimiters? "(a+[b*c]-{d/e})"))))

(run-test test-variation)
