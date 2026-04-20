(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 2] (change-base-digits 8 3)))
  (is (= [1 0 0 0] (change-base-digits 8 2)))
  (is (= [1 1 1] (change-base-digits 7 2)))
  (is (= [0] (change-base-digits 0 2))))

(run-test test-variation)
