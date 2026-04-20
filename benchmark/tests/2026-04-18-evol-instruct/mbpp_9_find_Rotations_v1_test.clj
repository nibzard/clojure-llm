(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (min-prefix-rotations [1 2 3 1 2 3])))
  (is (= 2 (min-prefix-rotations [:a :b :a :b])))
  (is (= nil (min-prefix-rotations [1 2 3 4]))))

(run-test test-variation)
