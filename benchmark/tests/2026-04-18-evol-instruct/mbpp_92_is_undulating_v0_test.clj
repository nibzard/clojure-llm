(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(false true true true) (undulating-prefixes [1 2 1 2])))
  (is (= '(false false true true) (undulating-prefixes [5 5 5 5])))
  (is (= '() (undulating-prefixes nil))))

(run-test test-variation)
