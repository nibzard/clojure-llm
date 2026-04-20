(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (max-of-nth-infinite [[1 2 3] [4 5 6] [7 8 9]] 1))))

(run-test test-variation)
