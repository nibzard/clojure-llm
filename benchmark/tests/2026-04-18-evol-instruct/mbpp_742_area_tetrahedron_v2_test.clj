(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1.7320508075688772 (tetrahedron-surface-area 1)))
  (is (= 6.928203230275509 (tetrahedron-surface-area 2)))
  (is (= nil (tetrahedron-surface-area nil))))

(run-test test-variation)
