(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4]] (split-evenly [1 2 3 4 5])))
  (is (= [[\a \c] [\b \d]] (split-evenly "abcd")))
  (is (= [[] []] (split-evenly nil))))

(run-test test-variation)
