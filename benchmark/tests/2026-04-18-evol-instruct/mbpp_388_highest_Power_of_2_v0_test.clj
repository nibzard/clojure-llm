(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (highest-power-of-2 17))))

(run-test test-variation)
