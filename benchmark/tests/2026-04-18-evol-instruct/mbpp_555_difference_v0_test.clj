(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (difference-of-powers 1)))
  (is (= 29 (difference-of-powers 3)))
  (is (= 0 (difference-of-powers nil))))

(run-test test-variation)
