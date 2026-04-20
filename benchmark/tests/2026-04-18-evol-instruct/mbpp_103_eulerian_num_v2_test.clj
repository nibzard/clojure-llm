(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1]] (eulerian_triangle 1)))
  (is (= [[1] [1 0] [1 1 0] [1 4 1 0]] (eulerian_triangle 4))))

(run-test test-variation)
