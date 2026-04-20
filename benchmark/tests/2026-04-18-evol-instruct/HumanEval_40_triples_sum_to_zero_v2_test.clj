(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (triples_sum_to_zero_sorted [-5 -2 0 1 3 7])))
  (is (= false (triples_sum_to_zero_sorted [-4 -1 2 5 9])))
  (is (= true (triples_sum_to_zero_sorted (range -10 10))))
  (is (= false (triples_sum_to_zero_sorted [1 2 3]))))

(run-test test-variation)
