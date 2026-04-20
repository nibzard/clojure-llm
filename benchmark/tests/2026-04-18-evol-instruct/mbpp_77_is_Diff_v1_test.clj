(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (divisible-by-11? 121)))
  (is (= false (divisible-by-11? 123)))
  (is (= true (divisible-by-11? -44)))
  (is (= false (divisible-by-11? nil))))

(run-test test-variation)
