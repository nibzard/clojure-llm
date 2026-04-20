(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-out-of-order [2 4 1 3 5]))))

(run-test test-variation)
