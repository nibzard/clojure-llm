(defn surfacearea_cuboid
  "Return the surface area of a cuboid.

  Accepts a map with keys :length, :width, and :height.
  Missing dimensions are treated as 0.

  Examples:
  (surfacearea_cuboid {:length 2 :width 3 :height 4}) ;=> 52
  (surfacearea_cuboid {:length 5 :width 0 :height 2}) ;=> 20"
  [dims]
  (let [l (or (:length dims) 0)
        w (or (:width dims) 0)
        h (or (:height dims) 0)]
    (+ (* 2 l w)
       (* 2 l h)
       (* 2 w h))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 52 (surfacearea_cuboid {:length 2 :width 3 :height 4})))
  (is (= 20 (surfacearea_cuboid {:length 5 :width 0 :height 2})))
  (is (= 0 (surfacearea_cuboid {}))))

(run-test test-variation)
