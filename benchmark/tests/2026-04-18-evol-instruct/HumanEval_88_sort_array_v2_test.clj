(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (sort_by_ends [])))
  (is (= [5] (sort_by_ends [5])))
  (is (= [5 4 3 2 1 0] (sort_by_ends [2 4 3 0 1 5])))
  (is (= [0 1 2 3 4 6] (sort_by_ends [2 4 3 0 1 6]))))

(run-test test-variation)
