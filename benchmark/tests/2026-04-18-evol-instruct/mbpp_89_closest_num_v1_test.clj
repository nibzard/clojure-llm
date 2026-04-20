(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (closest_num [10 3 8 1] 7)))
  (is (= 4 (closest_num (range) 5)))
  (is (= 4 (closest_num [9 2 6 4] 5))))

(run-test test-variation)
