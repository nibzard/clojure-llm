(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (min-rotations-to-repeat [1 2 1 2])))
  (is (= 3 (min-rotations-to-repeat [:a :b :c])))
  (is (= 1 (min-rotations-to-repeat [7 7 7 7])))
  (is (= 0 (min-rotations-to-repeat []))))

(run-test test-variation)
