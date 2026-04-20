(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (max-abs-diff-stream [3 8 2 10 5]))))

(run-test test-variation)
