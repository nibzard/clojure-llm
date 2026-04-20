(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (highest-power-of-2-below 19)))
  (is (= 32 (highest-power-of-2-below 32)))
  (is (= 0 (highest-power-of-2-below 0))))

(run-test test-variation)
