(defn lateralsurface_cube
  "	Write a function to find the lateral surface area of a cube given its side length."
  [l]
  (if (nil? l)
    nil
    (* 4 l l)))