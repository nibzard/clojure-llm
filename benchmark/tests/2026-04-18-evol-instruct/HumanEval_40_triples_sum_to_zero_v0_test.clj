(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= false (triples_sum_to_zero_sorted [1 3 5 0])))
  (is (= true (triples_sum_to_zero_sorted [-4 -1 -1 0 1 2])))
  (is (= true (triples_sum_to_zero_sorted [0 0 0])))
  (is (= true (triples_sum_to_zero_sorted [-2 1 1])))
  (is (= false (triples_sum_to_zero_sorted [1 2 4 8]))))

(run-test test-variation)
