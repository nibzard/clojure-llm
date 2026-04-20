(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (average-of-cubes [1 2 3])))
  (is (= 72 (average-of-cubes [2 4 6])))
  (is (= 0 (average-of-cubes nil)))
  (is (= 0 (average-of-cubes []))))

(run-test test-variation)
