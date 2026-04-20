(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (digit-power-sum 2 3)))
  (is (= 9 (digit-power-sum 12 2)))
  (is (= 8 (digit-power-sum -5 3))))

(run-test test-variation)
