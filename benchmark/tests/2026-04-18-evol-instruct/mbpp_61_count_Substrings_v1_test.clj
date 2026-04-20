(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-even-runs [1 2 4 3 6 8 10 5]))))

(run-test test-variation)
