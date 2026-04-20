(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 0 0] (xor-bits [0 1 0] [1 1 0]))))

(run-test test-variation)
