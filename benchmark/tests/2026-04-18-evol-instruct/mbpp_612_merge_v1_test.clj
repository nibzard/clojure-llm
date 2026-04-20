(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 4]] (split-at-nil [[1 2] [3 4] nil [5 6]])))
  (is (= [[] []] (split-at-nil []))))

(run-test test-variation)
