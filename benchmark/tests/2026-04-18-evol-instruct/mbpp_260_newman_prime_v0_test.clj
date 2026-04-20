(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '((2 3) (5)) (prime-partitions 5))))

(run-test test-variation)
