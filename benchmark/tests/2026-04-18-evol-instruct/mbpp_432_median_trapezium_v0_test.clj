(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (median-triangle [3 4 5]))))

(run-test test-variation)
