(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2] (nth-finite-prefix [1 2 3 4] 2))))

(run-test test-variation)
