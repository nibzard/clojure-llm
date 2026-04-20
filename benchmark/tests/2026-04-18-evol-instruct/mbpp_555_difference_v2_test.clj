(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (alternating-sum 5))))

(run-test test-variation)
