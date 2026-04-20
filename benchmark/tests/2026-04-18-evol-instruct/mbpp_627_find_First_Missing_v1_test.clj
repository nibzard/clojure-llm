(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (first-missing-positive [3 4 -1 1])))
  (is (= 3 (first-missing-positive [1 2 0])))
  (is (= 1 (first-missing-positive nil)))
  (is (= 4 (first-missing-positive (range 1 4)))))

(run-test test-variation)
