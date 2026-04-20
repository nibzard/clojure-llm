(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (min-of-many [3 1 4 2]))))

(run-test test-variation)
