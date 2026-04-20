(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 14 (sum-squares-transduce 3 [1.0 2.0 3.0 4.0])))
  (is (= 0 (sum-squares-transduce 0 [100.0 200.0])))
  (is (= 5 (sum-squares-transduce 3 (range)))))

(run-test test-variation)
