(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (first-missing-odd [1 3 5 9])))
  (is (= 5 (first-missing-odd [nil -3 -1 1 1 3 7])))
  (is (= 1 (first-missing-odd []))))

(run-test test-variation)
