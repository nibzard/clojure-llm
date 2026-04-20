(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-nested-seqs [1 [2 3] '(4 [5])]))))

(run-test test-variation)
