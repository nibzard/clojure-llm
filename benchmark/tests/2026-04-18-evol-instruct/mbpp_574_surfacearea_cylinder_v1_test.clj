(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 150.79644737231007 (surfacearea_cylinder 3 5)))
  (is (= 0.0 (surfacearea_cylinder nil 5)))
  (is (= 25.132741228718345 (surfacearea_cylinder 2 nil))))

(run-test test-variation)
