(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (kth-merged [1 3 5] [2 4 6] 4)))
  (is (= 20 (kth-merged [1 2 3] [10 20 30] 5)))
  (is (= 1 (kth-merged [1 4 7] [2 3 5 6] 1))))

(run-test test-variation)
