(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-element [1 1 1] 1))))

(run-test test-variation)
