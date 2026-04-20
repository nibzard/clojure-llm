(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-frequencies [1 2 2 3] '(2 3 2 1)))))

(run-test test-variation)
