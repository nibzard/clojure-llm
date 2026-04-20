(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (min-range [3 8 1 10])))
  (is (= 1 (min-range [7 2 4])))
  (is (= nil (min-range [5])))
  (is (= nil (min-range nil))))

(run-test test-variation)
