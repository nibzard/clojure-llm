(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (can-sum-to-target 3)))
  (is (= true (can-sum-to-target 7)))
  (is (= false (can-sum-to-target 8))))

(run-test test-variation)
