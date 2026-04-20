(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (digit-distance-vectors [1 2 3] [4 5 6]))))

(run-test test-variation)
