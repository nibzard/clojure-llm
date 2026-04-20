(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-distinct-pairs [1 1 2 2])))
  (is (= 0 (count-distinct-pairs [5])))
  (is (= 0 (count-distinct-pairs nil))))

(run-test test-variation)
