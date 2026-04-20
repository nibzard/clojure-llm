(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-prefix-rotations [1 2 1 2])))
  (is (= nil (count-prefix-rotations [:a :b :c])))
  (is (= nil (count-prefix-rotations [])))
  (is (= nil (count-prefix-rotations nil))))

(run-test test-variation)
