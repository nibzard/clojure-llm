(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-distinct-pairs [1 2 3])))
  (is (= 1 (count-distinct-pairs [1 1 2 2])))
  (is (= 3 (count-distinct-pairs [1 nil 2 2 nil 3])))
  (is (= 0 (count-distinct-pairs []))))

(run-test test-variation)
