(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (first-disorder-index [1 2 4 3 5])))
  (is (= -1 (first-disorder-index [1 2 3])))
  (is (= -1 (first-disorder-index [10])))
  (is (= 2 (first-disorder-index '(1 3 2 4)))))

(run-test test-variation)
