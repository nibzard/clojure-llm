(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 1, 3 1} (count-set-bits 10))))

(run-test test-variation)
