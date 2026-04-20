(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1/4] (parabola-focus 1 0 0)))
  (is (= [-1 0] (parabola-focus 2 4 1))))

(run-test test-variation)
