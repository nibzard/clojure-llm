(defn area_tetrahedron
  "	Write a function to caluclate the area of a tetrahedron."
  [side]
  (when (number? side)
    (* (Math/sqrt 3.0) side side)))