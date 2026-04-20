(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[5 6 7]] (longest-subseq [[1] [2 3] [4] [5 6 7]]))))

(run-test test-variation)
