(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-nested-vectors [1 [2 3] {:a [4]} nil]))))

(run-test test-variation)
