(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-unique-seq [1 2 2 3 3 3])))
  (is (= 2 (count-unique-seq [nil :a nil :b :a])))
  (is (= 0 (count-unique-seq nil))))

(run-test test-variation)
