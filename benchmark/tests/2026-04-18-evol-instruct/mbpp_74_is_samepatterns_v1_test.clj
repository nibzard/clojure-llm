(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (matches-pattern? [1 2 1 2] [1 2]))))

(run-test test-variation)
