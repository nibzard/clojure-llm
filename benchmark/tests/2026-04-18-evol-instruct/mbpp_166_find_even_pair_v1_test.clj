(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (count-zero-xor-pairs [1 1 2])))
  (is (= 3 (count-zero-xor-pairs [0 0 0])))
  (is (= 0 (count-zero-xor-pairs []))))

(run-test test-variation)
