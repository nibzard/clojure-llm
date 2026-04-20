(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-run [false true true false true]))))

(run-test test-variation)
