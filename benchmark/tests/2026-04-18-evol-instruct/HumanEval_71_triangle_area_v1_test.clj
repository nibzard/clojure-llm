(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12.0 (polygon_area [0 0 4 0 4 3 0 3])))
  (is (= -1 (polygon_area [0 0 1 1 2 2 3 3]))))

(run-test test-variation)
