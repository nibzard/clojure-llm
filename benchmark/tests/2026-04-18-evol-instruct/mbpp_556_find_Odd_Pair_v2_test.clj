(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-odd-xor-pairs [1 2 3 4])))
  (is (= 0 (count-odd-xor-pairs nil)))
  (is (= 6 (count-odd-xor-pairs [1 3 5 2])))
  (is (= 3 (count-odd-xor-pairs (take 4 (iterate inc 1)))))))

(run-test test-variation)
