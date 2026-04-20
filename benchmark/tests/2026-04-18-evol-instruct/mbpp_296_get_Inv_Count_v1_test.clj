(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-descents [3 2 1])))
  (is (= 0 (count-descents [1 2 3])))
  (is (= 0 (count-descents nil))))

(run-test test-variation)
