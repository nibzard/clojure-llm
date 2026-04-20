(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (sum-of-odd-squares 0)))
  (is (= 1 (sum-of-odd-squares 1)))
  (is (= 84 (sum-of-odd-squares 4))))

(run-test test-variation)
