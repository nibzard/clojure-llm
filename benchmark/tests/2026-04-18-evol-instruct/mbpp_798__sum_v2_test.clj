(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (sum-even-values [1 2 3 4 5])))
  (is (= 0 (sum-even-values nil)))
  (is (= 20 (sum-even-values '(2 4 6 8))))
  (is (= 0 (sum-even-values [1 3 5]))))

(run-test test-variation)
