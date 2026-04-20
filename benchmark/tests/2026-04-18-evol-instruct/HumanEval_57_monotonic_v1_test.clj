(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (monotonic-slices [])))
  (is (= true (monotonic-slices [1])))
  (is (= true (monotonic-slices [1 2 3 2 1])))
  (is (= true (monotonic-slices [1 3 5 4 2 6 7])))
  (is (= false (monotonic-slices [1 2 3 3 2]))))

(run-test test-variation)
