(defn area_tetrahedron
  "Write a function to calculate the area of a tetrahedron."
  [side]
  (when side
    (* (Math/sqrt 3) (* side side))))