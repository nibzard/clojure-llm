(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 18 (sum-of-even-factors 12)))
  (is (= 0 (sum-of-even-factors 7)))
  (is (= nil (sum-of-even-factors nil))))

(run-test test-variation)
