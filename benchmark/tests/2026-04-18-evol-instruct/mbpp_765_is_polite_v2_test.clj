(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (kth-distinct [1 2 2 3 4 4 5] 1)))
  (is (= 3 (kth-distinct [1 2 2 3 4 4 5] 2)))
  (is (= 5 (kth-distinct [1 2 2 3 4 4 5] 3)))
  (is (= nil (kth-distinct [1 1 1] 1))))

(run-test test-variation)
