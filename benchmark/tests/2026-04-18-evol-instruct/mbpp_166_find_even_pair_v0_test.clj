(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-odd-xor-pairs [1 2 3 4])))
  (is (= 0 (count-odd-xor-pairs [2 4 6])))
  (is (= 0 (count-odd-xor-pairs nil))))

(run-test test-variation)
