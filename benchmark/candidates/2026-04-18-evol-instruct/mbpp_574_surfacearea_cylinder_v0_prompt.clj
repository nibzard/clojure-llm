(defn surfacearea_cuboid
  "Return the surface area of a cuboid.

  Accepts a map with keys :length, :width, and :height.
  Missing dimensions are treated as 0.

  Examples:
  (surfacearea_cuboid {:length 2 :width 3 :height 4}) ;=> 52
  (surfacearea_cuboid {:length 5 :width 0 :height 2}) ;=> 20"
  [dims])