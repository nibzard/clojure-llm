(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (pattern? [1 1 2 2 2 3] [2 3 1])))

(run-test test-variation)
