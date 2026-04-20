(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (max-of-nth [[1 2 3] [4 5 6] [0 9 8]] 1))))

(run-test test-variation)
