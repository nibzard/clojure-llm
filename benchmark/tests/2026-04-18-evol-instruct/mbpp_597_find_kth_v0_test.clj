(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (kth-from-sorted [1 4 9] [2 3 8] 0)))
  (is (= 4 (kth-from-sorted [1 4 9] [2 3 8] 4)))
  (is (= 20 (kth-from-sorted nil [10 20 30] 1)))
  (is (= 5 (kth-from-sorted (range) [100 200] 5))))

(run-test test-variation)
