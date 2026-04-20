(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 4 6 1 3 7] (sort_by_parity_then_value [3 2 7 4 1 6])))
  (is (= '(0 2 8 -5 3 9) (sort_by_parity_then_value '(-5 8 0 3 2 9))))
  (is (= [2 4 1 3 5] (sort_by_parity_then_value (take 5 (iterate inc 1)))))
  (is (= [] (sort_by_parity_then_value []))))

(run-test test-variation)
