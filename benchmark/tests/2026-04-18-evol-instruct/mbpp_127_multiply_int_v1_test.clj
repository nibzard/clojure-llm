(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 24 (multiply-collection [2 3 4])))
  (is (= 30 (multiply-collection [2 nil 5 "x" 3])))
  (is (= 1 (multiply-collection nil))))

(run-test test-variation)
