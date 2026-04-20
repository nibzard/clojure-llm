(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (sum-binary-digits [1 0 0 0])))
  (is (= 3 (sum-binary-digits [1 0 0 1 1 0])))
  (is (= 0 (sum-binary-digits [])))
  (is (= 500000 (sum-binary-digits (take 1000000 (cycle [1 0]))))))

(run-test test-variation)
