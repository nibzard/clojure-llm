(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 1} (odd-collatz-counts 1)))
  (is (= {1 1, 5 1} (odd-collatz-counts 5)))
  (is (= {1 1, 5 1, 13 1, 17 1, 31 1, 41 1} (odd-collatz-counts 13))))

(run-test test-variation)
