(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (left-insertion-index [1 2 2 4 5] 2)))
  (is (= 3 (left-insertion-index [1 2 2 4 5] 3)))
  (is (= 0 (left-insertion-index [] 10))))

(run-test test-variation)
