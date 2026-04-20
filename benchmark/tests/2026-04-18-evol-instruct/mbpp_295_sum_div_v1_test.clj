(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (proper-divisor-sum nil)))
  (is (= 0 (proper-divisor-sum 1)))
  (is (= 6 (proper-divisor-sum 6)))
  (is (= 16 (proper-divisor-sum 12)))
  (is (= 28 (proper-divisor-sum 28))))

(run-test test-variation)
