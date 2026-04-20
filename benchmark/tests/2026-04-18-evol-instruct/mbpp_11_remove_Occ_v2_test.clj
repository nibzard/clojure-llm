(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2 4] (trim-first-last [1 2 3 2 4] 2))))

(run-test test-variation)
