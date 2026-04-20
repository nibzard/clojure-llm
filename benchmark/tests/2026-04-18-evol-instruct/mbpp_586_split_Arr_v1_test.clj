(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 4 1] (rotate-left [1 2 3 4] 1))))

(run-test test-variation)
