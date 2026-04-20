(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (sum-even-nums [1 2 3 4])))
  (is (= 14 (sum-even-nums '(nil 6 7 8)))))

(run-test test-variation)
