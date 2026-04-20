(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-nested-lists '(1 (2 3) [4 (5)] nil))))
  (is (= 0 (count-nested-lists nil))))

(run-test test-variation)
