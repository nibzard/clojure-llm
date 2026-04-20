(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-run-of-true [true true false true])))
  (is (= 4 (max-run-of-true [false true true true true false])))
  (is (= 0 (max-run-of-true nil))))

(run-test test-variation)
