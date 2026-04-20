(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 56 (sum-even-squares-in-range 1 6))))

(run-test test-variation)
