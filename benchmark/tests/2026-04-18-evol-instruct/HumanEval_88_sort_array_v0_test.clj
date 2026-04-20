(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (rotate-sort [])))
  (is (= [5] (rotate-sort [5])))
  (is (= [5 4 3 2 1 0] (rotate-sort [2 4 3 0 1 5])))
  (is (= [0 1 2 3 4 6] (rotate-sort [2 4 3 0 1 6]))))

(run-test test-variation)
