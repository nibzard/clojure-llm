(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_strictly_monotonic [1 2 3 4])))
  (is (= true (is_strictly_monotonic [4 3 2 1])))
  (is (= false (is_strictly_monotonic [1 1 2])))
  (is (= true (is_strictly_monotonic [])))
  (is (= true (is_strictly_monotonic nil))))

(run-test test-variation)
