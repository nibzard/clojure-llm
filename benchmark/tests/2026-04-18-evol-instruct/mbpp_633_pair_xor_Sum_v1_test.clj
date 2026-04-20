(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (pairwise-xor-count [1 2 3 1] 2)))
  (is (= 3 (pairwise-xor-count [5 5 5] 0)))
  (is (= 0 (pairwise-xor-count [] 7))))

(run-test test-variation)
