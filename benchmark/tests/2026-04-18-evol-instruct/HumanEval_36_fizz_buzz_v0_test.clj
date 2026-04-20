(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 30 (sum-ratios [1 2 3 4 6 8 nil 12]))))

(run-test test-variation)
