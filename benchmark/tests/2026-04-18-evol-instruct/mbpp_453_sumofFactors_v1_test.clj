(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (sum-of-even-factors 6)))
  (is (= 0 (sum-of-even-factors 15)))
  (is (= 0 (sum-of-even-factors nil))))

(run-test test-variation)
