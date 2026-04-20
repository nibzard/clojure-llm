(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 4 11) (eulerian-num [[3 0] [3 1] [4 1]]))))

(run-test test-variation)
