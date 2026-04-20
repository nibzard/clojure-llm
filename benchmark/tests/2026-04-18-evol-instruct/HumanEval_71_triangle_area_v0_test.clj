(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12.0 (triangle-perimeter 3 4 5)))
  (is (= -1 (triangle-perimeter 1 2 10)))
  (is (= -1 (triangle-perimeter nil 4 5))))

(run-test test-variation)
