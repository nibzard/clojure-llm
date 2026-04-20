(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-run-of-true [false true true false true])))
  (is (= 3 (max-run-of-true [nil nil true true true false true])))
  (is (= 0 (max-run-of-true []))))

(run-test test-variation)
