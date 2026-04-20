(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (middle-sum [5 1 9 3]))))

(run-test test-variation)
