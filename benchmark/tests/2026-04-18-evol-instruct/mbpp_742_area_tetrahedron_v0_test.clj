(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0.11785113019775793 (tetrahedron-volume 1)))
  (is (= 0.9428090415820634 (tetrahedron-volume 2)))
  (is (= 0.0 (tetrahedron-volume nil))))

(run-test test-variation)
