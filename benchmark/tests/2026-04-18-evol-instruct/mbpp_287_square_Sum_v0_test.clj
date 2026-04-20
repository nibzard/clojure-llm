(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (sum-of-squares-odd-prefix 1)))
  (is (= 35 (sum-of-squares-odd-prefix 3)))
  (is (= 165 (sum-of-squares-odd-prefix 5))))

(run-test test-variation)
