(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1] (eulerian-row 0)))
  (is (= [1] (eulerian-row 1)))
  (is (= [1 4 1] (eulerian-row 3))))

(run-test test-variation)
