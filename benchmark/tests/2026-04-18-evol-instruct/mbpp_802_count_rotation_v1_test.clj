(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-rotation [15 18 2 3 6 12])))
  (is (= 4 (count-rotation [7 9 11 12 5])))
  (is (= 0 (count-rotation [1 2 3 4])))
  (is (= 3 (count-rotation [2 2 2 1 2]))))

(run-test test-variation)
