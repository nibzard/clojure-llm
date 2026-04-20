(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (max_abs_product [1 -3 2 4]))))

(run-test test-variation)
