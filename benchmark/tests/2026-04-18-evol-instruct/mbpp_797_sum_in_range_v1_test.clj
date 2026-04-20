(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-in-range 1 7)))
  (is (= 9 (sum-in-range nil 5)))
  (is (= 5 (sum-in-range 4 nil))))

(run-test test-variation)
