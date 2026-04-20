(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 5 (count-distinct-pairs [1 1 2 3]))))

(run-test test-variation)
