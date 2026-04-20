(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-positive-bits [1 -2 3 0 4]))))

(run-test test-variation)
