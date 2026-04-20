(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (highest-power-of-2-in-range 20))))

(run-test test-variation)
