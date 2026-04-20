(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (sort_by_ends-parity [])))
  (is (= [5] (sort_by_ends-parity [5])))
  (is (= [5 3 2 1 0 -4] (sort_by_ends-parity [2 -4 3 0 1 5])))
  (is (= [6 4 3 2 1 0] (sort_by_ends-parity [2 4 3 0 1 6]))))

(run-test test-variation)
