(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (kth-merge [1 3 5] [2 4 6] 4))))

(run-test test-variation)
