(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-position-sums [1 2 1])))
  (is (= true (odd-position-sums [2 3 1 4 2])))
  (is (= false (odd-position-sums [1 5 2]))))

(run-test test-variation)
