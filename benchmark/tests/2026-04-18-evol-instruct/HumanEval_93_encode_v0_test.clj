(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1] [1 2] [1 2 3] [4] [4 5]]
         (interleave-prefix [[1 2 3] [4 5]]))))

(run-test test-variation)
