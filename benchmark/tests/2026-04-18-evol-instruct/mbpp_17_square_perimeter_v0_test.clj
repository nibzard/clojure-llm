(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 14 (rectangle-perimeter [3 4])))
  (is (= 14 (rectangle-perimeter '(5 2))))
  (is (= 0 (rectangle-perimeter nil))))

(run-test test-variation)
