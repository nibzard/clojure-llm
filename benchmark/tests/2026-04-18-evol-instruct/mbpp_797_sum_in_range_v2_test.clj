(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 18 (sum-in-range 1 10)))
  (is (= 18 (sum-in-range 10 1)))
  (is (= 0 (sum-in-range -5 5))))

(run-test test-variation)
