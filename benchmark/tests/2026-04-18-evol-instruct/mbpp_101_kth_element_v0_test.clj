(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 20 (kth-infinite [10 20 30] 2)))
  (is (= 4 (kth-infinite (range) 5)))
  (is (= nil (kth-infinite [10 20 30] 0)))
  (is (= nil (kth-infinite [10 20 30] 10))))

(run-test test-variation)
