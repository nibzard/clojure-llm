(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 30 (sum-months-with-thirty-days [1 4 6 9 11])))
  (is (= 10 (sum-months-with-thirty-days [2 3 4 13 nil 6])))
  (is (= 0 (sum-months-with-thirty-days nil))))

(run-test test-variation)
