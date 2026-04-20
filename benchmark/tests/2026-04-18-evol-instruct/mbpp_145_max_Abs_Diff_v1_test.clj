(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-abs-diff-in-order [2 7 1 8])))
  (is (= 9 (max-abs-diff-in-order [10 3 6 1])))
  (is (= 0 (max-abs-diff-in-order nil))))

(run-test test-variation)
