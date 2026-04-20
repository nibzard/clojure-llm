(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-even-pairs [1 2 3 4])))
  (is (= 3 (count-even-pairs [2 4 6])))
  (is (= 0 (count-even-pairs [])))
  (is (= 0 (count-even-pairs nil))))

(run-test test-variation)
