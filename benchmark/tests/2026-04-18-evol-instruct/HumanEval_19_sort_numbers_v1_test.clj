(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 1 5] (sort-numbers-by-frequencies [3 1 3 2 2 2 5])))
  (is (= [1 2 3] (sort-numbers-by-frequencies [3 1 2 3 2 1])))
  (is (= [] (sort-numbers-by-frequencies nil))))

(run-test test-variation)
