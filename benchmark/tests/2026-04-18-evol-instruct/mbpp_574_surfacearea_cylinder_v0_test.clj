(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 52 (surfacearea_cuboid {:length 2 :width 3 :height 4})))
  (is (= 20 (surfacearea_cuboid {:length 5 :width 0 :height 2})))
  (is (= 0 (surfacearea_cuboid {}))))

(run-test test-variation)
