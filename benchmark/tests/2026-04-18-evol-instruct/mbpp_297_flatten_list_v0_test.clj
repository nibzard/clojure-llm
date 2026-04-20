(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 (2 3) 4 5) (flatten-limited '((1 (2 3)) (4 5)) 1))))

(run-test test-variation)
