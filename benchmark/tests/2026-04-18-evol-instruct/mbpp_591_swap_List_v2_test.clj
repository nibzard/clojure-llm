(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 3 1] (rotate-first-last [1 2 3 4]))))

(run-test test-variation)
