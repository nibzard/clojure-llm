(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-descents [1 3 2 5 4])))
  (is (= 2 (count-descents [5 4 3])))
  (is (= 0 (count-descents nil))))

(run-test test-variation)
