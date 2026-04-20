(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9534330 (max-number-from-digits [3 30 34 5 9]))))

(run-test test-variation)
