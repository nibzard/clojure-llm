(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1/4] (parabola-focus 1 0 0)))
  (is (= [1 1/8] (parabola-focus 2 -4 1)))
  (is (= nil (parabola-focus 0 3 2))))

(run-test test-variation)
