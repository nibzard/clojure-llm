(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 2] [3 1]] (collapse-runs [1 1 1 2 2 3])))
  (is (= [] (collapse-runs [])))
  (is (= [] (collapse-runs nil))))

(run-test test-variation)
