(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[2 4 6] [1 3 5]] (split-even-and-odd [1 2 3 4 5 6])))
  (is (= [[] []] (split-even-and-odd nil))))

(run-test test-variation)
