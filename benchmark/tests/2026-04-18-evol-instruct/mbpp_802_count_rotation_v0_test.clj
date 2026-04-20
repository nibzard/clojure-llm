(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-rots [15 18 2 3 6 12])))
  (is (= 4 (count-rots [7 9 11 12 5])))
  (is (= nil (count-rots []))))

(run-test test-variation)
