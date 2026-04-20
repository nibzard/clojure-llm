(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 1] (unique-transitions [1 1 2 2 3 1 1]))))

(run-test test-variation)
