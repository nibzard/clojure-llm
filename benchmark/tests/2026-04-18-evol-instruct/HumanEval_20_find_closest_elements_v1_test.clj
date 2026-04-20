(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [6 7] (closest-pair [10 3 8 6 7])))
  (is (= [9 10] (closest-pair [1 4 9 10 20])))
  (is (= [5 5] (closest-pair [5 5 2 9]))))

(run-test test-variation)
