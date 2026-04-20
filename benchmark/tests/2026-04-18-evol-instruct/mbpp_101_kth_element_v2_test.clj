(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (kth_even [1 4 7 8 10] 2))))

(run-test test-variation)
