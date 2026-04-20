(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 4 5] (group-by-bit-count [1 5 2 3 4])))
  (is (= [-6 -5 -4 -3 -2] (group-by-bit-count [-2 -3 -4 -5 -6])))
  (is (= [8 9 10 7] (group-by-bit-count [7 8 9 10]))))

(run-test test-variation)
