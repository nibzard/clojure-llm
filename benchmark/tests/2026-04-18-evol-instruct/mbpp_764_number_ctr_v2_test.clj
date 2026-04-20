(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (number-ctr [1 2 :a 3])))
  (is (= 2 (number-ctr '("x" 4 5 nil))))
  (is (= 0 (number-ctr nil))))

(run-test test-variation)
