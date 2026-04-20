(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-xor-odd-pairs [1 2 3 4])))
  (is (= 0 (count-xor-odd-pairs [0 0 1])))
  (is (= 0 (count-xor-odd-pairs nil))))

(run-test test-variation)
