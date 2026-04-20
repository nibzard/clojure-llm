(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (triples_sum_to_zero_sorted [-5 -2 0 1 4 7])))
  (is (= true (triples_sum_to_zero_sorted [-3 -1 2 4 8])))
  (is (= false (triples_sum_to_zero_sorted [-4 -1 1 2 9])))
  (is (= false (triples_sum_to_zero_sorted [])))
  (is (= false (triples_sum_to_zero_sorted nil))))

(run-test test-variation)
