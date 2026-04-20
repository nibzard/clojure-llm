(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (digit-distance-coll [1 2 3] [4 5 6])))
  (is (= 11 (digit-distance-coll [9 1] [2 8 7])))
  (is (= 6 (digit-distance-coll nil [1 2 3]))))

(run-test test-variation)
