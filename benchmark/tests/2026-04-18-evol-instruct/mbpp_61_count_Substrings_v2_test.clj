(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-even-prefix-sums [1 2 3 4]))))

(run-test test-variation)
