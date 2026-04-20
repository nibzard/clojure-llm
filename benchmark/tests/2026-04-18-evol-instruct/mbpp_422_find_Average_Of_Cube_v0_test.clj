(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (average-of-cubes 3))))

(run-test test-variation)
