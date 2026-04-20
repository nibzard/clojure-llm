(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (has-consecutive-run? [1 2 5]))))

(run-test test-variation)
