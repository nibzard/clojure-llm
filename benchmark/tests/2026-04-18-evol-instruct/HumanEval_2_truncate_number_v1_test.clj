(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0.5 (normalize-fraction 3.5))))

(run-test test-variation)
