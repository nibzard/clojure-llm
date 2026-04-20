(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (next_multiple_of_3 1)))
  (is (= 3 (next_multiple_of_3 3)))
  (is (= 12 (next_multiple_of_3 10)))
  (is (= 0 (next_multiple_of_3 0)))
  (is (= -3 (next_multiple_of_3 -2))))

(run-test test-variation)
