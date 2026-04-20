(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-in-range 1 7)))
  (is (= 9 (sum-in-range 2 6)))
  (is (= 0 (sum-in-range 7 1))))

(run-test test-variation)
