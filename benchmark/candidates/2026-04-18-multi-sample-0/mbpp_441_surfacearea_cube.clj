(defn surfacearea_cube
  "	Write a function to find the surface area of a cube of a given size."
  [l]
  (if (nil? l)
    nil
    (* 6 l l)))