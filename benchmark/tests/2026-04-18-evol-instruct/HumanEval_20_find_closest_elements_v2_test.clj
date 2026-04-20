(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4] (find_closest_infinite_pair [1 4 7 10])))
  (is (= [8 6] (find_closest_infinite_pair [10 3 8 6])))
  (is (= [0 1] (find_closest_infinite_pair (iterate inc 0)))))

(run-test test-variation)
