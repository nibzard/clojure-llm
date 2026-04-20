(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_undulating-seq [1 2 1 2 1])))
  (is (= false (is_undulating-seq [3 3 3])))
  (is (= true (is_undulating-seq [])))
  (is (= true (is_undulating-seq nil))))

(run-test test-variation)
