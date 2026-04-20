(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-empty-collections [() [] {} #{1} nil "hi" (list 1 2)]))))

(run-test test-variation)
