(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (pair-xor-sum [1 2 3]))))

(run-test test-variation)
